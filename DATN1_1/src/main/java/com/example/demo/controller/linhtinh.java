//package com.example.demo.controller;
//
//import com.example.demo.dtos.ChiTietHoaDonRep;
//import com.example.demo.dtos.HoaDonRep;
//import com.example.demo.dtos.HoaDonReq;
//import com.example.demo.entities.ChiTietHoaDon;
//import com.example.demo.entities.HoaDon;
//import com.example.demo.entities.KhachHang;
//import com.example.demo.entities.NhanVien;
//import com.example.demo.repository.ChiTietHoaDonRepo;
//import com.example.demo.repository.HoaDonRepo;
//import com.example.demo.repository.KhachHangRepo;
//import com.example.demo.repository.NhanVienRepo;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/banhang")
//public class BanHangController {
//
//    @Autowired
//    private HoaDonRepo hoaDonRepo;
//
//    @Autowired
//    private ChiTietHoaDonRepo chiTietHoaDonRepo;
//    @Autowired
//    private NhanVienRepo nhanVienRepo;
//    @Autowired
//    private KhachHangRepo khachHangRepo;
//
//    // Read all
//    @PostMapping("/add")
//    public ResponseEntity<?> createHoaDon(@RequestBody HoaDonReq req) {
//
//
//        String defaultIdNV = "68639AA2";
//
//        Optional<NhanVien> nhanVienOptional = nhanVienRepo.findById(defaultIdNV);
//        if (!nhanVienOptional.isPresent()) {
//            return ResponseEntity.badRequest().body("Không tìm thấy nhân viên mặc định với ID: " + defaultIdNV);
//        }
//
//        HoaDon hoaDon = new HoaDon();
//
//        BeanUtils.copyProperties(req, hoaDon);
//
//        LocalDateTime now = LocalDateTime.now();
//        hoaDon.setNgayTao(now);
//        hoaDon.setNgaySua(now);
//
//        // Đặt trạng thái hóa đơn mặc định là 1
//        hoaDon.setTrangThai(1);
//
//        // Đặt các trường khác là null
//        hoaDon.setLoaiHD(req.getLoaiHD()); // Thiết lập loại hóa đơn nếu cần
//        hoaDon.setMaVoucher(null);
//        hoaDon.setNgayThanhToan(null);
//        hoaDon.setNgayNhanHang(null);
//        hoaDon.setThanhTien(null);
//        hoaDon.setPhiVanChuyen("30000"); // Nếu cần giá trị mặc định
//        hoaDon.setThongTinGiaoHang("Vận chuyển nhanh");
//        hoaDon.setKhachHang(null);
//        hoaDon.setNhanVien(nhanVienOptional.get());
//        System.out.println("Mã hóa đơn: " + hoaDon.getMaHD());
//        System.out.println("Ngày tạo: " + hoaDon.getNgayTao());
//        System.out.println("Ngày sửa: " + hoaDon.getNgaySua());
//        System.out.println("Ngày thanh toán: " + hoaDon.getNgayThanhToan());
//        System.out.println("Ngày nhận hàng: " + hoaDon.getNgayNhanHang());
//        System.out.println("Mã voucher: " + hoaDon.getMaVoucher());
//        System.out.println("Thành tiền: " + hoaDon.getThanhTien());
//        System.out.println("Phí vận chuyển: " + hoaDon.getPhiVanChuyen());
//        System.out.println("Thông tin giao hàng: " + hoaDon.getThongTinGiaoHang());
//        System.out.println("Trạng thái: " + hoaDon.getTrangThai());
//
//        // Lưu hóa đơn
//        try {
//            hoaDonRepo.save(hoaDon);
//            return ResponseEntity.ok().body(Map.of("message", "Thêm hóa đơn thành công!", "hoaDon", hoaDon));
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("error", "Có lỗi xảy ra khi lưu hóa đơn: " + e.getMessage()));
//        }
//    }
//
//
//    @GetMapping("/list")
//    public ResponseEntity<List<HoaDonRep>> getAllHoaDon() {
//        Sort sort = Sort.by(Sort.Direction.DESC, "ngayTao"); // Sắp xếp theo ngày tạo giảm dần
//        List<HoaDon> hoaDonList = hoaDonRepo.findAll(sort); // Lấy danh sách hóa đơn đã sắp xếp
//
//        // Chuyển đổi danh sách hóa đơn thành danh sách DTO bằng cách sử dụng toResponse
//        List<HoaDonRep> hoaDons = hoaDonList.stream()
//                .map(hoaDon -> hoaDon.toResponse(List.of())) // Truyền danh sách chi tiết hóa đơn rỗng hoặc thực tế nếu có
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(hoaDons); // Trả về danh sách DTO
//    }
//    // Read by ID
//    @GetMapping("/detail/{idHD}")
//    public ResponseEntity<HoaDonRep> getHoaDonById(@PathVariable String idHD) {
//        return hoaDonRepo.findById(idHD).map(hoaDon -> {
//            List<ChiTietHoaDon> chiTietHoaDons = chiTietHoaDonRepo.findByHoaDon_Id(idHD);
//
//            List<ChiTietHoaDonRep> chiTietRepList = chiTietHoaDons.stream().map(chiTiet -> {
//                ChiTietHoaDonRep chiTietRep = new ChiTietHoaDonRep();
//                chiTietRep.setId(chiTiet.getId());
//                chiTietRep.setMaCTHD(chiTiet.getMaCTHD());
//                chiTietRep.setTongTien(chiTiet.getTongTien());
//                chiTietRep.setSoLuong(chiTiet.getSoLuong());
//                chiTietRep.setGiaBan(chiTiet.getGiaBan());
//                chiTietRep.setTrangThai(chiTiet.getTrangThai());
//                chiTietRep.setGhiChu(chiTiet.getGhiChu());
//                return chiTietRep;
//            }).collect(Collectors.toList());
//
//
//            HoaDonRep rep = hoaDon.toResponse(chiTietRepList);
//            return ResponseEntity.ok(rep);
//        }).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    // Update
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Void> updateHoaDon(@PathVariable String id, @Validated @RequestBody HoaDonReq req) {
//        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(id);
//        if (hoaDonOptional.isPresent()) {
//            HoaDon hoaDon = hoaDonOptional.get();
//
//            // Cập nhật các thông tin khác của hóa đơn
//            hoaDon.setMaHD(req.getMaHD());
//            hoaDon.setMaVoucher(req.getMaVoucher());
//            hoaDon.setThanhTien(req.getThanhTien());
//            hoaDon.setNgayThanhToan(req.getNgayThanhToan());
//            hoaDon.setNgayNhanHang(req.getNgayNhanHang());
//            hoaDon.setTrangThai(req.getTrangThai());
//            hoaDon.setLoaiHD(req.getLoaiHD());
//            hoaDon.setPhiVanChuyen(req.getPhiVanChuyen());
//            hoaDon.setThongTinGiaoHang(req.getThongTinGiaoHang());
//            hoaDon.setNgaySua(LocalDateTime.now());
//
//            try {
//                hoaDonRepo.save(hoaDon);
//                return ResponseEntity.ok().build();
//            } catch (Exception e) {
//
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteHoaDon(@PathVariable String id) {
//        if (hoaDonRepo.existsById(id)) {
//            hoaDonRepo.deleteById(id);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//
//}
