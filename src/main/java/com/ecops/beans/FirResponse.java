package com.ecops.beans;

import com.ecops.domain.CrimeStatus;
import com.ecops.domain.FirStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FirResponse {

    private int firId;
    private CrimeStatus crimeCommit;
    private String description;
    private String place;
    private Instant incidentDate;
    private Instant filedDate;
    private String address;
    private String mobileNumber;
    private String district;
    private String state;
    private int pincode;
    private String policeOfficerName;
    private String stationName;
    private FirStatus status;
    private String comments;

}
