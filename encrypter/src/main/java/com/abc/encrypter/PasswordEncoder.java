package com.abc.encrypter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author joao4018 25/03/2020.
 */
public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("seadasdasnha"));
    }
}
