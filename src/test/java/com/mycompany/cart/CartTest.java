package com.mycompany.cart;

import com.ubikee.cart.Product;
import com.ubikee.cart.Stock;
import com.ubikee.cart.Cart;
import com.ubikee.cart.Catalog;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 *
 * @author jeroldan
 */
@RunWith(MockitoJUnitRunner.class)
public class CartTest {

    final String PRODUCT_ID_1 = "PRODUCT_1";

    @Spy Stock stock;
    @Spy Catalog catalog;

    @InjectMocks
    Cart cart;

    @Test
    public void shouldBeEmpty() throws Exception {
        assertTrue(cart.isEmpty());
    }

    /*
     * The cart contains a single CartItem for any product added.
     */
    @Test
    public void shouldAddProduct() throws Exception {
        // given
        assertTrue(cart.isEmpty());
        
        // when
        cart.addProduct(PRODUCT_ID_1, 1);
        
        // then
        assertFalse(cart.isEmpty());
    }

    /*
     * The cart can check if contains an item by productID
     */
    @Test
    public void shouldCheckIfContainsProduct() throws Exception {

        String notAddedProductID = "notAddedProductID";
        cart.addProduct(PRODUCT_ID_1, 1);

        assertTrue("product not found in cart", cart.contains(PRODUCT_ID_1));
        assertFalse("found product not previously added", cart.contains(notAddedProductID));
    }
    
    /*
     * The cart can get items by productID
     */
    @Test
    public void shouldGetCartITemByProductID() throws Exception {
        // when
        cart.addProduct(PRODUCT_ID_1, 1);
        
        // then
        assertTrue(cart.contains(PRODUCT_ID_1));
    }

    /*
     * If product already exist the old CartItem is replaced with the new one.
     */
    @Test
    public void shouldReplaceProduct() throws Exception {
        //given
        cart.addProduct(PRODUCT_ID_1, 1);
        
        //when
        cart.addProduct(PRODUCT_ID_1, 5);

        //then    
        assertTrue(cart.contains(PRODUCT_ID_1, 5f));
    }

    /*
     * The cart checks product stock before adding it
     * if product to be added is out of stock throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddOutOfStockProducts() throws Exception {
        //given
        String UNAVAILABLE_PRODUCT_ID_1 = "UNAVAILABLE_PRODUCT_ID_1";
        when(stock.isAvailable(UNAVAILABLE_PRODUCT_ID_1)).thenReturn(false);
        
        //when
        cart.addProduct(UNAVAILABLE_PRODUCT_ID_1, 1);
        
        //then should throw IllegalArgumentException
    }

    @Test
    public void shouldRemoveProduct() throws Exception {
        //given
        cart.addProduct(PRODUCT_ID_1, 1f);
        assertFalse(cart.isEmpty());
        
        //when
        cart.removeProduct(PRODUCT_ID_1);
        
        //then
        assertTrue(cart.isEmpty());
    }
    
    @Test
    public void shouldRecalculateTotals_OnProductAdded() throws Exception {
        //given
        when(catalog.product(PRODUCT_ID_1)).thenReturn(new Product(PRODUCT_ID_1, 10f));
        
        //when
        cart.addProduct(PRODUCT_ID_1, 1f);
        
        //then
        assertTrue(cart.getTotalAmmount().equals(10f));
    }
    
}
