package com.br.free.commerce.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by eduardo.sanson on 16/06/2016.
 */
public class CloudImage {

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dhydvxirz",
            "api_key", "835914615312876",
            "api_secret", "o-QUD352bnc0FSiV2mANXS-zNLs"));

    public String enviarArquivo(File toUpload) throws IOException {

        Map uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap());

        return uploadResult.get("url").toString();

    }
}
