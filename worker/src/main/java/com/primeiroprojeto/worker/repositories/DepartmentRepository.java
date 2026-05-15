package com.primeiroprojeto.worker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.primeiroprojeto.worker.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
    
}
