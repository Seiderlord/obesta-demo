package at.example.backenddemo.service;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;

import at.example.backenddemo.model.db.MediaResource;
import at.example.backenddemo.model.db.Product;
import at.example.backenddemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product loadProduct(Long id) {
        Optional<Product> productOptional = this.productRepository.findById(id);
        if(productOptional.isPresent())
            return productOptional.get();
        throw new NotFoundException("Product with given id not found!");
    }

    public List<Product> loadAllProducts() {
        return this.productRepository.findAll();
    }

    public Product createProduct(Product product) {
        if(product.getId() != null)
            throw new NotAllowedException("Given product for creating should not have an id!");
        product.setMediaResources(new ArrayList<>());
        return this.productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        if(product.getId() == null)
            throw new NotAllowedException("Given product for updating does not have an id!");
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        this.productRepository.deleteById(productId);
    }

    public void addMediaFileToProduct(Long productId, MultipartFile file) throws IOException, SQLException {
        Product product = this.loadProduct(productId);
        List<MediaResource> mediaFiles = product.getMediaResources();
        mediaFiles.add(new MediaResource(file));
        product.setMediaResources(mediaFiles);
        this.productRepository.save(product);
    }

    public void removeImageToProduct(Long productId, int positionId) {
        Product product = this.loadProduct(productId);
        List<MediaResource> mediaFiles = product.getMediaResources();
        if (positionId < 0 || positionId > mediaFiles.size())
            return;
        mediaFiles.remove(positionId);
        product.setMediaResources(mediaFiles);
        this.productRepository.save(product);
    }
}
