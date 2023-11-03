package com.example.springmvc_thuc_tap.controller;

import com.example.springmvc_thuc_tap.model.Order;
import com.example.springmvc_thuc_tap.service.orderService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class orderController {
    @Autowired
    orderService orderServ;

    @GetMapping
    public String show(HttpSession session, Model model) {
        List <Order> orderList = orderServ.getAll();
        model.addAttribute("listOrder", orderList);
        return "orders";
    }

    @GetMapping("/register")
    public String getRegisterPage(HttpSession session, Model model){
        return "registerOrder";
    }

    @PostMapping
    public String registerOrder(HttpSession session, Model model,
                              String name, String address, String phone, String orderName){
//        System.out.println(name +   address +  phone +  orderName);
        orderServ.registerOrder(name,  address, phone, orderName);
        return "redirect:/order";
    }

    @GetMapping("getUploadFile")
    public String getUploadFile(){return "upload_file";}

//    @Value("${upload.directory}")
//    private Resource uploadDirectory;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("status") String status) throws Exception {
//        Path uploadPath = Paths.get(uploadDirectory.getURI());
        // Get the original filename
        String originalFilename = file.getOriginalFilename();

        String projectDir = System.getProperty("user.dir");
        // Define the upload directory
        String uploadDir = "";
        if (status.equals("public")){
            uploadDir = projectDir + "\\src\\main\\resources\\static\\uploads";
        } else{
            uploadDir = projectDir + "\\src\\main\\uploads";
        }

        System.out.println(uploadDir);
        // Create the upload path if it doesn't exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        if (file.getSize() > 5000000) { // Maximum file size of 5MB
            throw new Exception("File size too large. Please upload a file less than 5MB.");
        }
        // Create a new file with a unique name in the upload directory
        File uploadedFile = new File(uploadDir, originalFilename);
        // Save the uploaded file to disk
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, uploadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return "redirect:/order/getUploadFile"; // Redirect to another page after file upload
    }
}
