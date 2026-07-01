package com.javaproblems.governance.exception;

public class ProposalNotFoundException extends RuntimeException {

    public ProposalNotFoundException(Long proposalId) {
        super("Proposal not found: " + proposalId);
    }
}
