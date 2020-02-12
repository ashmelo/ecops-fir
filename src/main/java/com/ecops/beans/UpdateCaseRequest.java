package com.ecops.beans;

import com.ecops.domain.FirStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCaseRequest {

    @NotNull(message = "status cannot be null")
    @NotEmpty(message = "status cannot be empty")
    private FirStatus status;
    @NotNull(message = "comments cannot be null")
    @NotEmpty(message = "comments cannot be empty")
    private String comments;
}
