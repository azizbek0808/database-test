package org.example.repository.beta;

import org.example.domain.beta.BetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetaRepo extends JpaRepository<BetaEntity, Long> {
}
