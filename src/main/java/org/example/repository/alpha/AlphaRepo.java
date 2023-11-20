package org.example.repository.alpha;

import org.example.domain.alpha.AlphaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlphaRepo extends JpaRepository<AlphaEntity, Long> {
}
