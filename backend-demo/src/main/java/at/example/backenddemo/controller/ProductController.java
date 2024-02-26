package at.example.backenddemo.controller;

import at.example.backenddemo.model.dto.ProductDto;
import at.example.backenddemo.model.mapper.ProductMapper;
import at.example.backenddemo.model.validator.ProductValidationGroups;
import at.example.backenddemo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/product/{productId}")
    @Operation(description = "Load product with given id.")
    public ProductDto getProduct(@PathVariable long productId) {
        return productMapper.toDto(productService.loadProduct(productId));
    }

    @GetMapping("/products")
    @Operation(description = "Load all products.")
    public List<ProductDto> getAll() {
        return productMapper.toDto(productService.loadAllProducts());
    }

    @PostMapping("/product")
    @Secured({"ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_ADMIN"})
    @Operation(description = "Create a new product.")
    public ProductDto create(@Validated(ProductValidationGroups.CreateGroup.class) @RequestBody ProductDto productDto) {
        return productMapper.toDto(productService.createProduct(productMapper.toEntity(productDto)));
    }

    @PutMapping("/product")
    @Secured({"ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_ADMIN"})
    @Operation(description = "Update existing product.")
    public ProductDto update(@Validated(ProductValidationGroups.UpdateGroup.class) @RequestBody ProductDto productDto) {
        return productMapper.toDto(productService.updateProduct(productMapper.toEntity(productDto)));
    }

    @DeleteMapping("/product/{productId}")
    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @Operation(description = "Delete an product with given id.")
    public void delete(@PathVariable("productId") long productId) {
        productService.deleteProduct(productId);
    }

    // IMAGE INTERFACES
    @PostMapping(value = "/product/image", consumes = "multipart/form-data")
    @Secured({"ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_ADMIN"})
    @Operation(description = "Upload an image to given product.")
    public ResponseEntity<ProductDto> uploadFile(@RequestParam("productId") Long productId, @RequestParam("file") MultipartFile file) {
        try {
            productService.addMediaFileToProduct(productId, file);
            return ResponseEntity.ok(productMapper.toDto(productService.loadProduct(productId)));
        } catch (IOException | SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/product/image")
    @Secured({"ROLE_CUSTOMER", "ROLE_MANAGER", "ROLE_ADMIN"})
    @Operation(description = "Remove an image of given product.")
    public ResponseEntity<String> removeFile(@RequestParam("productId") Long articleId, @RequestParam("posId") int posId) {
        productService.removeImageToProduct(articleId, posId);
        return ResponseEntity.noContent().build();
    }
}
