package com.ubikee.cart.repository;

import com.ubikee.cart.Cart;
import com.ubikee.cart.Catalog;
import com.ubikee.cart.Stock;
import java.util.UUID;

/**
 *
 * @author jeroldan
 */
public class CartFactory {
    
    public static Cart create() {
        String cartID = UUID.randomUUID().toString();
        return new Cart(cartID, new Stock(), new Catalog());
    }
}
