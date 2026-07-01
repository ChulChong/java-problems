package com.javaproblems.governance.dto;

import com.javaproblems.governance.domain.VoteChoice;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VoteRequest(
        @NotBlank String voterId,
        @NotNull VoteChoice choice
) {
}
