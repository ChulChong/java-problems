package com.javaproblems.governance.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long proposalId;

    private String voterId;

    @Enumerated(EnumType.STRING)
    private VoteChoice choice;

    private LocalDateTime votedAt;

    protected Vote() {
        // JPA
    }

    public Vote(Long proposalId, String voterId, VoteChoice choice) {
        this.proposalId = proposalId;
        this.voterId = voterId;
        this.choice = choice;
        this.votedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public String getVoterId() {
        return voterId;
    }

    public VoteChoice getChoice() {
        return choice;
    }

    public LocalDateTime getVotedAt() {
        return votedAt;
    }
}
