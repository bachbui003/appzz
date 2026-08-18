package com.appzz.helpdesk.repository;

import com.appzz.helpdesk.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
