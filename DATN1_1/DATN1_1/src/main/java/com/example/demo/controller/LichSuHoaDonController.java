package com.example.demo.controller;

import com.example.demo.entities.LichSuHoaDon;
import com.example.demo.repository.LichSuHoaDonRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lichsuhoadon")
public class LichSuHoaDonController {

    @Autowired
    private LichSuHoaDonRepo lichSuHoaDonRepo;

    // Tìm kiếm LichSuHoaDon theo tên khách hàng
    @GetMapping("/search")
    public ResponseEntity<List<LichSuHoaDon>> searchLichSuHoaDon(@RequestParam("ngayTao") String NgayTao) {
        List<LichSuHoaDon> results = lichSuHoaDonRepo.findByNgayTaoContainingIgnoreCase(NgayTao);
        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(results);
        }
    }

    // Tạo mới
    @PostMapping("/create")
    public ResponseEntity<LichSuHoaDon> createLichSuHoaDon(@RequestBody LichSuHoaDon lichSuHoaDon) {
        LichSuHoaDon savedLichSuHoaDon = lichSuHoaDonRepo.save(lichSuHoaDon);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLichSuHoaDon);
    }

    // Lấy một LichSuHoaDon theo ID
    @GetMapping("/get/{id}")
    public ResponseEntity<LichSuHoaDon> getLichSuHoaDonById(@PathVariable String id) {
        Optional<LichSuHoaDon> lichSuHoaDonOptional = lichSuHoaDonRepo.findById(id);
        if (lichSuHoaDonOptional.isPresent()) {
            return ResponseEntity.ok(lichSuHoaDonOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Lấy danh sách LichSuHoaDon với phân trang
    @GetMapping("/list")
    public ResponseEntity<Page<LichSuHoaDon>> getAllLichSuHoaDon(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<LichSuHoaDon> lichSuHoaDonPage = lichSuHoaDonRepo.findAll(pageable);
        return ResponseEntity.ok(lichSuHoaDonPage);
    }
}
