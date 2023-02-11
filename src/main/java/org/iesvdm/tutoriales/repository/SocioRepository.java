package org.iesvdm.tutoriales.repository;

import org.iesvdm.tutoriales.domain.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {
}
