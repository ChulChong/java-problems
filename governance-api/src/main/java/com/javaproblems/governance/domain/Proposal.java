package com.javaproblems.governance.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String proposerId;

    @Enumerated(EnumType.STRING)
    private ProposalStatus status;

    /** Minimum number of votes required for the result to be valid. */
    private int quorum;

    /** Minimum approve ratio (0.0 ~ 1.0, abstain votes excluded) required to be APPROVED. */
    private double requiredApprovalRatio;

    private LocalDateTime createdAt;

    private LocalDateTime votingDeadline;

    protected Proposal() {
        // JPA
    }

    public Proposal(String title, String description, String proposerId, int quorum, double requiredApprovalRatio) {
        this.title = title;
        this.description = description;
        this.proposerId = proposerId;
        this.quorum = quorum;
        this.requiredApprovalRatio = requiredApprovalRatio;
        this.status = ProposalStatus.DRAFT;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getProposerId() {
        return proposerId;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    public int getQuorum() {
        return quorum;
    }

    public double getRequiredApprovalRatio() {
        return requiredApprovalRatio;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getVotingDeadline() {
        return votingDeadline;
    }

    public void setVotingDeadline(LocalDateTime votingDeadline) {
        this.votingDeadline = votingDeadline;
    }
}
