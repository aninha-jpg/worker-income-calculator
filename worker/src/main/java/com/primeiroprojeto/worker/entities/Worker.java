package com.primeiroprojeto.worker.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_worker")

public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private WorkerLevel workerLever;
    private Double baseSalary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    //nome do worker no hourcontract, se remover 1 contrato ele apaga no banco de dados.
    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HourContract> contracts = new ArrayList<>();

    public Worker(){

    }
    
    public Worker(String name, WorkerLevel workerLever, Double baseSalary, Department department) {
        this.name = name;
        this.workerLever = workerLever;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerLevel getWorkerLever() {
        return workerLever;
    }

    public void setWorkerLever(WorkerLevel workerLever) {
        this.workerLever = workerLever;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<HourContract> getContracts() {
        return contracts;
    }

    public void adcBaseSalary(Double contract) {
        baseSalary += contract;
    }

    public void addContract(HourContract contract){
        contracts.add(contract);
        //contrato aponta para esse trabalhador
        contract.setWorker(this);
    }

    public void removeContract(HourContract contract){
        contracts.remove(contract);
        //manter a consistencia em memória, que ele não aponta pra ninguém
        contract.setWorker(null);
    }

    public Double income(int year, int month){
        double sum = baseSalary;
        for(HourContract c: contracts){
            if(year == c.getDate().getYear() && month == c.getDate().getMonthValue()){
                sum += c.totalValue();
            }
        }
        return sum;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Worker other = (Worker) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

}
