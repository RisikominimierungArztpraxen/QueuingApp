package de.risikominimierungarztpraxen.queuingApp.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.threeten.bp.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.risikominimierungarztpraxen.queuingApp.model.Appointment;
import de.risikominimierungarztpraxen.queuingApp.model.AppointmentBase;
import de.risikominimierungarztpraxen.queuingApp.model.AppointmentChange;
import de.risikominimierungarztpraxen.queuingApp.service.QueueService;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-21T14:46:54.089Z[GMT]")
@Controller
public class QueueApiController implements QueueApi {

    private static final Logger log = LoggerFactory.getLogger(QueueApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private final QueueService queueService;

    @org.springframework.beans.factory.annotation.Autowired
    public QueueApiController(ObjectMapper objectMapper, HttpServletRequest request, QueueService queueService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.queueService = queueService;
    }

    @Override
    public ResponseEntity<Void> queueOfficeIdDayPatientIdDelete(
            @ApiParam(value = "", required = true) @PathVariable("officeId") String officeId,
            @ApiParam(value = "", required = true) @PathVariable("patientId") Integer patientId,
            @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day) {
        queueService.deleteAppointment(officeId, patientId, day);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Appointment> queueOfficeIdDayPatientIdGet(
            @ApiParam(value = "", required = true) @PathVariable("officeId") String officeId,
            @ApiParam(value = "", required = true) @PathVariable("patientId") Integer patientId,
            @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(queueService.getAppointment(officeId, patientId, day), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Appointment> queueOfficeIdDayPatientIdPut(
            @ApiParam(value = "", required = true) @PathVariable("officeId") String officeId,
            @ApiParam(value = "", required = true) @PathVariable("patientId") Integer patientId,
            @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day,
            @ApiParam(value = "") @Valid @RequestBody AppointmentChange body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(queueService.updateAppointment(officeId, day, patientId, body), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Appointment> queueOfficeIdDayPost(
            @ApiParam(value = "", required = true) @PathVariable("officeId") String officeId,
            @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day,
            @ApiParam(value = "the new patient") @Valid @RequestBody AppointmentBase body) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(queueService.createAppointMent(officeId, day, body), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> queueOfficeIdDayPut(@ApiParam(value = "", required = true) @PathVariable("officeId") String officeId, @ApiParam(value = "", required = true) @PathVariable("day") LocalDate day, @ApiParam(value = "the new patient") @Valid @RequestBody List<AppointmentBase> body) {
        queueService.replaceQueue(officeId, day, body);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
