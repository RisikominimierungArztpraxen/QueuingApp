package de.risikominimierungarztpraxen.queuingApp.rest.api;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import de.risikominimierungarztpraxen.queuingApp.rest.model.AppointmentDto;
import de.risikominimierungarztpraxen.queuingApp.rest.model.AppointmentChangeDto;
import de.risikominimierungarztpraxen.queuingApp.rest.model.AppointmentCreatorDto;
import de.risikominimierungarztpraxen.queuingApp.service.queue.QueueService;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-21T14:46:54.089Z[GMT]")
@Controller
public class QueueApiController implements QueueApi {

    private final HttpServletRequest request;
    private final QueueService queueService;

    @org.springframework.beans.factory.annotation.Autowired
    public QueueApiController(HttpServletRequest request, QueueService queueService) {
        this.request = request;
        this.queueService = queueService;
    }

    @Override
    public ResponseEntity<Void> queueOfficeIdDayPatientIdDelete(
            @ApiParam(value = "", required = true) @PathVariable("officeId") String officeId,
            @ApiParam(value = "", required = true) @PathVariable("patientId") String patientId,
            @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day) {
        queueService.deleteAppointment(officeId, patientId, day);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AppointmentDto> queueOfficeIdDayPatientIdGet(
            @ApiParam(value = "", required = true) @PathVariable("officeId") String officeId,
            @ApiParam(value = "", required = true) @PathVariable("patientId") String patientId,
            @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(queueService.getAppointment(officeId, patientId, day), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<AppointmentDto> queueOfficeIdDayPatientIdPut(
            @ApiParam(value = "", required = true) @PathVariable("officeId") String officeId,
            @ApiParam(value = "", required = true) @PathVariable("patientId") String patientId,
            @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day,
            @ApiParam(value = "") @Valid @RequestBody AppointmentChangeDto body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(queueService.updateAppointment(officeId, day, patientId, body), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<AppointmentDto> queueOfficeIdDayPost(
            @ApiParam(value = "", required = true) @PathVariable("officeId") String officeId,
            @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day,
            @ApiParam(value = "the new patient") @Valid @RequestBody AppointmentCreatorDto body) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(queueService.createAppointMent(officeId, day, body), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> queueOfficeIdDayPut(
            @ApiParam(value = "", required = true) @PathVariable("officeId") String officeId,
            @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day,
            @ApiParam(value = "the new patient") @Valid @RequestBody List<AppointmentCreatorDto> body) {
        queueService.replaceQueue(officeId, day, body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
