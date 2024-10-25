package com.example.demo.repository;

import com.example.demo.entities.ChiTietHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChiTietHoaDonRepo extends JpaRepository<ChiTietHoaDon,String> {
    List<ChiTietHoaDon> findByHoaDon_Id(String idHD);
}
