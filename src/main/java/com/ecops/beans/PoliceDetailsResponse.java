package com.ecops.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PoliceDetailsResponse {

    private int id;
    private String loginId;
    private String password;
    private String contact;
}
