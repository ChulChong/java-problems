package com.javaproblems.governance.dto;

import com.javaproblems.governance.domain.ProposalStatus;

import java.time.LocalDateTime;

public record ProposalResponse(
        Long id,
        String title,
        String description,
        String proposerId,
        ProposalStatus status,
        int quorum,
        double requiredApprovalRatio,
        LocalDateTime createdAt,
        LocalDateTime votingDeadline
) {
}
