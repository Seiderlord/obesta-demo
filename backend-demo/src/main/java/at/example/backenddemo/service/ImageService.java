package at.example.backenddemo.service;


import at.example.backenddemo.model.db.MediaResource;
import at.example.backenddemo.repository.MediaResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ImageService {

    private final MediaResourceRepository mediaResourceRepository;

    /**
     * Loads data of the file with the given id.
     * @param id
     * @return
     * @throws SQLException
     * @throws IOException
     */
    @Transactional
    public ByteArrayResource loadMediaResourceData(UUID id) throws SQLException, IOException {
        Optional<MediaResource> mediaResourceOptional = this.mediaResourceRepository.findById(id);
        if(mediaResourceOptional.isPresent())
            return new ByteArrayResource((mediaResourceOptional.get().getData().getBinaryStream().readAllBytes()));
        throw new NotFoundException("Media resource with given id not found!");
    }

    public MediaResource loadMediaResource(UUID id) {
        Optional<MediaResource> mediaResourceOptional = this.mediaResourceRepository.findById(id);
        if(mediaResourceOptional.isPresent())
            return mediaResourceOptional.get();
        throw new NotFoundException("Media resource with given id not found!");
    }
}
