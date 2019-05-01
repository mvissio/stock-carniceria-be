package com.svcg.StockCustom.repository;

import com.svcg.StockCustom.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Serializable> {

    Optional<Rol> findRolByRolId(Long rolId);

    Optional<Rol> findRolByNombre(String nombre);
}
