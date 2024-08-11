package com.example.amazone_database.Controller;

import com.example.amazone_database.Api.ApiResponse;
import com.example.amazone_database.Model.Product;
import com.example.amazone_database.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    // Dependency Injection
    private final ProductService productService;


    // -------- CRUD -------
    @GetMapping("/get") //get All product
    public ResponseEntity getProduct() {
       return ResponseEntity.status(200).body(productService.getProducts());
    }


    @PostMapping("/add") // creat new Product
    public ResponseEntity addProduct(@Valid @RequestBody Product product , Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        productService.addProducts(product);
        return ResponseEntity.status(200).body(" Product Added Successfully");
    }


    @DeleteMapping("/delete/{id}")// Delete Product by id
    public ResponseEntity deleteProduct(@PathVariable Integer id) {

        if (productService.removeProducts(id)){
            return ResponseEntity.status(200).body(" Product Deleted Successfully");
        }
        return ResponseEntity.status(404).body(" Product Not Found");
    }


    @PutMapping("/update/{id}")// update Product by id
    public ResponseEntity updateProduct(@PathVariable Integer id, @Valid@RequestBody Product product , Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        if (productService.updateProducts(id, product)){
            return ResponseEntity.status(200).body(" Product Updated Successfully");
        }
        return ResponseEntity.status(404).body(" Product Not Found");
    }


    // ------------ End Point -------------------

    @PostMapping("/{userId}/{productId}/{merchantId}/buy")//user can buy a product directly
    public ResponseEntity buyProduct( @PathVariable Integer userId ,@PathVariable Integer productId , @PathVariable Integer merchantId ) {

        String buy_product=productService.UserBuyProduct(userId, productId, merchantId);
        if (buy_product.equalsIgnoreCase("Purchase successful.")){
            return ResponseEntity.status(200).body(new ApiResponse("Purchase successful."));
        }
        return ResponseEntity.status(400).body(buy_product);
    }

    // ----- Extra End Point --------

    @PostMapping("/wishlist/{productId}/{userId}")//Add Product to WishList
    public ResponseEntity wishlist( @PathVariable Integer productId , @PathVariable Integer userId ) {

        if (productService.addProductToWishList(productId,userId) != null){
            return ResponseEntity.status(200).body(new ApiResponse(" Added to Wishlist Successfully "));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Sorry bad request Check if product id  , or username is wrong"));
    }



    @GetMapping("/get-wishlist/{userId}") // get wishlist of the user logged in
    public ResponseEntity getWishlist( @PathVariable Integer userId ) {
        List<Product> wishlist = productService.getWishList(userId);

        if (wishlist == null) {
            return ResponseEntity.status(400).body(new ApiResponse("User Not Found"));
        }
        return ResponseEntity.status(200).body(wishlist);
    }




    @GetMapping("/card/{userId}/{productId}")
    public ResponseEntity getCard( @PathVariable Integer userId , @PathVariable Integer productId ,@PathVariable Integer merchantId ) {
        String mseeageGift=productService.giftCard(userId,productId,merchantId);
        if (mseeageGift.equalsIgnoreCase("You have not spent enough to qualify for a gift card.")){
            return ResponseEntity.status(200).body(new ApiResponse("Gift Card Empty"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(mseeageGift));
    }




    @GetMapping("/track-order/{userId}/{password}/{productId}/{merchantId}")
    public ResponseEntity trackOrder(@PathVariable Integer userId, @PathVariable String password, @PathVariable Integer productId , @PathVariable Integer merchantId) {
        String orderStatus = productService.trackOrderMethod( userId,  password,  productId,  merchantId);

        if (orderStatus.equals("User not found") || orderStatus.equals("Incorrect password")
                ||orderStatus.equals("No orders available to track") ) {
            return ResponseEntity.status(400).body(orderStatus);
        }
        return ResponseEntity.status(200).body(orderStatus);
    }


    @GetMapping("/get-product/{productId}") // get product by id
    public ResponseEntity getProduct(@PathVariable Integer productId) {
        Product product=productService.getProductById(productId);

        if (product==null){
            return ResponseEntity.status(404).body(new ApiResponse(" Product Not Found"));
        }
        return  ResponseEntity.status(200).body(product);
    }





    @GetMapping("/card/{userId}/{productId}/{merchantId}")
    public ResponseEntity gift(@PathVariable Integer userId, @PathVariable Integer productId, @PathVariable Integer merchantId) {
        String giftMessage = productService.giftCard( userId,  productId,  merchantId);

        if (giftMessage.equals("You have not spent enough to qualify for a gift card.")) {
            return ResponseEntity.status(400).body(new ApiResponse("Gift Card Empty"));
        }
        return ResponseEntity.status(200).body(new ApiResponse(giftMessage));
    }




}
