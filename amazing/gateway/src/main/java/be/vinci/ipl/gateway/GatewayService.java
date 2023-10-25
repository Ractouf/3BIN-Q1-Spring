package be.vinci.ipl.gateway;

import be.vinci.ipl.gateway.data.AuthenticationProxy;
import be.vinci.ipl.gateway.data.ProductsProxy;
import be.vinci.ipl.gateway.data.UsersProxy;
import be.vinci.ipl.gateway.data.WishlistsProxy;
import be.vinci.ipl.gateway.exceptions.BadRequestException;
import be.vinci.ipl.gateway.exceptions.ConflictException;
import be.vinci.ipl.gateway.exceptions.NotFoundException;
import be.vinci.ipl.gateway.models.Product;
import be.vinci.ipl.gateway.models.UnsafeCredentials;
import be.vinci.ipl.gateway.models.User;
import feign.FeignException;
import org.springframework.http.HttpStatus;
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

    public Product readOneProduct(int id) throws NotFoundException {
        Product product;

        try {
            product = productsProxy.readOne(id).getBody();
        } catch (FeignException e) {
            if (e.status() == 404) throw new NotFoundException();
            else throw e;
        }

        return product;
    }

    public ResponseEntity<Void> createOne(UnsafeCredentials unsafeCredentials, User user) throws BadRequestException, ConflictException, NotFoundException {
        try {
            usersProxy.createOne(unsafeCredentials.getPseudo(), user);
        } catch (FeignException e) {
            if (e.status() == 400) throw new BadRequestException();
            else if (e.status() == 409) throw new ConflictException();
            else throw e;
        }

        try {
            authenticationProxy.createUser(unsafeCredentials);
        } catch (FeignException e) {
            try {
                usersProxy.deleteOne(unsafeCredentials.getPseudo());
            } catch (FeignException ex) {
                if (e.status() == 404) throw new NotFoundException();
            }

            if (e.status() == 400) throw new BadRequestException();
            else if (e.status() == 409) throw new ConflictException();
            else throw e;
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
