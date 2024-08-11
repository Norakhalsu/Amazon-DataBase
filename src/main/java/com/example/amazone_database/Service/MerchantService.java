package com.example.amazone_database.Service;

import com.example.amazone_database.Model.Merchant;
import com.example.amazone_database.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;



    // --------- CRUD ---------------


    // get All Merchant
    public List<Merchant> getMerchants() {

        return merchantRepository.findAll();
    }

    // Creat new Merchant
    public void addMerchants(Merchant merchant) {
        merchantRepository.save(merchant);
    }


    // Delete Merchant by id
    public boolean removeMerchant(Integer id ) {
       Merchant m = merchantRepository.getById(id);
       if (m == null) {
           return false;
       }
        merchantRepository.delete(m);
        return true;

    }


    // Update Merchant by id
    public boolean updateMerchant(Integer id ,Merchant merchant) {
        Merchant m=merchantRepository.getById(id);
        if (m == null) {
            return false;
        }
        m.setName(merchant.getName());
        return true;
    }



    // get merchant by id
    public Merchant getMerchantById(Integer id) {
      Merchant m=merchantRepository.getById(id);
      if (m == null) {
      return null;
      }
      return m;
    }

}
