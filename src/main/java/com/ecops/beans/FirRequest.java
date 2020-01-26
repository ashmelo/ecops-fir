package com.ecops.beans;

import com.ecops.domain.CrimeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirRequest {

    private String userId;
    private CrimeStatus crimeCommit;
    private String description;
    private String place;
    private Instant incidentDate;
    private String address;
    private String mobileNumber;
    private String district;
    private String state;
    private int pincode;
}
