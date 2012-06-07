/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.virtualaccount.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author endy
 */
@Entity @Table(name="t_transaksi")
public class Transaksi {
    
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;
    
    @Column(name="waktu_transaksi", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuTransaksi = new Date();
    
    @Column(name="jenis_transaksi")
    @Enumerated(EnumType.STRING)
    private JenisTransaksi jenisTransaksi;
    
    @ManyToOne
    @JoinColumn(name="id_rekening")
    private Rekening rekening;
    private BigDecimal nilai;
    
    @ElementCollection
    @JoinTable(name="rekening_informasi_tambahan", joinColumns=@JoinColumn(name="id_transaksi"))
    @MapKeyColumn(name="informasi_tambahan_key")
    @Column(name="informasi_tambahan_value")
    private Map<String, String> informasiTambahan = new HashMap<String, String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getInformasiTambahan() {
        return informasiTambahan;
    }

    public void setInformasiTambahan(Map<String, String> informasiTambahan) {
        this.informasiTambahan = informasiTambahan;
    }

    public JenisTransaksi getJenisTransaksi() {
        return jenisTransaksi;
    }

    public void setJenisTransaksi(JenisTransaksi jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public Rekening getRekening() {
        return rekening;
    }

    public void setRekening(Rekening rekening) {
        this.rekening = rekening;
    }

    public Date getWaktuTransaksi() {
        return waktuTransaksi;
    }

    public void setWaktuTransaksi(Date waktuTransaksi) {
        this.waktuTransaksi = waktuTransaksi;
    }
}
