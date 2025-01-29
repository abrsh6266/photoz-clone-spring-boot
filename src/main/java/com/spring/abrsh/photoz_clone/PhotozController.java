package com.spring.abrsh.photoz_clone;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotozController {

    private List<Photo> db = List.of(new Photo("1", "photo1.jpg"), new Photo("2", "photo2.jpg"));

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/photos")
    public List<Photo> get() {
        return db;
    }

}
