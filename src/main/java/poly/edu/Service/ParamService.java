package poly.edu.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service

public class ParamService {
    @Autowired
    HttpServletRequest req;

    public String getString(String name, String defaultValue) {
        String value = req.getParameter(name);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }


    public int getInt(String name, int defaultValue) {
        String value = req.getParameter(name);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    public double getDouble(String name, double defaultValue) {

        String value = req.getParameter(name);
        return value != null ? Double.parseDouble(value) : defaultValue;
    }


    public boolean getBoolean(String name, boolean defaultValue) {
        String value = req.getParameter(name);
        return value != null && value.equalsIgnoreCase("true") ? true : false;
    }


    public Date getDate(String name, String pattern) throws java.text.ParseException {

        String value = req.getParameter(name);
        if (value == null || value.isEmpty()) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);

        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException("Sai định dạng ngày: " + value + ", phải theo: " + pattern, e);
        }
    }

    public File save(MultipartFile file, String path) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        String uploadDir = "E:/SOF302_JAVA5/LABS/Ps39199_NguuyenNhutKha_Lab/src/main/resources/static/photos";
        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File savedFile = new File(directory, fileName);

        try {
            file.transferTo(savedFile);
            return savedFile;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi lưu file: " + e.getMessage(), e);
        }


    }


}
