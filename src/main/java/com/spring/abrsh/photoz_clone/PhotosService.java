package com.spring.abrsh.photoz_clone;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//@Component
@Service
public class PhotosService {
    private Map<String, Photo> db = new HashMap<>() {
        {
            put("1", new Photo("1", "photo1.jpg"));
            put("2", new Photo("2", "photo2.jpg"));
        }
    };

    public Collection<Photo> get() {
        return db.values();
    }

    public Photo get(String id) {
        return db.get(id);
    }

    public Object remove(String id) {
        return db.remove(id);
    }

    public Photo save(String fileName, byte[] data) {
        Photo photo = new Photo();

        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(fileName);
        try {
            photo.setData(data);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving photo");
        }

        db.put(photo.getId(), photo);
        return photo;
    }

}
