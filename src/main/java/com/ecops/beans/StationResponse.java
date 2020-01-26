package com.ecops.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StationResponse {
    private int stationId;
    private String stationName;
    private String pincode;
    private String address;
    private String place;
    private String stationContact;
}
