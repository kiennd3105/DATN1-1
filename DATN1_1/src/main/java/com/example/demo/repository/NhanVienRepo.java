package com.example.demo.repository;

import com.example.demo.entities.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NhanVienRepo extends JpaRepository<NhanVien,String> {
    Optional<NhanVien> findByTenNV(String tenNV);

}
