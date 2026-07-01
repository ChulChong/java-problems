package com.javaproblems.governance.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProposalCreateRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String proposerId,
        @Min(1) int quorum,
        @DecimalMin("0.0") @DecimalMax("1.0") double requiredApprovalRatio
) {
}
