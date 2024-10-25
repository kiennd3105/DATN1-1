package com.example.demo.controller;

import com.example.demo.dtos.ChiTietHoaDonRep;
import com.example.demo.dtos.ChiTietHoaDonReq;
import com.example.demo.entities.ChiTietHoaDon;
import com.example.demo.entities.ChiTietSanPham;
import com.example.demo.entities.HoaDon;
import com.example.demo.entities.LichSuHoaDon;
import com.example.demo.repository.ChiTietHoaDonRepo;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.HoaDonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chitiethoadon")
public class ChiTietHoaDonController {

    @Autowired
    private ChiTietHoaDonRepo chiTietHoaDonRepo;
    @Autowired
    private HoaDonRepo hoaDonRepo;



    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepo; // Đảm bảo bạn đã khai báo repository này
    @PostMapping("/add")
    public ResponseEntity<String> createChiTietHoaDon(@Validated @RequestBody ChiTietHoaDonReq req) {

        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(req.getIdHD());
        if (!hoaDonOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Hóa đơn không tồn tại."); // Trả về lỗi nếu hóa đơn không tồn tại
        }


        Optional<ChiTietSanPham> chiTietSanPhamOptional = chiTietSanPhamRepo.findById(req.getIdCTSP());
        if (!chiTietSanPhamOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Chi tiết sản phẩm không tồn tại."); // Trả về lỗi nếu chi tiết sản phẩm không tồn tại
        }

        // Tạo đối tượng ChiTietHoaDon
        ChiTietHoaDon chiTietHoaDons = new ChiTietHoaDon();
        chiTietHoaDons.setMaCTHD(req.getMaCTHD());
        chiTietHoaDons.setTongTien(req.getTongTien());
        chiTietHoaDons.setSoLuong(req.getSoLuong());
        chiTietHoaDons.setGiaBan(req.getGiaBan());
        chiTietHoaDons.setTrangThai(req.getTrangThai());
        chiTietHoaDons.setNgayTao(LocalDateTime.now());
        chiTietHoaDons.setGhiChu(req.getGhiChu());
        chiTietHoaDons.setHoaDon(hoaDonOptional.get());
        chiTietHoaDons.setChiTietSanPham(chiTietSanPhamOptional.get());

        // Lưu đối tượng vào cơ sở dữ liệu
        chiTietHoaDonRepo.save(chiTietHoaDons);
        return ResponseEntity.ok("Thêm chi tiết hóa đơn thành công.");
    }



    @GetMapping("/list")
    public ResponseEntity<List<ChiTietHoaDonRep>> getAllChiTietHoaDon() {
        List<ChiTietHoaDonRep> chiTietHoaDons = chiTietHoaDonRepo.findAll().stream()
                .map(ChiTietHoaDon::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(chiTietHoaDons);
    }


    // Update
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateChiTietHoaDon(@PathVariable String id, @Validated @RequestBody ChiTietHoaDonReq req) {
        // Validate ID
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Kiểm tra sự tồn tại của ChiTietHoaDon
        Optional<ChiTietHoaDon> chiTietHoaDonOptional = chiTietHoaDonRepo.findById(id);
        if (chiTietHoaDonOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ChiTietHoaDon chiTietHoaDon = chiTietHoaDonOptional.get();

        // Cập nhật các trường
        chiTietHoaDon.setMaCTHD(req.getMaCTHD());
        chiTietHoaDon.setTongTien(req.getTongTien());
        chiTietHoaDon.setSoLuong(req.getSoLuong());
        chiTietHoaDon.setGiaBan(req.getGiaBan());
        chiTietHoaDon.setTrangThai(req.getTrangThai());
        chiTietHoaDon.setNgaySua(LocalDateTime.now());
        chiTietHoaDon.setGhiChu(req.getGhiChu());
        System.out.println("Updating ChiTietHoaDon with ID: " + chiTietHoaDon.getId());
        System.out.println("Updated Fields: ");
        System.out.println(" - MaCTHD: " + chiTietHoaDon.getMaCTHD());
        System.out.println(" - TongTien: " + chiTietHoaDon.getTongTien());
        System.out.println(" - SoLuong: " + chiTietHoaDon.getSoLuong());
        System.out.println(" - GiaBan: " + chiTietHoaDon.getGiaBan());
        System.out.println(" - TrangThai: " + chiTietHoaDon.getTrangThai());
        System.out.println(" - GhiChu: " + chiTietHoaDon.getGhiChu());

        // Validate new ChiTietSanPham ID
        if (req.getIdCTSP() == null || req.getIdCTSP().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<ChiTietSanPham> chiTietSanPhamOptional = chiTietSanPhamRepo.findById(req.getIdCTSP());
        if (chiTietSanPhamOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        chiTietHoaDon.setChiTietSanPham(chiTietSanPhamOptional.get());

        // Validate new HoaDon ID
        if (req.getIdHD() == null || req.getIdHD().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(req.getIdHD());
        if (hoaDonOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        chiTietHoaDon.setHoaDon(hoaDonOptional.get());

        // Lưu đối tượng đã cập nhật
        chiTietHoaDonRepo.save(chiTietHoaDon);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/list/{idHD}")
    public ResponseEntity<List<ChiTietHoaDonRep>> getChiTietHoaDonByIdHD(@PathVariable String idHD) {
        List<ChiTietHoaDon> chiTietList = chiTietHoaDonRepo.findByHoaDon_Id(idHD);
        if (chiTietList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ChiTietHoaDonRep> responseList = chiTietList.stream()
                .map(ChiTietHoaDon::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteChiTietHoaDon(@PathVariable String id) {
        if (chiTietHoaDonRepo.existsById(id)) {
            chiTietHoaDonRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu không tìm thấy
        }
    }
}
