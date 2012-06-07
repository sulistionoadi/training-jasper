/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.virtualaccount.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author endy
 */
@Entity @Table(name="t_nasabah")
public class Nasabah {
    
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;
    
    @Column(nullable=false, unique=true)
    @NotNull @Size(min=3, max=10)
    private String nomer;
    
    @Column(nullable=false)
    @NotNull @Size(min=3)
    private String nama;
    
    @Size(min=1)
    @Email
    private String email;
    
    @Temporal(TemporalType.DATE)
    @Column(name="tanggal_lahir")
    @Past
    private Date tanggalLahir;
    
    @ElementCollection
    @JoinTable(name="t_nasabah_nomer_telepon", joinColumns=@JoinColumn(name="id_nasabah"))
    @Column(name="nomer_telepon")
    private Set<String> daftarNomerTelepon = new HashSet<String>();

    public Set<String> getDaftarNomerTelepon() {
        return daftarNomerTelepon;
    }

    public void setDaftarNomerTelepon(Set<String> daftarNomerTelepon) {
        this.daftarNomerTelepon = daftarNomerTelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }
}
