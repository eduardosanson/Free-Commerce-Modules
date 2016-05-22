package com.br.free.commerce.util;

import com.br.free.commerce.InterfaceApplication;
import com.free.commerce.entity.Imagem;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by pc on 21/05/2016.
 */
public class ImagemProcessor {

    public static Imagem processor(String nomePasta, String nomeArquivo, MultipartFile file) {
        String nomeDaPasta = InterfaceApplication.ROOT + "/" + nomePasta + "/";
        FileName fileName = new FileName(file.getOriginalFilename(), '/', '.');
        String pathCompleto = nomeDaPasta + nomeArquivo + "." + fileName.extension();
        Imagem imagem = new Imagem();

        if (!file.isEmpty()) {
            try {
                File pasta = new File(nomeDaPasta);
                Path path = Paths.get(nomeDaPasta);
                if (Files.notExists(path)) {
                    pasta.mkdirs();
                }

                File fileRoot = new File(pathCompleto);
                FileOutputStream outputStream = new FileOutputStream(fileRoot);
                BufferedOutputStream stream = new BufferedOutputStream(outputStream);

                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                imagem.setRegistrado(new Date());
                imagem.setNomeDoArquivo(nomeArquivo);
                imagem.setPath(pathCompleto);
                return imagem;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
