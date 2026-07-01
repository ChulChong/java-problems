package com.javaproblems.governance.service;

import com.javaproblems.governance.domain.Proposal;
import com.javaproblems.governance.domain.ProposalStatus;
import com.javaproblems.governance.dto.OpenProposalRequest;
import com.javaproblems.governance.dto.ProposalCreateRequest;
import com.javaproblems.governance.dto.ProposalResponse;
import com.javaproblems.governance.dto.VoteRequest;
import com.javaproblems.governance.dto.VoteResultResponse;
import com.javaproblems.governance.repository.ProposalRepository;
import com.javaproblems.governance.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final VoteRepository voteRepository;

    public ProposalServiceImpl(ProposalRepository proposalRepository, VoteRepository voteRepository) {
        this.proposalRepository = proposalRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public ProposalResponse createProposal(ProposalCreateRequest request) {
        Proposal proposal = new Proposal(request.title(), request.description(), request.proposerId(), request.quorum(), request.requiredApprovalRatio());
        Proposal savedProposal = proposalRepository.save(proposal);
        return new ProposalResponse(savedProposal.getId(), savedProposal.getTitle(),
                savedProposal.getDescription(), savedProposal.getProposerId(), savedProposal.getStatus(),
                savedProposal.getQuorum(), savedProposal.getRequiredApprovalRatio(), savedProposal.getCreatedAt(),
                savedProposal.getVotingDeadline());
    }

    @Override
    public List<ProposalResponse> getProposals(ProposalStatus status) {
        // TODO: return all proposals, optionally filtered by status.
        throw new UnsupportedOperationException("TODO: implement getProposals");
    }

    @Override
    public ProposalResponse getProposal(Long proposalId) {
        // TODO: fetch a single proposal or throw ProposalNotFoundException.
        throw new UnsupportedOperationException("TODO: implement getProposal");
    }

    @Override
    public ProposalResponse openProposal(Long proposalId, OpenProposalRequest request) {
        // TODO: transition DRAFT -> OPEN and set the voting deadline.
        // Reject if the proposal is not in DRAFT state (InvalidProposalStateException).
        throw new UnsupportedOperationException("TODO: implement openProposal");
    }

    @Override
    public void castVote(Long proposalId, VoteRequest request) {
        // TODO: validate proposal is OPEN and the deadline has not passed.
        // Reject duplicate votes from the same voterId (DuplicateVoteException).
        throw new UnsupportedOperationException("TODO: implement castVote");
    }

    @Override
    public VoteResultResponse closeProposal(Long proposalId) {
        // TODO: tally votes and transition the proposal to APPROVED or REJECTED
        // based on quorum and requiredApprovalRatio. See README for the exact rule.
        throw new UnsupportedOperationException("TODO: implement closeProposal");
    }

    @Override
    public VoteResultResponse getResults(Long proposalId) {
        // TODO: return the current vote tally for a proposal (works before and after closing).
        throw new UnsupportedOperationException("TODO: implement getResults");
    }
}
