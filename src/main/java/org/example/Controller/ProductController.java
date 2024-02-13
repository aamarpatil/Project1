package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import org.example.Exception.ProductException;
import org.example.Model.Seller;
import org.example.Model.Product;
import org.example.Service.SellerService;
import org.example.Service.ProductService;

import java.util.List;

import static java.lang.Integer.parseInt;

public class ProductController {

    SellerService sellerService;
    ProductService productService;

    public ProductController(SellerService sellerService, ProductService productService){
        this.sellerService = sellerService;
        this.productService = productService;
    }

    public Javalin getAPI(){
        Javalin api = Javalin.create();

        api.get("health", context -> {context.result("Server is UP");});
//        a get for both product and seller
//        a post for both seller and product
        api.get("seller", context -> {
            List<Seller> sellerList = sellerService.getSellerList();
            context.json(sellerList);
        });
        api.get("product", context -> {
            List<Product> productList = productService.getProductList();
            context.json(productList);
        });
        api.post("seller", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Seller a = om.readValue(context.body(), Seller.class);
                sellerService.addSeller(a);
                context.status(201);
            }catch(JsonProcessingException e){
                context.status(400);
            }
        });
        api.post("product", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Product p = om.readValue(context.body(), Product.class);
                Product newProduct = productService.addProduct(p);
                context.status(201);
                context.json(newProduct);
            }catch(JsonProcessingException e){
                context.status(400);
            }catch(ProductException e){
                context.result(e.getMessage());
                context.status(400);
            }
        });
        /**
         * case 1: the product id is found
         *  - respond with the product JSON status 200
         *  case 2: the product is not found
         *  - respond with no body status 404
         */
        api.get("product/{id}", context -> {
            int id = parseInt(context.pathParam("id"));
            Product p = productService.getProductById(id);
            if(p == null){
                context.status(404);
                context.json("Product not found");
            }else{
                context.json(p);
                context.status(200);
                context.json("Product  found");
            }
        });

        api.delete("product/{id}", context -> {
            int id = parseInt(context.pathParam("id"));
            Product p = productService.deletebyProductId(id);
            if (p == null) {
                context.status(404);
                context.json("Product not found");
            } else {
                context.json(p);
                context.status(200);
                context.json("Product removed");
            }
        });
        return api;
    }


}