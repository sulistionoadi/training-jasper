/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.virtualaccount.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author endy
 */
public class VirtualAccountServiceHibernateTest {
    private static AbstractApplicationContext context;
    
    @BeforeClass
    public static void inisialisasi(){
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        context.registerShutdownHook();
    }
    
    @Test
    public void halo(){
        System.out.println("Halo");
    }
}
