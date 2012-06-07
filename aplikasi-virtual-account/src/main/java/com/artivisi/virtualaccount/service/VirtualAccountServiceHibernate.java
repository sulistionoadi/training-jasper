/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.virtualaccount.service;

import com.artivisi.virtualaccount.domain.Nasabah;
import com.artivisi.virtualaccount.domain.VirtualAccountService;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author endy
 */
@Service("virtualAccountService")
@Transactional
public class VirtualAccountServiceHibernate implements VirtualAccountService {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<Nasabah> cariNasabah() {
        List<Nasabah> result = sessionFactory.getCurrentSession()
                .createQuery("select n from Nasabah n")
                .list();
        
        for (Nasabah n : result) {
            Hibernate.initialize(n.getDaftarNomerTelepon());
        }
        return result;
    }

    @Override
    public void simpan(Nasabah n) {
        sessionFactory.getCurrentSession().saveOrUpdate(n);
    }

    @Override
    public Nasabah cariNasabahById(String id) {
        if(id == null || id.trim().length() < 1) {
            return null;
        }
        return (Nasabah) sessionFactory.getCurrentSession().get(Nasabah.class, id);
    }

    @Override
    public void hapus(Nasabah n) {
        sessionFactory.getCurrentSession().delete(n);
    }
    
}
