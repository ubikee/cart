package com.ubikee.cart.commands;

import com.ubikee.fw.Command;

/**
 *
 * @author jeroldan
 */
public class AddProductToCartCommand implements Command  {
    
    public final String cartID;
    public final String productID;
    public final Float measure;
    
    public AddProductToCartCommand(final String cartID, final String productID, Float measure) {
        this.cartID = cartID;
        this.productID = productID;
        this.measure = measure;
    }
    
}
