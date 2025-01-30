package com.spring.abrsh.photoz_clone;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
public class PhotozController {

    private final PhotosService photosService;

    public PhotozController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/photos")
    public Collection<Photo> get() {
        return photosService.get();
    }

    @GetMapping("/photos/{id}")
    public Photo getOne(@PathVariable String id) {
        Photo photo = photosService.get(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        return photosService.get(id);
    }

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id) {
        if (photosService.remove(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
    }

    @PostMapping("/photos")
    public Photo create(@RequestPart("data") MultipartFile file) {
        try {
            Photo photo = photosService.save(file.getOriginalFilename(), file.getBytes());
            return photo;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save photo", e);
        }
    }
}
