package com.javaproblems.governance.dto;

import com.javaproblems.governance.domain.ProposalStatus;

public record VoteResultResponse(
        Long proposalId,
        ProposalStatus status,
        long approveCount,
        long rejectCount,
        long abstainCount,
        long totalVotes
) {
}
