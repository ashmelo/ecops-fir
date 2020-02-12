package com.ecops.beans;

import com.ecops.domain.CrimeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirRequest {

    @NotNull(message = "userId cannot be null")
    @NotEmpty(message = "userId cannot be empty")
    private String userId;
    @NotNull(message = "crimeCommit cannot be null")
    @NotEmpty(message = "crimeCommit cannot be empty")
    private CrimeStatus crimeCommit;
    private String description;
    @NotNull(message = "place cannot be null")
    @NotEmpty(message = "place cannot be empty")
    private String place;
    @NotNull(message = "incidentDate cannot be null")
    @NotEmpty(message = "incidentDate cannot be empty")
    private Instant incidentDate;
    private String address;
    @NotNull(message = "mobileNumber cannot be null")
    @NotEmpty(message = "mobileNumber cannot be empty")
    private String mobileNumber;
    @NotNull(message = "district cannot be null")
    @NotEmpty(message = "district cannot be empty")
    private String district;
    @NotNull(message = "state cannot be null")
    @NotEmpty(message = "state cannot be empty")
    private String state;
    @NotNull(message = "pincode cannot be null")
    @NotEmpty(message = "pincode cannot be empty")
    private int pincode;
}
