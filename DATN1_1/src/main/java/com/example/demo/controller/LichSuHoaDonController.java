package com.example.demo.controller;

import com.example.demo.dtos.LichSuHoaDonResponse;
import com.example.demo.entities.HoaDon;
import com.example.demo.entities.LichSuHoaDon;
import com.example.demo.repository.HoaDonRepo;
import com.example.demo.repository.LichSuHoaDonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lichsuhoadon")
public class LichSuHoaDonController {

    @Autowired
    private LichSuHoaDonRepo lichSuHoaDonRepo;

    @Autowired
    private HoaDonRepo hoaDonRepo;


    @GetMapping("/list")
    public List<LichSuHoaDonResponse> getAllLichSuHoaDon() {
        Sort sort = Sort.by(Sort.Direction.DESC, "ngayTao");
        List<LichSuHoaDonResponse> lichSuHoaDonResponses = new ArrayList<>();
        lichSuHoaDonRepo.findAll(sort).forEach(lichSuHoaDon -> lichSuHoaDonResponses.add(lichSuHoaDon.toResponse()));
        return lichSuHoaDonResponses;
    }


    @PostMapping("/add")
    public ResponseEntity<LichSuHoaDonResponse> addLichSuHoaDon(@RequestBody LichSuHoaDon lichSuHoaDon) {
        // Lấy hóa đơn dựa trên ID của hoaDon từ đối tượng lichSuHoaDon
        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(lichSuHoaDon.getHoaDon().getId());
        if (hoaDonOptional.isPresent()) {
            HoaDon hoaDon = hoaDonOptional.get();
            lichSuHoaDon.setHoaDon(hoaDon);
            LichSuHoaDon savedLichSuHoaDon = lichSuHoaDonRepo.save(lichSuHoaDon);
            LichSuHoaDonResponse response = savedLichSuHoaDon.toResponse();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHoaDon(@PathVariable String id) {
        if (lichSuHoaDonRepo.existsById(id)) {
            lichSuHoaDonRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
