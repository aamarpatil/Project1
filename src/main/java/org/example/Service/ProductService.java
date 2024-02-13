package org.example.Service;

import org.example.Exception.ProductException;
import org.example.Main;
import org.example.Model.Product;
import java.util.ArrayList;
import java.util.List;


public class ProductService {
    List<Product> productList;
    public  SellerService sellerService;

    public ProductService(SellerService sellerService){
        this.sellerService = sellerService;
        productList = new ArrayList<>();
    }

/*    public ProductService() {
    }
    */

    public List<Product> getProductList(){
        Main.log.info("Product List returned: " + productList);
        return productList;
    }
    public Product addProduct(Product product) throws ProductException {
        int id = (int) (Math.random() * Integer.MAX_VALUE);
        product.setId(id);
        String sellerName = product.getSellerName();
        if (product.getProductdesc() == null || product.getProductdesc().isEmpty()) {
            Main.log.warn("Product name/desc is empty");
            throw new ProductException("Product name/desc is empty");
        }
        else if (product.getPrice() <= 0) {
            Main.log.warn("Product price is less than or equal to 0");
            throw new ProductException("Product price is less than or equal to 0");
        }
        else if (!SellerCheck(sellerName)) {
            Main.log.warn("Seller " + sellerName + " is not the right Seller");
            throw new ProductException("Seller " + sellerName + " is not the right Seller");
        }
        productList.add(product);
        Main.log.info("Product added: " + product.toString());
        return product;
    }

    public Product getProductById(int id) {
        for(int i = 0; i < productList.size(); i++){
            Product currentProduct = productList.get(i);
            if(currentProduct.getId() == id){
                return currentProduct;
            }
        }
        return null;
    }
    private boolean SellerCheck(String sellerName){
        return sellerService.isDuplicate(sellerName);
    }
    public Product deletebyProductId(int id){
        for(int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (currentProduct.getId() == id) {
                productList.remove(i);
            }
        }
        return null;
    }
    public Product updateProduct(int id, Product product) throws ProductException {
        String sellerName = product.getSellerName();
        if(product.getProductdesc() == null || product.getProductdesc().isEmpty()){
            Main.log.warn("Product name/desc is empty");
            throw new ProductException("Product name is empty");
        }
        if(product.getPrice() <= 0){
            Main.log.warn("Product price is less than or equal to 0");
            throw new ProductException("Product price is less than or equal to 0");
        }
        if(!SellerCheck(sellerName)){
            Main.log.warn("Seller " + sellerName + " is not the right Seller");
            throw new ProductException("Seller " + sellerName + " is not the right Seller");
        }

        Product productToUpdate = getProductById(id);
        productToUpdate.setProductdesc(product.getProductdesc());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setSellerName(product.getSellerName());

        Main.log.info("Product updated. New values: " + productToUpdate);
        return productToUpdate;
    }

}