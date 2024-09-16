package com.example.demo.repository;

import com.example.demo.entities.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoaDonRepo extends JpaRepository<HoaDon,Long> {
}
