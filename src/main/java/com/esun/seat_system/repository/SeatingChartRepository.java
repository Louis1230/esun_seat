package com.esun.seat_system.repository;

import com.esun.seat_system.model.SeatingChart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatingChartRepository extends JpaRepository<SeatingChart, Integer> {
}
