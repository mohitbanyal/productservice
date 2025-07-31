package com.mb.springcloud.productservice.repo;

import com.mb.springcloud.productservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
