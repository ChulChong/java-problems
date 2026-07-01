package com.javaproblems.governance.service;

import com.javaproblems.governance.domain.ProposalStatus;
import com.javaproblems.governance.dto.OpenProposalRequest;
import com.javaproblems.governance.dto.ProposalCreateRequest;
import com.javaproblems.governance.dto.ProposalResponse;
import com.javaproblems.governance.dto.VoteRequest;
import com.javaproblems.governance.dto.VoteResultResponse;

import java.util.List;

public interface ProposalService {

    ProposalResponse createProposal(ProposalCreateRequest request);

    List<ProposalResponse> getProposals(ProposalStatus status);

    ProposalResponse getProposal(Long proposalId);

    ProposalResponse openProposal(Long proposalId, OpenProposalRequest request);

    void castVote(Long proposalId, VoteRequest request);

    VoteResultResponse closeProposal(Long proposalId);

    VoteResultResponse getResults(Long proposalId);
}
