package com.primeiroprojeto.worker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.primeiroprojeto.worker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>{
    
}
