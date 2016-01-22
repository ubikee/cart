package com.ubikee.cart.handlers;

import com.ubikee.cart.Cart;
import com.ubikee.cart.commands.AddProductToCartCommand;
import com.ubikee.cart.repository.CartsRepository;
import com.ubikee.fw.CommandHandler;
import com.ubikee.fw.CommandResult;
import java.util.Optional;

/**
 *
 * @author jeroldan
 */
public class AddProductToCartHandler implements CommandHandler<AddProductToCartCommand> {

    CartsRepository carts;
    
    public AddProductToCartHandler(CartsRepository carts) {
        this.carts = carts;
    }
    
    @Override
    public CommandResult execute(AddProductToCartCommand command) {
        CommandResult result;
        Optional<Cart> cart = carts.cart(command.cartID);
        if (cart.isPresent()) {
            Cart theCart = cart.get();
            theCart.addProduct(command.productID, command.measure);
            result = new CommandResult(CommandResult.SUCCEED);
            carts.save(theCart);
        } else {
            result = new CommandResult(false);
        }
        return result;
    }
}