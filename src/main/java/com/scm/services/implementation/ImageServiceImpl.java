//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.services.implementation;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.services.ImageService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(MultipartFile contactImage, String fileName) {
        try {
            byte[] dataArr = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(dataArr);
            this.cloudinary.uploader().upload(dataArr, ObjectUtils.asMap(new Object[]{"public_id", fileName}));
            return this.getUrlFromPublicId(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUrlFromPublicId(String publicId) {
        return this.cloudinary.url().transformation((new Transformation()).width(500).height(500).crop("fill")).generate(publicId);
    }
}
