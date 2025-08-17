package org.example.util;

import org.example.model.UserInfoDto;

/**
 * 🛠️ ValidationUtil
 * -----------------
 * Utility class to validate user signup/login attributes.
 * Instead of directly saving anything from frontend → DB,
 * we first check if the data follows certain rules.
 *
 * Think of it as a "security guard 🚓" before data enters the system.
 */
public class ValidationUtil {

    /**
     * Validate common attributes of a user.
     * @param userInfoDto data from frontend (DTO)
     * @return true if all rules pass, false otherwise
     */
    public static Boolean validateUserAttributes(UserInfoDto userInfoDto) {
        if (userInfoDto == null) {
            return false;
        }

        // 1 Password validation
        String password = userInfoDto.getPassword();
        if (password == null || password.length() < 8) {
            // must have at least 8 characters
            return false;
        }
        // Bonus: strong password check (at least 1 digit, 1 letter)
        if (!password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*")) {
            return false;
        }

        // 2 Username validation
        String username = userInfoDto.getUsername();
        if (username == null || username.length() < 3) {
            // username must be at least 3 chars
            return false;
        }
        if (!username.matches("^[a-zA-Z0-9._-]+$")) {
            // allow only letters, numbers, dot, underscore, hyphen
            return false;
        }

        // 3 Email validation (basic regex)
        String email = userInfoDto.getEmail();
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return false;
        }

        // 4 Phone validation (must be 10 digits, if provided)
        Long phoneNumber = userInfoDto.getPhoneNumber();
        if (phoneNumber != null && String.valueOf(phoneNumber).length() != 10) {
            return false;
        }

        return true; //  all checks passed
    }
}