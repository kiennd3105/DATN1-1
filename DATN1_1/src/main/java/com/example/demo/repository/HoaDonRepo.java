package com.example.demo.repository;

import com.example.demo.entities.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HoaDonRepo extends JpaRepository<HoaDon,String> {
    Optional<HoaDon> findByMaHD(String maHD);
}
