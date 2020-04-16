package org.astashonok.onlinestore.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class FileUpload {
    public static boolean uploadFile(HttpServletRequest request, MultipartFile file, String code, long id) {
        String PATH = request.getSession().getServletContext().getRealPath("/assets/images/products/" + id + "/");
        if(!new File(PATH).exists()) {
            new File(PATH).mkdirs();
        }
        try {
            file.transferTo(new File(PATH + code + ".jpeg"));
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
