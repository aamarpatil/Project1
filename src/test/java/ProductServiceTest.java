
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductServiceTest {
    ProductService productService;
    SellerService sellerService;
    Seller testSeller;


    @Before
    public void setup() throws SellerException {
        sellerService = new SellerService();
        productService = new ProductService(sellerService);
    }

    @Test
    public void ProductServiceemptyAtStart(){
        List<Product> productList = productService.getProductList();
        Assert.assertTrue(productList.isEmpty());
    }

    @Test
    public void addProduct(){
        String productdesc = "Mower";
        int price = 11;
        String sellerName = "Ashwini";

        Product testProduct = new Product();
        testProduct.setProductdesc(productdesc);
        testProduct.setPrice(price);
        testProduct.setSellerName(sellerName);

        try{
            sellerService.addSeller(new Seller(sellerName));
            productService.addProduct(testProduct);
        } catch (ProductException e) {
            e.printStackTrace();
            Assert.fail("Exception should not be thrown");
        }
        catch (SellerException s) {
            s.printStackTrace();
            Assert.fail("Exception should not be thrown");
        }

        List<Product> productList = productService.getProductList();
        Product actual = productList.get(0);
        Assert.assertTrue(actual.getId() > 0);
        Assert.assertEquals(productdesc, actual.getProductdesc());
        Assert.assertEquals(price, actual.getPrice());
        Assert.assertEquals(sellerName, actual.getSellerName());
    }

    @Test
    public void getProductById(){
        addProduct();

        List<Product> productList = productService.getProductList();
        int id = (int) productList.get(0).getId();

        productService.getProductById(id);
    }

    @Test
    public void updateProduct(){

        String name = "SnowBlower";
        int price = 1500;
        String sellerName = "John Deere";

        Product testProduct = new Product();
        testProduct.setProductdesc(name);
        testProduct.setPrice(price);
        testProduct.setSellerName(sellerName);

        addProduct();

        List<Product> productList = productService.getProductList();
        int id = (int) productList.get(0).getId();

        try{
            sellerService.addSeller(new Seller(sellerName));
            productService.updateProduct(id,testProduct);

        } catch (ProductException e) {
            e.printStackTrace();
            Assert.fail();
        }
        catch (SellerException s) {
            s.printStackTrace();
            Assert.fail("Exception should not be thrown");
        }


        Product actual = productList.get(0);
        Assert.assertTrue(actual.getId() > 0);
        Assert.assertEquals(name, actual.getProductdesc());
        Assert.assertEquals(price, actual.getPrice());
        Assert.assertEquals(sellerName, actual.getSellerName());
    }

    @Test
    public void updateProductEmptyName(){

        String name = "";
        Integer price = 15;
        String sellerName = "Blue Sky";

        Product testProduct = new Product();
        testProduct.setProductdesc(name);
        testProduct.setPrice(price);
        testProduct.setSellerName(sellerName);

        addProduct();

        List<Product> productList = productService.getProductList();
        int id = (int) productList.get(0).getId();

        try{
            productService.updateProduct(id,testProduct);
            Assert.fail();

        } catch (ProductException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateProductPriceLessThanZero(){

        String name = "Mower";
        Integer price = -11;
        String sellerName = "Ashwini";

        Product testProduct = new Product();
        testProduct.setProductdesc(name);
        testProduct.setPrice(price);
        testProduct.setSellerName(sellerName);

        addProduct();

        List<Product> productList = productService.getProductList();
        int id = (int) productList.get(0).getId();

        try{
            productService.updateProduct(id,testProduct);
            Assert.fail();

        } catch (ProductException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateProductSellerWrong(){

        String name = "Mower";
        Integer price = 11;
        String sellerName = "Pelle";

        Product testProduct = new Product();
        testProduct.setProductdesc(name);
        testProduct.setPrice(price);
        testProduct.setSellerName(sellerName);

     addProduct();

        List<Product> productList = productService.getProductList();
        int id = (int) productList.get(0).getId();

        try{
            productService.updateProduct(id,testProduct);
            Assert.fail();

        } catch (ProductException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteProduct(){
        addProduct();

        List<Product> productList = productService.getProductList();
        int id = (int) productList.get(0).getId();

        productService.deletebyProductId(id);

        Assert.assertTrue(productList.isEmpty());
    }
}