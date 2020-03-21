package de.risikominimierungarztpraxen.queuingApp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class AppointmentCreator extends AppointmentBase {

    @JsonProperty("notifications")
    @Valid
    private List<AppointmentBaseNotifications> notifications = null;

    public AppointmentBase notifications(List<AppointmentBaseNotifications> notifications) {
        this.notifications = notifications;
        return this;
    }

    public AppointmentBase addNotificationsItem(AppointmentBaseNotifications notificationsItem) {
        if (this.notifications == null) {
            this.notifications = new ArrayList<>();
        }
        this.notifications.add(notificationsItem);
        return this;
    }

    /**
     * Get notifications
     * @return notifications
    **/
    @ApiModelProperty(value = "")
    @Valid
    public List<AppointmentBaseNotifications> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<AppointmentBaseNotifications> notifications) {
        this.notifications = notifications;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
        return result;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppointmentCreator appointment = (AppointmentCreator) o;
        return Objects.equals(this.notifications, appointment.notifications) &&
                super.equals(o);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Appointment {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    place: ").append(toIndentedString(notifications)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
