package com.example.amazone_database.Service;


import com.example.amazone_database.Model.Merchant;
import com.example.amazone_database.Model.MerchantStock;
import com.example.amazone_database.Model.Product;
import com.example.amazone_database.Model.User;
import com.example.amazone_database.Repository.MerchantRepository;
import com.example.amazone_database.Repository.MerchantStockRepository;
import com.example.amazone_database.Repository.ProductRepository;
import com.example.amazone_database.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {




    // Dependency Injections
    @Autowired
    private final UserService userService;
    private final MerchantStockService merchantStockService;
    private final MerchantService merchantService;
    private final UserRepository userRepository;
    private final MerchantStockRepository merchantStockRepository;
    private  final MerchantRepository MerchantRepository;
    private final ProductRepository productRepository;


    @Autowired
    private MerchantRepository merchantRepository;
    List<Product> wishList = new ArrayList<>();


    //-----  CRUD Methods   --------

    // get All Product
    public List<Product> getProducts() {
      return productRepository.findAll();

    }


    // creat new Product
    public void addProducts(Product product) {
         productRepository.save(product);
    }


    // Delete Product by id
    public boolean removeProducts(Integer id) {
     Product p=productRepository.getById(id) ;
        if (p == null) {
          return false;
        }
        productRepository.delete(p);
        return true;
    }


    // update product by id
    public boolean updateProducts(Integer id, Product product) {
       Product p =productRepository.getById(id);
       if (p == null) {
           return false;
       }
       p.setProductName(product.getProductName());
       p.setPrice(product.getPrice());
       p.setCategoryId(product.getCategoryId());
       productRepository.save(p);
       return true;
    }



    // -----  End Point -------

    //Create endpoint where user can buy a product directly
    public String UserBuyProduct(Integer userId, Integer productId, Integer merchantId) {

        User user = userRepository.getById(userId);
        if (!userId.equals(userId)) {
            return "User not found";
        }
        Product product = productRepository.getById(productId);
        if (product == null) {
            return "Product not found";
        }

        MerchantStock merchantStock = merchantStockRepository.getById(merchantId);
        if (merchantStock == null) {
            return "Merchant Not Found ";
        }

        if (merchantStock.getStock() <= 0) {
            return "product out of Stock";
        }

        if (user.getBalance() < product.getPrice()) {
            return "Insufficient balance";
        }

        merchantStock.setStock(merchantStock.getStock() - 1);
        user.setBalance(user.getBalance() - product.getPrice());
        return "Purchase successful.";
    }


    // --- Extra End Points -----


    // Costumer Have Gift Card when buy with determine price
    public String giftCard(Integer userId, Integer productId, Integer merchantId) {

        if (UserBuyProduct(userId, productId, merchantId).equals("Purchase successful.")) {

            User user =userRepository.getById(userId);
            Product product = productRepository.getById(productId);
            Merchant merchant=merchantRepository.getById(merchantId);

            if (user == null){
                return "User not found";
            }

            if (product == null) {
                return "Product not found";
            }

            if (merchant == null) {
                return "Merchant not found";
            }

            double totalSpent =  product.getPrice() - user.getBalance();

            if (totalSpent >= 1000) {
                return "Congratulations, you have a gift card with 600 Riyal!";
            } else if (totalSpent >= 800) {
                return "Congratulations, you have a gift card with 500 Riyal!";
            } else if (totalSpent >= 500) {
                return  "Congratulations, you have a gift card with 200 Riyal!";
            } else {
                return  "You have not spent enough to qualify for a gift card.";
            }
        } else {
            return  "Indicate that the purchase was not successful" ;
        }
    }



    // User want Tracking Order
    public String trackOrderMethod(Integer userId, String password, Integer productId, Integer merchantId) {

        String purchaseResult = UserBuyProduct(userId, productId, merchantId);

        if (!purchaseResult.equals("Purchase successful.")) {
            return "Indicate that the purchase was not successful";
        }

        User user = userRepository.getById(userId);
        if (user == null) {
            return "User not found";
        }

        boolean checkPassword = password.equals(user.getPassword());
        if (!checkPassword) {
            return "Incorrect password";
        }


         List<Product> orders = new ArrayList<>();
         orders.add(getProductById(productId));
         if (orders.isEmpty()) {
            return "No orders available to track";
        }


        int orderNumber = generateOrderNumber();
        LocalDate expectedDeliveryDate = LocalDate.now().plusDays(5);
        return "Track number: " + orderNumber + ", Expected Arrival Date: " + expectedDeliveryDate;
      }


    // get product by id
    public Product getProductById (Integer id){
        Product p=productRepository.getById(id);
        if (p == null) {
            return null;
        }
        return p;
       }



    // User add product to wish list
    public String addProductToWishList (Integer userId, Integer productId){

        User user = userRepository.getById(userId); // check if user id found
        if (user == null) {
            return " User Id not found ";
        }

        Product product = productRepository.getById(productId); // check if product id is found
        if (product == null) {
            return " Product Id not found ";
        }


        if (wishList.contains(product)) {
            return "Product is already in your wishlist"; // Product already exists in the wishlist
        }


        wishList.add(product); // Add Product to wish List
        user.setScore(user.getScore() + 1); // Increase user's score // when user added product to wish list , Score is increase
        return " Product added successfully , You have One Point Score !! ";
       }


    // Complete : first check if user found , then User want to get his WishList
    public List<Product> getWishList (Integer userId){
        User user = userRepository.getById(userId);
        if (user == null) {
            return null;
          }
        return wishList;
       }



    public int generateOrderNumber() {
        // logic to generate a track number
        return (int) (Math.random() * 100000);
    }




}
