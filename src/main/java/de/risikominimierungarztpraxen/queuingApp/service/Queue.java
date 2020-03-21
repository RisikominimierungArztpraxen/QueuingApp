package de.risikominimierungarztpraxen.queuingApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointment;
import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointmentChange;
import de.risikominimierungarztpraxen.queuingApp.model.ApiAppointmentCreator;

/**
 * The Queue Class handles a single Queue of one office.
 * @author Philipp Endrulat
 *
 */
public class Queue {

    private final List<Appointment> appointments = new ArrayList<>();

    /**
     * Adds one new appointment.
     * @param appointmentCreator
     */
    public synchronized ApiAppointment addAppointment(ApiAppointmentCreator appointmentCreator) {
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
                this.appointments.remove(i);
                this.removeMinutesFromAllApointments(appointment.getEstimatedInMinutes(), i);
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
    public ApiAppointment updateAppointment(String patientId, ApiAppointmentChange appointmentChange) {
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
    public Optional<ApiAppointment> findAppointment(String patientId) {
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
            if (next.getEstimatedWaitingTime() >= minutes) {
                next.setEstimatedWaitingTime(next.getEstimatedWaitingTime() - minutes);
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
        }
    }
}
