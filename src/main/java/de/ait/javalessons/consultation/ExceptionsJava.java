package de.ait.javalessons.consultation;

import de.ait.javalessons.exceptions.MyCustomException;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
public class ExceptionsJava {
    public static void main(String[] args) {
        // divide(10, 2);
        try {
            divide2(10, 0);
            divide3(23, 0);
        } catch (SQLException e) {
            log.error("SQLException: {}", e.getMessage());
        } catch (MyCustomException e) {
            log.error("MyCustomException: {}", e.getMessage());
        }

    }

    public static int divide(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            log.error("ArithmeticException: {}", e.getMessage());
            return -1;
        } finally {
            log.info("Finally block executed");
        }
    }

    public static int divide2(int a, int b) throws SQLException {
        return a / b;
    }

    public static int divide3(int a, int b) throws MyCustomException {
        return a / b;
    }
}
