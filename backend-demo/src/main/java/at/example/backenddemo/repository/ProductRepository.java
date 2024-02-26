package at.example.backenddemo.repository;

import at.example.backenddemo.model.db.Product;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NonNullApi
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    void deleteById(long id);

}
