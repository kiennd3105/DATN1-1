package com.example.demo.repository;

import com.example.demo.entities.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KhachHangRepo extends JpaRepository<KhachHang,String> {
    Optional<KhachHang> findByTenKH(String tenKH);
}
