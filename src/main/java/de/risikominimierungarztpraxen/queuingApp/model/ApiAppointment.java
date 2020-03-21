package de.risikominimierungarztpraxen.queuingApp.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-21T14:46:54.089Z[GMT]")
public class ApiAppointment extends ApiAppointmentBase {
    @JsonProperty("place")
    private Integer place;

    @JsonProperty("estimateWaitingMinutes")
    private Integer estimateWaitingMinutes;

    @JsonCreator
    public ApiAppointment(
            @JsonProperty("time") String time,
            @JsonProperty("patientId") String patientId,
            @JsonProperty("estimatedInMinutes") Integer estimatedInMinutes,
            @JsonProperty("place") Integer place,
            @JsonProperty("estimateWaitingMinutes") Integer estimateWaitingMinutes) {
        super(time, patientId, estimatedInMinutes);
        this.place = place;
        this.estimateWaitingMinutes = estimateWaitingMinutes;
    }

    public ApiAppointment place(Integer place) {
        this.place = place;
        return this;
    }

    /**
     * Get place
     * @return place
    **/
    @ApiModelProperty(value = "")

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public ApiAppointment estimateWaitingMinutes(Integer estimateWaitingMinutes) {
        this.estimateWaitingMinutes = estimateWaitingMinutes;
        return this;
    }

    /**
     * Get estimateWaitingMinutes
     * @return estimateWaitingMinutes
    **/
    @ApiModelProperty(value = "")

    public Integer getEstimateWaitingMinutes() {
        return estimateWaitingMinutes;
    }

    public void setEstimateWaitingMinutes(Integer estimateWaitingMinutes) {
        this.estimateWaitingMinutes = estimateWaitingMinutes;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiAppointment appointment = (ApiAppointment) o;
        return Objects.equals(this.place, appointment.place) &&
                Objects.equals(this.estimateWaitingMinutes, appointment.estimateWaitingMinutes) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, estimateWaitingMinutes, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Appointment {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    place: ").append(toIndentedString(place)).append("\n");
        sb.append("    estimateWaitingMinutes: ").append(toIndentedString(estimateWaitingMinutes)).append("\n");
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
