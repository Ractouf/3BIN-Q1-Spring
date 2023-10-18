package be.vinci.ipl.gateway;

import be.vinci.ipl.gateway.data.AuthenticationProxy;
import be.vinci.ipl.gateway.data.ProductsProxy;
import be.vinci.ipl.gateway.data.UsersProxy;
import be.vinci.ipl.gateway.data.WishlistsProxy;
import be.vinci.ipl.gateway.models.Product;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {
    private final ProductsProxy productsProxy;
    private final UsersProxy usersProxy;
    private final WishlistsProxy wishlistsProxy;
    private final AuthenticationProxy authenticationProxy;

    public GatewayService(ProductsProxy productsProxy, UsersProxy usersProxy, WishlistsProxy wishlistsProxy, AuthenticationProxy authenticationProxy) {
        this.productsProxy = productsProxy;
        this.usersProxy = usersProxy;
        this.wishlistsProxy = wishlistsProxy;
        this.authenticationProxy = authenticationProxy;
    }

    public Iterable<Product> readAllProducts() {
        return productsProxy.readAll();
    }

    public Product readOneProduct(int id) {
        ResponseEntity<Product> response =  productsProxy.readOne(id);
        System.out.println(response);
        return response.getBody();
    }
}
