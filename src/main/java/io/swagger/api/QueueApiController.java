package io.swagger.api;

import io.swagger.model.Appointment;
import io.swagger.model.AppointmentBase;
import io.swagger.model.AppointmentChange;
import org.threeten.bp.LocalDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-21T14:46:54.089Z[GMT]")
@Controller
public class QueueApiController implements QueueApi {

    private static final Logger log = LoggerFactory.getLogger(QueueApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public QueueApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Appointment> queueOfficeIdDayPatientIdDelete(@ApiParam(value = "",required=true) @PathVariable("officeId") String officeId
,@ApiParam(value = "",required=true) @PathVariable("patientId") Integer patientId
,@ApiParam(value = "",required=true) @PathVariable("day") LocalDate day
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Appointment>(objectMapper.readValue("\"\"", Appointment.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Appointment>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Appointment>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Appointment> queueOfficeIdDayPatientIdGet(@ApiParam(value = "",required=true) @PathVariable("officeId") String officeId
,@ApiParam(value = "",required=true) @PathVariable("patientId") Integer patientId
,@ApiParam(value = "",required=true) @PathVariable("day") LocalDate day
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Appointment>(objectMapper.readValue("\"\"", Appointment.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Appointment>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Appointment>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Appointment> queueOfficeIdDayPatientIdPut(@ApiParam(value = "",required=true) @PathVariable("officeId") String officeId
,@ApiParam(value = "",required=true) @PathVariable("patientId") Integer patientId
,@ApiParam(value = "",required=true) @PathVariable("day") LocalDate day
,@ApiParam(value = ""  )  @Valid @RequestBody AppointmentChange body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Appointment>(objectMapper.readValue("\"\"", Appointment.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Appointment>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Appointment>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> queueOfficeIdDayPost(@ApiParam(value = "",required=true) @PathVariable("officeId") String officeId
,@ApiParam(value = "",required=true) @PathVariable("day") LocalDate day
,@ApiParam(value = "the new patient"  )  @Valid @RequestBody AppointmentBase body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> queueOfficeIdDayPut(@ApiParam(value = "",required=true) @PathVariable("officeId") String officeId
,@ApiParam(value = "",required=true) @PathVariable("day") LocalDate day
,@ApiParam(value = "the new patient"  )  @Valid @RequestBody List<AppointmentBase> body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
