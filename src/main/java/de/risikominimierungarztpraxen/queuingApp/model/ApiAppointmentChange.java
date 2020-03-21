package de.risikominimierungarztpraxen.queuingApp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * change an appointment
 */
@ApiModel(description = "change an appointment")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-21T14:46:54.089Z[GMT]")
public class ApiAppointmentChange {
    @JsonProperty("notifications")
    @Valid
    private List<ApiNotification> notifications = null;

    public ApiAppointmentChange notifications(List<ApiNotification> notifications) {
        this.notifications = notifications;
        return this;
    }

    public ApiAppointmentChange addNotificationsItem(ApiNotification notificationsItem) {
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

    public List<ApiNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<ApiNotification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiAppointmentChange appointmentChange = (ApiAppointmentChange) o;
        return Objects.equals(this.notifications, appointmentChange.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notifications);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AppointmentChange {\n");

        sb.append("    notifications: ").append(toIndentedString(notifications)).append("\n");
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
