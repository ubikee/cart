package com.mycompany.customer;

import com.ubikee.customer.Customer;
import com.ubikee.customer.repository.CustomersGraphRepository;
import com.ubikee.customer.repository.CustomersInMemRepository;
import com.ubikee.customer.repository.CustomersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
/**
 *
 * @author jeroldan
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerTest {
 
    CustomersRepository customers = new CustomersInMemRepository();
    
    @Test
    public void shouldRegisterCustomer() throws Exception {
        String CUSTOMER_ID = "customer1";
        Customer customer = new Customer(CUSTOMER_ID);
        customers.register(customer);
        assertTrue(true);
    }
    
}
