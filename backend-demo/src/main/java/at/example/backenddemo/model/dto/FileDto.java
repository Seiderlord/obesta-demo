package at.example.backenddemo.model.dto;

import at.example.backenddemo.model.enumeration.DocumentType;
import lombok.Data;

import java.util.UUID;

@Data
public class FileDto {
    private UUID id;
    private DocumentType documentType;
    private String fileName;
    private String contentType;
}
