package org.example.Service;

import org.example.Exception.SellerException;
import org.example.Main;
import org.example.Model.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SellerService {

    List<Seller> sellerList;
    List<String> sellerNames;

    public SellerService(){
        this.sellerList = new ArrayList<>();
        this.sellerNames = new ArrayList<>();
    }

    public List<Seller> getSellerList(){
        Main.log.info("Seller List returned: " + sellerList);
        return sellerList;
    }

    public void addSeller(Seller a) throws SellerException {
        if(a.getName().isEmpty()){
            Main.log.warn("Seller name is empty");
            throw new SellerException("Seller name is empty");
        }
        if(isDuplicate(a.getName())){
            Main.log.warn("Seller already exists: " + a.getName());
            throw new SellerException("Seller already exists: " + a.getName());
        }
        sellerList.add(a);
        sellerNames.add(a.getName());
        Main.log.info("Seller added: " + a);
    }
    public boolean isDuplicate (String name) {
        for (String seller : sellerNames) {
            if (name.equalsIgnoreCase(seller)) return true;
        }
        return false;
    }
}