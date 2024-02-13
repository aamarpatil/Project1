package org.example.Test;

import org.example.Exception.SellerException;
import org.example.Model.Seller;
import org.example.Service.SellerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SellerServiceTest {
    SellerService sellerService;


    @Before
    public void setup() {
        sellerService = new SellerService();
    }
    @Test
    public void getAllEmpty(){
        List<Seller> sellerList = sellerService.getSellerList();
        Assert.assertTrue(sellerList.isEmpty());
    }


    @Test
    public void insertSeller(){
        Seller testSeller = new Seller();
        testSeller.setName("Ashwini");

        try{
            sellerService.addSeller(testSeller);
        } catch (SellerException e) {
            e.printStackTrace();
            Assert.fail();
        }

        List<Seller> sellerList = sellerService.getSellerList();
        Seller actual = sellerList.get(0);
        Assert.assertEquals("Ashwini", actual.getName());
    }

    @Test
    public void insertDuplicateSeller(){
        Seller testSeller = new Seller();
        testSeller.setName("Ashwini");

        try{
            sellerService.addSeller(testSeller);
            sellerService.addSeller(testSeller);
            Assert.fail();
        } catch (SellerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertEmptySeller(){
        Seller testSeller = new Seller();
        testSeller.setName("");

        try{
            sellerService.addSeller(testSeller);
            Assert.fail();
        } catch (SellerException e) {
            e.printStackTrace();
        }
    }
}