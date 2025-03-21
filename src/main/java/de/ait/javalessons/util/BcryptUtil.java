package de.ait.javalessons.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "adminpass";

        String encoderPassword = encoder.encode(password);
        System.out.println(password + "-->" + encoderPassword);

        //  userpass-->$2a$10$T6sAgKK5VhWVfCtMzTFFKeyDRc3Akplfnomaszb29aJ78gbTfxRvy
        // adminpass-->$2a$10$QuZhvEKSj/urNBcv0e.d8.amolVqh5nCQE8gE3qg4tSWuu2ThMs9q

    }
}
