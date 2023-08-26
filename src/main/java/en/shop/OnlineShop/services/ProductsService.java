package en.shop.OnlineShop.services;

import en.shop.OnlineShop.models.Product;
import en.shop.OnlineShop.repositories.ProductsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Product find(int id) {
        return productsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Product product){
        productsRepository.save(product);
    }

    @Transactional
    public void update(int id, Product product) {
        product.setId(id);
        productsRepository.save(product);
    }

    @Transactional
    public void remove(int id){
        productsRepository.deleteById(id);
    }

    @Transactional
    public void changeCount(int id, int count){
        Product product = productsRepository.findById(id).get();
        product.setCount(product.getCount() + count);
    }

}
