package com.example.amazone_database.Service;


import com.example.amazone_database.Model.MerchantStock;
import com.example.amazone_database.Repository.MerchantStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

       private final MerchantStockRepository merchantStockRepository;

    //    private final ProductService productService;
//    ArrayList<MerchantStock> merchantStockList = new ArrayList<MerchantStock>();




    // -------- CRUD --------

    // get All Merchant Stock
    public List<MerchantStock> getMerchantStock() {
        return merchantStockRepository.findAll();
    }

    // Creat new Merchant Stocks
    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStockRepository.save(merchantStock);
    }

    // Delete Merchant Stock by id
    public boolean removeMerchantStock(Integer id) {
        MerchantStock merchantStock = merchantStockRepository.getById(id);
        if (merchantStock == null) {
            return false;
        }
        merchantStockRepository.deleteById(id);
        return true;
    }


    // update Merchant Stock by id
    public boolean updateMerchantStock(Integer id ,MerchantStock merchantStock) {
        MerchantStock m=merchantStockRepository.getById(id);
        if (m == null) {
            return false;
        }
        m.setMerchantId(merchantStock.getMerchantId());
        m.setProductId(merchantStock.getProductId());
        m.setStock(merchantStock.getStock());

        merchantStockRepository.save(m);
        return true;
    }



                                  // ------ End Point -----

    //user can add more stocks of product to a merchant Stock
    public boolean addStockToMerchant(Integer productId, Integer merchantId, int amount) {
        MerchantStock m1=merchantStockRepository.getById(productId);
        MerchantStock m2=merchantStockRepository.getById(merchantId);

        if (m1 == null || m2 == null) {
            return false;
        }
         m1.setStock(m1.getStock() + amount);
        return true;
    }








//    //  get Product and Merchant by id
//    public MerchantStock getProductAndMerchant(Integer productId, Integer merchantId) {
//        MerchantStock m1=merchantStockRepository.getById(productId);
//        MerchantStock m2=merchantStockRepository.getById(merchantId);
//        if (m1 == null || m2 == null) {
//            return null;
//        }
//        return merchantStockRepository.findAll();
//    }




}
