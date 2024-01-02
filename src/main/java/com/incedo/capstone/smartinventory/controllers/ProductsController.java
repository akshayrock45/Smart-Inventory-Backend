package com.incedo.capstone.smartinventory.controllers;

import com.incedo.capstone.smartinventory.entities.Products;

import com.incedo.capstone.smartinventory.exceptions.ProductsNotFoundException;
import com.incedo.capstone.smartinventory.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @PostMapping("/addProduct")
    public ResponseEntity<Products> addProducts(@RequestBody Products products){
        Products addedProduct=productsService.addProduct(products);
        return new ResponseEntity<>(addedProduct,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts(){
        List<Products> productList= productsService.getAllProducts();
        if(productList!=null)
        {
            return new ResponseEntity<>(productList,HttpStatus.OK);

        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductsById(@PathVariable("id") int id){
        Products product = productsService.getProductById(id);
        if (product !=null)
        {
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Products> updateProduct(@PathVariable("productId") long productId, @RequestBody Products product) {
        Products updatedProduct = productsService.updateProduct(productId, product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteByProductId(@PathVariable("productId") long productId)
    {
        try{
            //String message= productsService.deleteProduct(productId);
            String message=productsService.deleteProductById(productId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch(ProductsNotFoundException infe)
        {
            return new ResponseEntity<>(infe.getMessage(), HttpStatus.OK);
        }
    }
}
