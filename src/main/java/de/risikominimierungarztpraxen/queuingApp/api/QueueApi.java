/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.18).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package de.risikominimierungarztpraxen.queuingApp.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.threeten.bp.LocalDate;

import de.risikominimierungarztpraxen.queuingApp.model.Appointment;
import de.risikominimierungarztpraxen.queuingApp.model.AppointmentBase;
import de.risikominimierungarztpraxen.queuingApp.model.AppointmentChange;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-21T14:46:54.089Z[GMT]")
@Api(value = "queue", description = "the queue API")
public interface QueueApi {

    @ApiOperation(value = "", nickname = "queueOfficeIdDayPatientIdDelete", notes = "", response = Appointment.class, tags = {"queue",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Appointment.class)})
    @RequestMapping(value = "/queue/{officeId}/{day}/{patientId}", produces = {"application/json"}, method = RequestMethod.DELETE)
    ResponseEntity<Appointment> queueOfficeIdDayPatientIdDelete(@ApiParam(value = "", required = true) @PathVariable("officeId") String officeId, @ApiParam(value = "", required = true) @PathVariable("patientId") Integer patientId, @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day);

    @ApiOperation(value = "", nickname = "queueOfficeIdDayPatientIdGet", notes = "", response = Appointment.class, tags = {"queue",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Appointment.class)})
    @RequestMapping(value = "/queue/{officeId}/{day}/{patientId}", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<Appointment> queueOfficeIdDayPatientIdGet(@ApiParam(value = "", required = true) @PathVariable("officeId") String officeId, @ApiParam(value = "", required = true) @PathVariable("patientId") Integer patientId, @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day);

    @ApiOperation(value = "", nickname = "queueOfficeIdDayPatientIdPut", notes = "", response = Appointment.class, tags = {"queue",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Appointment.class)})
    @RequestMapping(value = "/queue/{officeId}/{day}/{patientId}", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.PUT)
    ResponseEntity<Appointment> queueOfficeIdDayPatientIdPut(@ApiParam(value = "", required = true) @PathVariable("officeId") String officeId, @ApiParam(value = "", required = true) @PathVariable("patientId") Integer patientId, @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day, @ApiParam(value = "") @Valid @RequestBody AppointmentChange body);

    @ApiOperation(value = "", nickname = "queueOfficeIdDayPost", notes = "", tags = {"queue",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success")})
    @RequestMapping(value = "/queue/{officeId}/{day}", consumes = {"application/json"}, method = RequestMethod.POST)
    ResponseEntity<Void> queueOfficeIdDayPost(@ApiParam(value = "", required = true) @PathVariable("officeId") String officeId, @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day, @ApiParam(value = "the new patient") @Valid @RequestBody AppointmentBase body);

    @ApiOperation(value = "", nickname = "queueOfficeIdDayPut", notes = "replace the whole queue", tags = {"queue",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success")})
    @RequestMapping(value = "/queue/{officeId}/{day}", consumes = {"application/json"}, method = RequestMethod.PUT)
    ResponseEntity<Void> queueOfficeIdDayPut(@ApiParam(value = "", required = true) @PathVariable("officeId") String officeId, @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day, @ApiParam(value = "the new patient") @Valid @RequestBody List<AppointmentBase> body);

}