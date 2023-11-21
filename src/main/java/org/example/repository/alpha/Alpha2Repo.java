package org.example.repository.alpha;

import org.example.domain.alpha.Alpha2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Alpha2Repo extends JpaRepository<Alpha2Entity, Long> {
}
