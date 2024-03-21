package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lớp Validation được sử dụng để kiểm tra tính hợp lệ của mật khẩu, số điện thoại và tên.
 */
public class Validation {
    
    /**
     * Phương thức kiểm tra tính hợp lệ của mật khẩu.
     * @param password Mật khẩu cần kiểm tra.
     * @return true nếu mật khẩu hợp lệ, ngược lại trả về false.
     */
    public boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    /**
     * Phương thức kiểm tra tính hợp lệ của số điện thoại.
     * @param phoneNumber Số điện thoại cần kiểm tra.
     * @return true nếu số điện thoại hợp lệ, ngược lại trả về false.
     */
    public boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    
    /**
     * Phương thức kiểm tra tính hợp lệ của tên.
     * @param name Tên cần kiểm tra.
     * @return true nếu tên hợp lệ, ngược lại trả về false.
     */
    public boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        String[] nameParts = name.split("\\s+");
        if (nameParts.length < 2) {
            return false;
        }
        return name.matches("[a-zA-Z\\s]+");
    }
}
