package de.risikominimierungarztpraxen.queuingApp.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.risikominimierungarztpraxen.queuingApp.model.Office;
import de.risikominimierungarztpraxen.queuingApp.service.OfficeService;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-21T14:46:54.089Z[GMT]")
@Controller
public class OfficeApiController implements OfficeApi {

    private static final Logger log = LoggerFactory.getLogger(OfficeApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private final OfficeService officeService;

    @Autowired
    public OfficeApiController(ObjectMapper objectMapper, HttpServletRequest request, OfficeService officeService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.officeService = officeService;
    }

    @Override
    public ResponseEntity<Void> officeOfficeIdDelete(@ApiParam(value = "", required = true) @PathVariable("officeId") String officeId) {
        officeService.deleteOffice(officeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Office> officePost(@ApiParam(value = "new medical office") @Valid @RequestBody Office body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(officeService.createOffice(body), HttpStatus.NOT_IMPLEMENTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
