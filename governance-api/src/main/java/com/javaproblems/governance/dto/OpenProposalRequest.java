package com.javaproblems.governance.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record OpenProposalRequest(
        @NotNull @Future LocalDateTime votingDeadline
) {
}
