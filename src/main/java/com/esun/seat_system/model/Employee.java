package com.esun.seat_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "emp_id")
    private String empId;

    private String name;
    private String email;

    @OneToOne
    @JoinColumn(name = "floor_seat_seq")
    @JsonBackReference
    private SeatingChart seatingChart;

    // 建構子、Getter、Setter
    public Employee() {}

    public Employee(String empId, String name, String email) {
        this.empId = empId;
        this.name = name;
        this.email = email;
    }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public SeatingChart getSeatingChart() { return seatingChart; }
    public void setSeatingChart(SeatingChart seatingChart) { this.seatingChart = seatingChart; }
}
