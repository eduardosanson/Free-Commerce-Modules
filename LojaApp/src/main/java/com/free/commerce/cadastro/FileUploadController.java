package com.free.commerce.cadastro;


import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileUploadController {

//    @RequestMapping(method = RequestMethod.GET, value = "/upload")
//    public String provideUploadInfo(Model model) {
//        File rootFolder = new File(UiApplication.ROOT);
//        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
//                .map(f -> f.getName())
//                .collect(Collectors.toList());
//
//        model.addAttribute("files",
//                Arrays.stream(rootFolder.listFiles())
//                        .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
//                        .map(f -> f.getName())
//                        .collect(Collectors.toList())
//        );
//
//        return "uploadForm";
//    }

//    @RequestMapping(method = RequestMethod.POST, value = "/upload")
//    public String handleFileUpload(@RequestParam("name") String name,
//                                   @RequestParam("file") MultipartFile file,
//                                   RedirectAttributes redirectAttributes) {
//        if (name.contains("/")) {
//            redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
//            return "redirect:clientSignIn";
//        }
//        if (name.contains("/")) {
//            redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
//            return "redirect:clientSignIn";
//        }
//
//        if (!file.isEmpty()) {
//            try {
//                BufferedOutputStream stream = new BufferedOutputStream(
//                        new FileOutputStream(new File(UiApplication.ROOT + "/" + name)));
//                FileCopyUtils.copy(file.getInputStream(), stream);
//                stream.close();
//                redirectAttributes.addFlashAttribute("message",
//                        "You successfully uploaded " + name + "!");
//            }
//            catch (Exception e) {
//                redirectAttributes.addFlashAttribute("message",
//                        "You failed to upload " + name + " => " + e.getMessage());
//            }
//        }
//        else {
//            redirectAttributes.addFlashAttribute("message",
//                    "You failed to upload " + name + " because the file was empty");
//        }
//
//        return "redirect:clientSignIn";
//    }


    @RequestMapping(value = "/continueFileUpload", method = RequestMethod.POST)
    public String continueFileUpload(HttpServletRequest request,
                                     HttpServletResponse response){
        MultipartHttpServletRequest mRequest;
        try {
            request.getParameter("file");
            System.out.print(request.getParameter("file"));
            mRequest = (MultipartHttpServletRequest) request;
            mRequest.getParameterMap();

            Iterator<String> itr = mRequest.getFileNames();
            while (itr.hasNext()) {
                MultipartFile mFile = mRequest.getFile(itr.next());
                String fileName = mFile.getOriginalFilename();
                System.out.println(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}