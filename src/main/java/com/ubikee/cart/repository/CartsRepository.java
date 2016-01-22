package com.ubikee.cart.repository;

import com.ubikee.cart.Cart;
import java.util.Optional;

/**
 *
 * @author jeroldan
 */
public interface CartsRepository {

    public Optional<Cart> cart(String cartID);

    public void add(Cart cart);
    
    public void save(Cart cart);
}
