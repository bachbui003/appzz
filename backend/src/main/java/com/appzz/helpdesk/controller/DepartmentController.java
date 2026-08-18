package com.appzz.helpdesk.controller;

import com.appzz.helpdesk.model.Department;
import com.appzz.helpdesk.repository.DepartmentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentRepository departments;
    public DepartmentController(DepartmentRepository departments) { this.departments = departments; }
    @GetMapping public List<Department> listDepartments() { return departments.findAll(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Department createDepartment(@Valid @RequestBody Department department) { return departments.save(department); }
}
