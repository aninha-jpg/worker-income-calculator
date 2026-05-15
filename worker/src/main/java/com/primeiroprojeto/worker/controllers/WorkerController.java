package com.primeiroprojeto.worker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.primeiroprojeto.worker.dto.WorkerIncomeDTO;
import com.primeiroprojeto.worker.entities.Worker;
import com.primeiroprojeto.worker.repositories.WorkerRepository;


@RestController
@RequestMapping(value = "/worker")
public class WorkerController {


    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping("/{id}/income/{year}/{month}")
    public WorkerIncomeDTO getIncome(@PathVariable Long id,
        @PathVariable Integer year, 
        @PathVariable Integer month) 
        {

        Worker worker = workerRepository.findById(id).get();

        return new WorkerIncomeDTO(
        worker.getName(),
        worker.getDepartment().getName(),
        worker.income(year, month)
        );
    }
    
}
