package com.ecops.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int firId;
    private String userId;

    @Enumerated(EnumType.STRING)
    private CrimeStatus crimeCommit;
    private String description;
    private String place;
    private Instant incidentDate;
    private String address;
    private Instant filedDate;
    private String mobileNumber;
    private int policeStationId;
    private String stationName;
    private String stationContact;
    private int policeOfficerId;
    private String policeOfficerName;
    private String district;
    private String state;
    private int pincode;

    @Enumerated(EnumType.STRING)
    private FirStatus status;
    private String comments;
}
