package com.example.exception;

/**
 * @author chyj0
 * @date 2024/6/11 上午10:02 10 02
 * @package_name com.example.exception
 * @Project_name FlightSystem-master
 */
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
