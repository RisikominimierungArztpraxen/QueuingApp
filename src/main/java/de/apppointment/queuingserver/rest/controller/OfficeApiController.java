package de.apppointment.queuingserver.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import de.apppointment.queuingserver.rest.model.OfficeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import de.apppointment.queuingserver.service.office.OfficeService;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-21T14:46:54.089Z[GMT]")
@Controller
public class OfficeApiController implements OfficeApi {

    private final HttpServletRequest request;
    private final OfficeService officeService;

    @Autowired
    public OfficeApiController(HttpServletRequest request, OfficeService officeService) {
        this.request = request;
        this.officeService = officeService;
    }

    @Override
    public ResponseEntity<Void> officeOfficeIdDelete(@ApiParam(value = "", required = true) @PathVariable("officeId") String officeId) {
        officeService.deleteOffice(officeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OfficeDto> officePost(@ApiParam(value = "new medical office") @Valid @RequestBody OfficeDto body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(officeService.createOffice(body), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
