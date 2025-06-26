package com.GerenciamentoHP.Repository;

import com.GerenciamentoHP.Model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByDescription(String description);
}
