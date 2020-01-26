package com.ecops.beans;

import com.ecops.domain.CrimeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllFirResponse {

    private int firId;
    private CrimeStatus crimeType;
    private Instant filedDate;
    private Instant incidentDate;
}
