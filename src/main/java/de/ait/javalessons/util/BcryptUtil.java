package de.ait.javalessons.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "adminpass";
        String encodetPassword = encoder.encode(password);
        System.out.println(password + "-->"+ encodetPassword);
    }

    //userpass-->$2a$10$XTYrfRQzPUDsJ.smAKwRougTNtZ80Ak267mGWnaYYQMZG9KaZD2jy
    //adminpass-->$2a$10$pSM3dZIRXDX9qX2R94tF1ehbH6HcKGwApQJDwl3NmC8bvmlIbmqrK
}
