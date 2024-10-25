package com.example.demo.repository;

import com.example.demo.entities.HoaDon;
import com.example.demo.entities.LichSuHoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface LichSuHoaDonRepo extends JpaRepository<LichSuHoaDon, String> {

}
