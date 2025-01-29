package com.spring.abrsh.photoz_clone;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PhotozController {
    private Map<String, Photo> db = new HashMap<>() {
        {
            put("1", new Photo("1", "photo1.jpg"));
            put("2", new Photo("2", "photo2.jpg"));
        }
    };

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/photos")
    public Collection<Photo> get() {
        return db.values();
    }

    @GetMapping("/photos/{id}")
    public Photo getOne(@PathVariable String id) {
        Photo photo = db.get(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        return db.get(id);
    }

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id) {
        if (db.remove(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
    }

    @PostMapping("/photos")
    public Photo create(@RequestBody Photo photo) {
        photo.setId(UUID.randomUUID().toString());
        db.put(photo.getId(), photo);
        return photo;
    }
}
