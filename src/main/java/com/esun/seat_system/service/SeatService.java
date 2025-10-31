package com.esun.seat_system.service;

import com.esun.seat_system.model.Employee;
import com.esun.seat_system.model.SeatingChart;
import com.esun.seat_system.repository.EmployeeRepository;
import com.esun.seat_system.repository.SeatingChartRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class SeatService {

    private final JdbcTemplate jdbcTemplate;
    private final SeatingChartRepository seatingChartRepository;
    private final EmployeeRepository employeeRepository;

    public SeatService(JdbcTemplate jdbcTemplate,
                       SeatingChartRepository seatingChartRepository,
                       EmployeeRepository employeeRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.seatingChartRepository = seatingChartRepository;
        this.employeeRepository = employeeRepository;
    }

    // 取得所有座位
    public List<SeatingChart> getAllSeats() {
        return seatingChartRepository.findAll();
    }

    // 取得所有員工
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // 指派座位：呼叫 stored procedure
    public boolean assignSeat(String empId, Integer seatSeq) {
        return jdbcTemplate.execute((Connection con) -> {
            try (CallableStatement cs = con.prepareCall("{call sp_assignSeat(?, ?)}")) {
                cs.setString(1, empId);
                cs.setInt(2, seatSeq);
                cs.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    // 清空座位（根據座位序號）
    public boolean clearSeat(Integer seatSeq) {
        return jdbcTemplate.execute((Connection con) -> {
            try (CallableStatement cs = con.prepareCall("{call sp_clearSeat(?)}")) {
                cs.setInt(1, seatSeq);
                cs.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }
}
