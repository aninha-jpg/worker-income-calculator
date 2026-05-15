package com.primeiroprojeto.worker.dto;
// api endpoint - data transfer object
public record WorkerIncomeDTO (
    String name,
    String department,
    Double income){
}
