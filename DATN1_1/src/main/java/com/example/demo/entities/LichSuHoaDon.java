package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="LICHSUHOADON")
public class LichSuHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer ID ;
    @Column(name="NGAYTAO")
    private Date NGAYTAO ;
    @Column(name="TRANGTHAI")
    private int TRANGTHAI ;
    @Column(name="NGUOITHAOTAC")
    private  String NGUOITHAOTAC ;

    @OneToOne
    @JoinColumn(name="IDHD")
    private HoaDon hoaDon;

}
