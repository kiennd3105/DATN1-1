package com.example.demo.repository;

import com.example.demo.entities.LichSuHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LichSuHoaDonRepo extends JpaRepository<LichSuHoaDon,String> {
    List<LichSuHoaDon> findByNgayTaoContainingIgnoreCase(String ngayTao);
}
