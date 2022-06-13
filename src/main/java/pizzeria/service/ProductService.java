package pizzeria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizzeria.model.Product;
import pizzeria.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }

    public List<Product> getProductsByIds(List<Integer> ids){
        return productRepository.getProductsByIds(ids);
    }

    public boolean addProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(int id){
        return productRepository.getProductById(id);
    }

    public List<Product> getProductsBySize(int sizeId){
        return productRepository.getAllProducts().stream()
                .filter(product -> product.getSize().getId() == sizeId)
                .toList();
    }
}
