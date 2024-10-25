package com.example.demo.controller;


import com.example.demo.dtos.ChiTietSanPhamRequest;
import com.example.demo.dtos.ChiTietSanPhamResponse;
import com.example.demo.entities.ChiTietSanPham;
import com.example.demo.entities.GiamGia;
import com.example.demo.entities.SanPham;

import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.SanPhamRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("chi-tiet-san-pham")
public class ChiTietSanPhamController {
    @Autowired
    SanPhamRepository sanPhamRepository;
    //    @Autowired
    //    GiamGiaRepository giamGiaRepository;
    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;


        @GetMapping()
        public ResponseEntity<?> getAll() {
            Sort sort = Sort.by(Sort.Direction.DESC, "ngayTao");
            List<ChiTietSanPham> chiTietSanPhams = chiTietSanPhamRepository.findAll(sort);

            if (chiTietSanPhams.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            List<ChiTietSanPhamResponse> responseList = chiTietSanPhams.stream()
                    .map(ChiTietSanPham::toChiTietSanPhamResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(responseList);
        }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateChiTietSanPham(@PathVariable String id, @Valid @RequestBody ChiTietSanPhamRequest request) {
        // Tìm sản phẩm chi tiết bằng ID
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(id).orElse(null);
        if (chiTietSanPham == null) {
            return ResponseEntity.notFound().build();
        }

        // Cập nhật thông tin
        BeanUtils.copyProperties(request, chiTietSanPham, "id", "ngayTao");
        chiTietSanPham.setNgaySua(LocalDateTime.now());
        // Lưu thay đổi
        chiTietSanPhamRepository.save(chiTietSanPham);

        return ResponseEntity.ok(chiTietSanPham.toChiTietSanPhamResponse());
    }


}