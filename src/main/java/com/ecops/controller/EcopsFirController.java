package com.ecops.controller;


import com.ecops.beans.AllFirResponse;
import com.ecops.beans.FirRequest;
import com.ecops.beans.FirResponse;
import com.ecops.beans.UpdateCaseRequest;
import com.ecops.exception.BadRequestException;
import com.ecops.service.FirService;
import com.ecops.wrapper.TokenVerify;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/fir/api/v1")
public class EcopsFirController {

    @Autowired
    TokenVerify tokenVerify;

    @Autowired
    FirService firService;

    @PostMapping(value = "/file_fir", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "File a FIR")
    public ResponseEntity fileFir(HttpServletRequest request, @RequestBody FirRequest firRequest) {
        try {
            if (tokenVerify.verifyToken(request, firRequest.getUserId())) {
                firService.createFir(firRequest);
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
        } catch (Exception e) {
            throw new BadRequestException("Error while creating FIR");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(value="/get_filed_fir/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all filed FIR's")
    public ResponseEntity<List<AllFirResponse>> getAllFir(HttpServletRequest request, @PathVariable("userId") String userId) {
        try {
            if (tokenVerify.verifyToken(request, userId)) {
                //firService.createFir(firRequest);
                return new ResponseEntity(firService.getAllFir(userId),HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new BadRequestException("Error while fetching FIR");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(value="/get_fir/{userId}/{firId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get specific FIR")
    public ResponseEntity<FirResponse> getFir(HttpServletRequest request, @PathVariable("userId") String userId, @PathVariable("firId") int firId) {
        try {
            if (tokenVerify.verifyToken(request, userId)) {
                //firService.createFir(firRequest);x
                return new ResponseEntity(firService.getFir(userId,firId),HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new BadRequestException("Error while fetching FIR with id {" + firId +"}");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(value="/get_cases/{loginId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all cases")
    public ResponseEntity<List<AllFirResponse>> getAllCases(HttpServletRequest request, @PathVariable("loginId") String loginId) {
        try {
            if (tokenVerify.verifyPoliceToken(request, loginId)) {
                System.out.println("Successfully validated");
                return new ResponseEntity<>(firService.getAllCases(loginId),HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new BadRequestException("Error while fetching cases with id {" + loginId +"}");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(value="/get_case_details/{loginId}/{caseId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get specific case details for the police")
    public ResponseEntity<FirResponse> getCaseDetails(HttpServletRequest request, @PathVariable("loginId") String loginId, @PathVariable("caseId") int caseId) {
        try {
            if (tokenVerify.verifyPoliceToken(request, loginId)) {
                return new ResponseEntity<>(firService.getCaseDetails(loginId,caseId),HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new BadRequestException("Error while fetching FIR with id {" + caseId +"}");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value="/update_case_status/{loginId}/{caseId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all cases")
    public ResponseEntity updateCaseStatus(HttpServletRequest request, @PathVariable("loginId") String loginId, @PathVariable("caseId") int caseId,
                                           @RequestBody UpdateCaseRequest updateCaseRequest) {
        try {
            if (tokenVerify.verifyPoliceToken(request, loginId)) {
                firService.updateCaseStatus(loginId,caseId,updateCaseRequest);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            throw new BadRequestException("Error while fetching FIR with id {" + caseId +"}");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
