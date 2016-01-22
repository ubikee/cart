package com.ubikee.customer;

import com.ubikee.fw.AggregateRoot;

/**
 *
 * @author jeroldan
 */
public class Customer extends AggregateRoot {

    public final String id;
    
    public Customer(final String id) {
        this.id = id;
    }
    
}
