package com.softz.identity.repository;

import com.softz.identity.entity.Permission;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    List<Permission> findByIdIn(Collection<Integer> ids);

    Stream<Permission> findByName(String name);
}
