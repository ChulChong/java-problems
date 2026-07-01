package com.javaproblems.governance.exception;

public class DuplicateVoteException extends RuntimeException {

    public DuplicateVoteException(Long proposalId, String voterId) {
        super("Voter " + voterId + " has already voted on proposal " + proposalId);
    }
}
