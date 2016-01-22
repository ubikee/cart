package com.ubikee.cart.commands.events;

import com.ubikee.fw.Event;

/**
 *
 * @author jeroldan
 */
public class ProductAddedEvent extends Event {

    public final String productID;
    public final float measure;
    
    public ProductAddedEvent(String productID, float measure) {
        this. productID = productID;
        this.measure = measure;
    }
    
}
