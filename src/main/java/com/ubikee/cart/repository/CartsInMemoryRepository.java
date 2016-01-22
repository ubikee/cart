package com.ubikee.cart.repository;

import com.ubikee.cart.Cart;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author jeroldan
 */
public class CartsInMemoryRepository implements CartsRepository {

    private final Map<String, Cart> carts = new HashMap();

    @Override
    public void add(Cart cart) {
        this.carts.put(cart.id, cart);
    }
    
    @Override
    public Optional<Cart> cart(String cartID) {
        Cart cart = this.carts.get(cartID);
        return Optional.of(cart);
    }

    @Override
    public void save(Cart cart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
