package org.example.Model;

import java.util.Objects;

public class Product {
    public long id;
    public String productdesc;
    public int price;
    public String sellerName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product(){

    }
    public Product(String productdesc, String sellerName) {
        this.productdesc = productdesc;
        this.sellerName = sellerName;
    }

    public String getProductdesc() {
        return productdesc;
    }
    public int getPrice() {
        return price;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productdesc, product.productdesc) && Objects.equals(sellerName, product.sellerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productdesc, sellerName);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id" + id +
                ", product name/desc='" + productdesc + '\'' +
                ", price" + price +
                ", sellerName='" + sellerName + '\'' +
                '}';
    }
}