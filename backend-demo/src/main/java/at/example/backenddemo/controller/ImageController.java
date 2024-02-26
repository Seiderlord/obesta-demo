package at.example.backenddemo.controller;


import at.example.backenddemo.model.dto.FileDto;
import at.example.backenddemo.model.mapper.MediaResourceMapper;
import at.example.backenddemo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ImageController {
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    private final ImageService imageService;
    private final MediaResourceMapper fileMapper;

    @GetMapping(value = "/image/{imageId}", produces = "image/jpeg")
    public ResponseEntity<byte[]> getImage(@PathVariable UUID imageId) {
        byte[] imageData = new byte[0];
        String contentType = "";
        try {
            FileDto file = fileMapper.toDto(imageService.loadMediaResource(imageId));
            imageData = imageService.loadMediaResourceData(imageId).getByteArray();

            contentType = file.getContentType();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType)); // Set the appropriate content type
            headers.setContentLength(imageData.length);
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);

        } catch (SQLException | IOException e) {
            logger.error("Error loading MediaResource: " + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            logger.error("Error loading MediaResource: " + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

