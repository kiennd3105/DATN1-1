package com.example.demo.repository;

import com.example.demo.entities.ChiTietSanPham;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham,String> {


}
