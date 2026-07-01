package com.javaproblems.governance.repository;

import com.javaproblems.governance.domain.Vote;
import com.javaproblems.governance.domain.VoteChoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByProposalId(Long proposalId);

    Optional<Vote> findByProposalIdAndVoterId(Long proposalId, String voterId);

    long countByProposalIdAndChoice(Long proposalId, VoteChoice choice);
}
