package org.example;

import io.javalin.Javalin;
import org.example.Controller.ProductController;
import org.example.Model.Seller;
import org.example.Service.SellerService;
import org.example.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
//        instantiate & inject all dependencies of our project

        SellerService sellerService = new SellerService();
        ProductService productService = new ProductService(sellerService);
        ProductController productController = new ProductController(sellerService, productService);

        Javalin api = productController.getAPI();
        api.start(9004);
    }
}