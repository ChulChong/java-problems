package com.javaproblems.governance.service;

import com.javaproblems.governance.domain.Proposal;
import com.javaproblems.governance.domain.ProposalStatus;
import com.javaproblems.governance.dto.OpenProposalRequest;
import com.javaproblems.governance.dto.ProposalCreateRequest;
import com.javaproblems.governance.dto.ProposalResponse;
import com.javaproblems.governance.dto.VoteRequest;
import com.javaproblems.governance.dto.VoteResultResponse;
import com.javaproblems.governance.exception.InvalidProposalStateException;
import com.javaproblems.governance.exception.ProposalNotFoundException;
import com.javaproblems.governance.repository.ProposalRepository;
import com.javaproblems.governance.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final VoteRepository voteRepository;

    public ProposalServiceImpl(ProposalRepository proposalRepository, VoteRepository voteRepository) {
        this.proposalRepository = proposalRepository;
        this.voteRepository = voteRepository;
    }

    private ProposalResponse toResponse(Proposal proposal) {
        return new ProposalResponse(proposal.getId(), proposal.getTitle(),
                proposal.getDescription(), proposal.getProposerId(), proposal.getStatus(),
                proposal.getQuorum(), proposal.getRequiredApprovalRatio(), proposal.getCreatedAt(),
                proposal.getVotingDeadline());
    }

    @Override
    public ProposalResponse createProposal(ProposalCreateRequest request) {
        Proposal proposal = new Proposal(request.title(), request.description(), request.proposerId(), request.quorum(), request.requiredApprovalRatio());
        Proposal savedProposal = proposalRepository.save(proposal);
        return toResponse(savedProposal);
    }

    @Override
    public List<ProposalResponse> getProposals(ProposalStatus status) {
        List<Proposal> proposals;
        if (status == null) {
            proposals = proposalRepository.findAll();
        } else {
            proposals = proposalRepository.findByStatus(status);
        }
        return proposals.stream().map(this::toResponse).toList();
    }

    @Override
    public ProposalResponse getProposal(Long proposalId) {
        Optional<Proposal> optionalProposal = proposalRepository.findById(proposalId);
        Proposal p = optionalProposal.orElseThrow(() -> new ProposalNotFoundException(proposalId));
        return toResponse(p);
    }

    @Override
    public ProposalResponse openProposal(Long proposalId, OpenProposalRequest request) {
        Optional<Proposal> optionalProposal = proposalRepository.findById(proposalId);
        Proposal p = optionalProposal.orElseThrow(() -> new ProposalNotFoundException(proposalId));
        if (p.getStatus() != ProposalStatus.DRAFT) {
            throw new InvalidProposalStateException("Status is not Draft");
        }
        p.setStatus(ProposalStatus.OPEN);
        p.setVotingDeadline(request.votingDeadline());
        Proposal savedProposal = proposalRepository.save(p);
        return toResponse(savedProposal);
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
