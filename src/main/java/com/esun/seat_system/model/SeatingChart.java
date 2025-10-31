package com.esun.seat_system.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "seating_chart")
public class SeatingChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "floor_seat_seq")
    private Long floorSeatSeq;

    @Column(name = "floor_no", nullable = false)
    private Integer floorNo;

    @Column(name = "seat_no", nullable = false, length = 10)
    private String seatNo;

    @OneToOne(mappedBy = "seatingChart", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Employee employee;

    public SeatingChart() {}

    public SeatingChart(Integer floorNo, String seatNo) {
        this.floorNo = floorNo;
        this.seatNo = seatNo;
    }

    public Long getFloorSeatSeq() { return floorSeatSeq; }
    public void setFloorSeatSeq(Long floorSeatSeq) { this.floorSeatSeq = floorSeatSeq; }

    public Integer getFloorNo() { return floorNo; }
    public void setFloorNo(Integer floorNo) { this.floorNo = floorNo; }

    public String getSeatNo() { return seatNo; }
    public void setSeatNo(String seatNo) { this.seatNo = seatNo; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}
