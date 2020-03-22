package de.risikominimierungarztpraxen.queuingApp.service.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.risikominimierungarztpraxen.queuingApp.rest.model.AppointmentDto;
import de.risikominimierungarztpraxen.queuingApp.rest.model.AppointmentChangeDto;
import de.risikominimierungarztpraxen.queuingApp.rest.model.AppointmentCreatorDto;
import de.risikominimierungarztpraxen.queuingApp.rest.model.NotificationDto;
import de.risikominimierungarztpraxen.queuingApp.service.notification.NotificationService;
import de.risikominimierungarztpraxen.queuingApp.service.notification.NotificationServiceImpl;

/**
 * The Queue Class handles a single Queue of one office.
 * @author Philipp Endrulat
 *
 */
public class Queue {

    private final List<Appointment> appointments = new ArrayList<>();

    // TODO Must be refactored for better testability:
    private final NotificationService notificationService = new NotificationServiceImpl();

    /**
     * Adds one new appointment.
     * @param appointmentCreator
     */
    public synchronized AppointmentDto addAppointment(AppointmentCreatorDto appointmentCreator) {
        int estimate = this.appointments.stream().mapToInt(appointment -> appointment.getEstimatedInMinutes()).sum();
        Appointment appointment = new Appointment(appointmentCreator, estimate);
        this.appointments.add(appointment);
        return appointment.toAppointment(appointments.size());
    }

    /**
     * Remove one appointment
     * @param patientId
     */
    public synchronized void removeAppointment(String patientId) {
        for (int i = 0; i < this.appointments.size(); i++) {
            Appointment appointment = this.appointments.get(i);
            if (appointment.getPatientId().equals(patientId)) {
                this.removeMinutesFromAllApointments(appointment.getEstimatedInMinutes(), i + 1);
                this.appointments.remove(i);
                return;
            }
        }
    }

    /**
     *
     * @return all appointments
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Update one appointment. Supports only changes of the notification. Will replace the notifications with the notifications given in the appointementChange object
     * @param patientId
     * @param appointmentChange
     * @return
     */
    public AppointmentDto updateAppointment(String patientId, AppointmentChangeDto appointmentChange) {
        Appointment appointment = this.appointments.stream().filter(app -> app.getPatientId().equals(patientId)).findFirst().orElseThrow(() -> new IllegalArgumentException("patientId not found. Could not update Appointment"));
        appointment.setNotifications(appointmentChange.getNotifications());
        return findAppointment(patientId).orElseThrow(() -> new IllegalStateException("appointment suddenly not there anymore?!"));
    }

    /**
     * Removes all appointments from the queue
     */
    public synchronized void clear() {
        this.appointments.clear();
    }

    /**
     * Tries to find one appointment and returns it as optional, if found. Will never return null
     * @param patientId
     * @return
     */
    public Optional<AppointmentDto> findAppointment(String patientId) {
        int place = 0;
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId)) {
                return Optional.of(appointment.toAppointment(place));
            }
            place++;
        }
        return Optional.empty();
    }

    public void removeOneMinuteFromAllAppointments() {
        this.removeMinutesFromAllApointments(1, 0);
    }

    /**
     * Will try to remove one minute of each appointment waiting time. <br />
     * - The waiting time can never be less than zero <br />
     * - The waiting time can never be less than the waiting time of the previous appointment plus its estimate
     */
    public synchronized void removeMinutesFromAllApointments(int minutes, int fromIndex) {
        Appointment next = appointments.get(0);
        if (fromIndex == 0) {
            if (next.getEstimatedInMinutes() >= minutes) {
                next.setEstimatedInMinutes(next.getEstimatedInMinutes() - minutes);
            } else {
                next.setEstimatedInMinutes(0);
            }
            fromIndex++;
        }

        for (int i = fromIndex; i < appointments.size(); i++) {
            Appointment currentApp = appointments.get(i);
            Appointment previousApp = appointments.get(i - 1);
            int minimumWaiting = previousApp.getEstimatedWaitingTime() + previousApp.getEstimatedInMinutes();
            int nextWaitingTime = currentApp.getEstimatedWaitingTime() >= minimumWaiting + minutes
                    ? currentApp.getEstimatedWaitingTime() - minutes
                    : minimumWaiting;
            currentApp.setEstimatedWaitingTime(nextWaitingTime);
            notifyIfAppointmentIsDueToNotification(currentApp);
        }
    }

    private void notifyIfAppointmentIsDueToNotification(Appointment appointment) {
        if (shouldSendNow(appointment.getEstimatedWaitingTime())) {
            NotificationDto notification = getFirstAvailableNotification(appointment.getNotifications());
            if (notification != null) {
                notificationService.sendNotification(notification, "Testmessage");
            }
        }
    }

    private boolean shouldSendNow(int remainingMinutes) {
        return (remainingMinutes == 60
            || remainingMinutes == 30
            || remainingMinutes == 15
            || remainingMinutes == 5);
    }

    private NotificationDto getFirstAvailableNotification(List<NotificationDto> notifications){
        List<NotificationDto> validNotifications = notifications.stream().filter(notification -> notification.getIdentifier() != null && notification.getIdentifier() != null).collect(Collectors.toList());
        if (!validNotifications.isEmpty()) {
            return validNotifications.get(0);
        }

        return null;
    }
}
