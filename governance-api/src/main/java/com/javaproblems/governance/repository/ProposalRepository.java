package com.javaproblems.governance.repository;

import com.javaproblems.governance.domain.Proposal;
import com.javaproblems.governance.domain.ProposalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    List<Proposal> findByStatus(ProposalStatus status);
}
