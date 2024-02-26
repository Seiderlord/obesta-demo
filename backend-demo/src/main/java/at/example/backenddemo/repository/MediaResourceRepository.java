package at.example.backenddemo.repository;

import at.example.backenddemo.model.db.MediaResource;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@NonNullApi
public interface MediaResourceRepository extends CrudRepository<MediaResource, UUID> {


}
