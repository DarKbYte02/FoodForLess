package com.ipn.mx.domain.Repository;

import com.ipn.mx.domain.Entity.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Long> {
}
