package com.br.free.commerce.controllers;

import com.br.free.commerce.InterfaceApplication;
import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.StoreService;
import com.br.free.commerce.to.StoreForm;
import com.free.commerce.entity.UserLogin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by eduardosanson on 13/03/16.
 */
@Controller
@RequestMapping(value = "/store")
public class StoreController {


    private static final String INDEX="index";
    private static final String MENU="/store/menu";
    private static final String PAGE_NAME="pageName";
    private static final String PAGE_FRAGMENT="pageFragment";
    private static final String PAGE_REGISTRATION="registration";
    private static final String REGISTRATION_FRAGMENT="registration";
    private static final String MENU_NAME="MENU_NAME";
    private static final String MENU_FRAGMENT="MENU_FRAGMENT";
    private static final String MENU_NAME_HOME="store-products";
    private static final String MENU_FRAGMENT_HOME="store-products";
    private static final String PAGE_ACCOUNT = "account-posts";
    private static final String PAGE_ACCOUNT_FRAGMENT = "account-posts";
    private static final String PAGE_CREATE_PRODUCT ="fragments/account-create-product";
    private static final String FRAGMENT_CREATE_PRODUCT ="account-create-product";
    private static final String PAGE_CHANGE_PASSWORD ="store-change-password";
    private static final String FRAGMENT_CHANGE_PASSWORD ="store-change-password";
    private static final String PAGE_CHANGE_ADDRESS ="store-change-address";
    private static final String FRAGMENT_CHANGE_ADDRESS ="store-change-address";
    private static final String PAGE_CHANGE_REGISTRATION ="store-change-registration";
    private static final String FRAGMENT_CHANGE_REGISTRATION ="store-change-registration";

    @Autowired
    private StoreService storeService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = Logger.getLogger(StoreController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(Model model,StoreForm storeForm){
        model.addAttribute(PAGE_NAME,PAGE_REGISTRATION);
        model.addAttribute(PAGE_FRAGMENT,REGISTRATION_FRAGMENT);
        return INDEX;
    }

    @RequestMapping(value = "/form",method = RequestMethod.POST)
    public String singUp(@Valid StoreForm storeForm, BindingResult bindingResult, Model model,HttpServletRequest request){
        model.addAttribute(PAGE_NAME,PAGE_REGISTRATION);
        model.addAttribute(PAGE_FRAGMENT,REGISTRATION_FRAGMENT);


            if (bindingResult.hasErrors()){
                System.out.println("ocorreu um erro");
                return INDEX;
            }
            UserLogin user = storeService.cadastrar(storeForm);

            if (user==null){
                return INDEX;
            }else {
                authenticateUserAndSetSession(user, request);
                return "redirect:"+MENU;
            }
    }

    @RequestMapping("/menu")
    public String login(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        customUserDetails.getUserlogin();

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);

        model.addAttribute(MENU_NAME,MENU_NAME_HOME);
        model.addAttribute(MENU_FRAGMENT,MENU_FRAGMENT_HOME);

        File rootFolder = new File(InterfaceApplication.ROOT);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("files",
                Arrays.stream(rootFolder.listFiles())
                        .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
                        .map(f -> f.getName())
                        .collect(Collectors.toList())
        );

        return INDEX;
    }

    @RequestMapping("/menu/createProduct")
    public String showCreateProduct(Model model){
        logger.info("usando ajax de create product");

        File rootFolder = new File(InterfaceApplication.ROOT);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("files",
                Arrays.stream(rootFolder.listFiles())
                        .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
                        .map(f -> f.getName())
                        .collect(Collectors.toList())
        );

        return PAGE_CREATE_PRODUCT +" :: " + FRAGMENT_CREATE_PRODUCT;
    }

    @RequestMapping("/menu/showMyProducts")
    public String showMyProducts(){
        return "fragments/"+MENU_NAME_HOME + " :: " + MENU_FRAGMENT_HOME;
    }

    @RequestMapping("/menu/changePassword")
    public String showChangePassword(){
        return "fragments/"+ PAGE_CHANGE_PASSWORD + " :: " + FRAGMENT_CHANGE_PASSWORD;
    }

    @RequestMapping("/menu/changeAddress")
    public String showChangeAndress(){
        return "fragments/"+ PAGE_CHANGE_ADDRESS + " :: " + FRAGMENT_CHANGE_ADDRESS;
    }

    @RequestMapping("/menu/changeRegistration")
    public String showChangeRegistration(){
        return "fragments/"+ PAGE_CHANGE_REGISTRATION + " :: " + FRAGMENT_CHANGE_REGISTRATION;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
            return "redirect:upload";
        }
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
            return "redirect:upload";
        }

        if(imageProcessor(name, file, redirectAttributes)==true);

        return "redirect:upload";
    }

    private boolean imageProcessor(String name, MultipartFile file, RedirectAttributes redirectAttributes) {
       boolean isSuccess=false;

        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(InterfaceApplication.ROOT + "/" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                isSuccess=true;
                return isSuccess;
            }
            catch (Exception e) {
                return isSuccess;
            }
        }
        else {
            return isSuccess;
        }
    }

    private void authenticateUserAndSetSession(UserLogin user, HttpServletRequest request) {

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getPermissao()));

        try {

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getLogin(),user.getSenha(),grantedAuthorityList));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
