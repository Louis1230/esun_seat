package com.esun.seat_system.controller;

import com.esun.seat_system.model.Employee;
import com.esun.seat_system.model.SeatingChart;
import com.esun.seat_system.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seats")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public List<SeatingChart> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return seatService.getAllEmployees();
    }

    @PostMapping("/assign")
    public Map<String, Object> assignSeat(@RequestBody Map<String, Object> payload) {
        String empId = (String) payload.get("empId");
        Integer floorSeatSeq = (Integer) payload.get("floorSeatSeq");
        boolean success = seatService.assignSeat(empId, floorSeatSeq);
        return Map.of("success", success);
    }

    @PostMapping("/clear")
    public Map<String, Object> clearSeat(@RequestBody Map<String, Object> payload) {
        Integer floorSeatSeq = (Integer) payload.get("floorSeatSeq");
        boolean success = seatService.clearSeat(floorSeatSeq);
        return Map.of("success", success);
    }
}
