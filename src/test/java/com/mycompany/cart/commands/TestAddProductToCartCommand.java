package com.mycompany.cart.commands;

import com.ubikee.cart.commands.AddProductToCartCommand;
import com.ubikee.cart.Cart;
import com.ubikee.cart.Stock;
import com.ubikee.cart.handlers.AddProductToCartHandler;
import com.ubikee.cart.repository.CartFactory;
import com.ubikee.cart.repository.CartsInMemoryRepository;
import com.ubikee.fw.CommandResult;
import java.util.Optional;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author jeroldan
 */
@RunWith(MockitoJUnitRunner.class)
public class TestAddProductToCartCommand {

    @Mock
    CartsInMemoryRepository carts;

    @Mock
    Stock stock;

    @InjectMocks
    AddProductToCartHandler handler;

    @Test
    public void testAddProductToCartCommand() throws Exception {
        String cartID = "CARTID";

        Cart cart = CartFactory.create();
        Mockito.when(carts.cart(cartID)).thenReturn(Optional.of(cart));
        AddProductToCartCommand command = new AddProductToCartCommand(cartID, "product1", 1f);
        CommandResult operation = handler.execute(command);
        assertTrue(operation.succeed);
    }
}
