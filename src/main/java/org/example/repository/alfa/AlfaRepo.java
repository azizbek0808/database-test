package org.example.repository.alfa;

import org.example.domain.alfa.AlfaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlfaRepo extends JpaRepository<AlfaEntity, Long> {
}
