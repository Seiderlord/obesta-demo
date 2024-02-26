package at.example.backenddemo.repository;

import at.example.backenddemo.model.db.Item;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NonNullApi
public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findAll();

    void deleteById(long id);
}
