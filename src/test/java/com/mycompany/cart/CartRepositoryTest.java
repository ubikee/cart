package com.mycompany.cart;

import com.ubikee.cart.Cart;
import com.ubikee.cart.repository.CartFactory;
import com.ubikee.cart.repository.CartsInMemoryRepository;
import org.junit.Test;

/**
 *
 * @author jeroldan
 */
public class CartRepositoryTest {
    
    CartsInMemoryRepository repository = new CartsInMemoryRepository();
    
    @Test
    public void testSave() throws Exception {
        //given
        Cart cart = new CartFactory().create();
        cart.addProduct("p1", 1f);
        
        //when
        repository.save(cart);
    }

    
}
