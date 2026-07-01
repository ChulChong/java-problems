package com.javaproblems.governance.controller;

import com.javaproblems.governance.domain.ProposalStatus;
import com.javaproblems.governance.dto.OpenProposalRequest;
import com.javaproblems.governance.dto.ProposalCreateRequest;
import com.javaproblems.governance.dto.ProposalResponse;
import com.javaproblems.governance.dto.VoteRequest;
import com.javaproblems.governance.dto.VoteResultResponse;
import com.javaproblems.governance.service.ProposalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/proposals")
public class ProposalController {

    private final ProposalService proposalService;

    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProposalResponse createProposal(@Valid @RequestBody ProposalCreateRequest request) {
        return proposalService.createProposal(request);
    }

    @GetMapping
    public List<ProposalResponse> getProposals(@RequestParam(required = false) ProposalStatus status) {
        return proposalService.getProposals(status);
    }

    @GetMapping("/{proposalId}")
    public ProposalResponse getProposal(@PathVariable Long proposalId) {
        return proposalService.getProposal(proposalId);
    }

    @PostMapping("/{proposalId}/open")
    public ProposalResponse openProposal(@PathVariable Long proposalId,
                                          @Valid @RequestBody OpenProposalRequest request) {
        return proposalService.openProposal(proposalId, request);
    }

    @PostMapping("/{proposalId}/votes")
    @ResponseStatus(HttpStatus.CREATED)
    public void castVote(@PathVariable Long proposalId, @Valid @RequestBody VoteRequest request) {
        proposalService.castVote(proposalId, request);
    }

    @PostMapping("/{proposalId}/close")
    public VoteResultResponse closeProposal(@PathVariable Long proposalId) {
        return proposalService.closeProposal(proposalId);
    }

    @GetMapping("/{proposalId}/results")
    public VoteResultResponse getResults(@PathVariable Long proposalId) {
        return proposalService.getResults(proposalId);
    }
}
