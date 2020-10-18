package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author twg
 * @since 2020/10/18
 */
public class MainTest {

    public static void main(String[] args) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String p1 = passwordEncoder.encode("order-secret-8888");
        String p1 = passwordEncoder.encode("security");
        System.out.println("MainTest.main p1 = " + p1);

    }
}
