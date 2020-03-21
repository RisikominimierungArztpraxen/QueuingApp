package de.risikominimierungarztpraxen.queuingApp.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * an appointment
 */
@ApiModel(description = "an appointment")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-21T14:46:54.089Z[GMT]")
public class AppointmentBase {
    @JsonProperty("time")
    private String time = null;

    @JsonProperty("patientId")
    private String patientId = null;

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
                Objects.equals(this.estimatedInMinutes, appointmentBase.estimatedInMinutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, patientId, estimatedInMinutes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AppointmentBase {\n");

        sb.append("    time: ").append(toIndentedString(time)).append("\n");
        sb.append("    patientId: ").append(toIndentedString(patientId)).append("\n");
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
