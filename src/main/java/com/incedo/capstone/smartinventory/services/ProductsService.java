package com.incedo.capstone.smartinventory.services;

import com.incedo.capstone.smartinventory.entities.Products;

import com.incedo.capstone.smartinventory.exceptions.ProductsNotFoundException;
import com.incedo.capstone.smartinventory.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    ProductsRepository productsRepository;

    public Products addProduct(Products products){
        return productsRepository.save(products);

    }

    public Products getProductById(long productId){
        Optional<Products> op= productsRepository.findById(productId);
        return op.orElse(null);
    }

    public Products updateProduct(long id, Products updatedProduct){
        Products existingProduct = getProductById(id);
        if (existingProduct != null) {

            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            existingProduct.setStock(updatedProduct.isStock());

            return productsRepository.save(existingProduct);
        } else {
            return null;
        }
    }
    public String deleteProductById(long productId) {
        Optional<Products> op = productsRepository.findById(productId);

        if (op.isPresent()) {
            Products deleteProducts = op.get();
            if (deleteProducts.isStock()) {
                productsRepository.delete(deleteProducts);
                return "Product deleted successfully";
            }
            else {
                return "Product cannot be deleted as it is not available ";
            }

        }
        throw new ProductsNotFoundException("Products not found for id: " + productId);
    }

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }
}
