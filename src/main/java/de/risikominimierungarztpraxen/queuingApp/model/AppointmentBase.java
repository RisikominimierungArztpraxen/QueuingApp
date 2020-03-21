package de.risikominimierungarztpraxen.queuingApp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * an appointment
 */
@ApiModel(description = "an appointment")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-21T14:46:54.089Z[GMT]")
public class AppointmentBase   {
  @JsonProperty("time")
  private String time = null;

  @JsonProperty("patientId")
  private String patientId = null;

  @JsonProperty("notifications")
  @Valid
  private List<AppointmentBaseNotifications> notifications = null;

  @JsonProperty("estimatedInMinutes")
  private Integer estimatedInMinutes = 15;

  public AppointmentBase time(String time) {
    this.time = time;
    return this;
  }

  /**
   * Get time
   * @return time
  **/
  @ApiModelProperty(value = "")
  
    public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public AppointmentBase patientId(String patientId) {
    this.patientId = patientId;
    return this;
  }

  /**
   * Get patientId
   * @return patientId
  **/
  @ApiModelProperty(value = "")
  
    public String getPatientId() {
    return patientId;
  }

  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  public AppointmentBase notifications(List<AppointmentBaseNotifications> notifications) {
    this.notifications = notifications;
    return this;
  }

  public AppointmentBase addNotificationsItem(AppointmentBaseNotifications notificationsItem) {
    if (this.notifications == null) {
      this.notifications = new ArrayList<AppointmentBaseNotifications>();
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

  public AppointmentBase estimatedInMinutes(Integer estimatedInMinutes) {
    this.estimatedInMinutes = estimatedInMinutes;
    return this;
  }

  /**
   * Get estimatedInMinutes
   * @return estimatedInMinutes
  **/
  @ApiModelProperty(value = "")
  
    public Integer getEstimatedInMinutes() {
    return estimatedInMinutes;
  }

  public void setEstimatedInMinutes(Integer estimatedInMinutes) {
    this.estimatedInMinutes = estimatedInMinutes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppointmentBase appointmentBase = (AppointmentBase) o;
    return Objects.equals(this.time, appointmentBase.time) &&
        Objects.equals(this.patientId, appointmentBase.patientId) &&
        Objects.equals(this.notifications, appointmentBase.notifications) &&
        Objects.equals(this.estimatedInMinutes, appointmentBase.estimatedInMinutes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(time, patientId, notifications, estimatedInMinutes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppointmentBase {\n");
    
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    patientId: ").append(toIndentedString(patientId)).append("\n");
    sb.append("    notifications: ").append(toIndentedString(notifications)).append("\n");
    sb.append("    estimatedInMinutes: ").append(toIndentedString(estimatedInMinutes)).append("\n");
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
