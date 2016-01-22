package com.ubikee.cart;

import com.ubikee.cart.commands.events.ProductAddedEvent;
import com.ubikee.fw.AggregateRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Cart. 
 * The cart contains a single CartItem for any product added.
 * The cart checks product stock before adding it;
 * if product to be added is out of stock throws an IllegalArgumentException.
 * If product already exist the CartItem content is replaced.
 * The cart can check if contains an item by productID
 * The cart can get items by productID The cart can remove items.
 *
 * @author jeroldan
 */
public class Cart extends AggregateRoot {

    public final String id;

    private final List<CartItem> items = new ArrayList();
    private final Stock stock;
    private final Catalog catalog;

    private Float totalAmmount = 0f;

    /**
     *
     * @param id
     * @param stock
     * @param catalog
     */
    public Cart(String id, Stock stock, Catalog catalog) {
        this.id = id;
        this.stock = stock;
        this.catalog = catalog;
    }

    /**
     *
     * @param productID
     * @param measure
     * @throws IllegalArgumentException
     */
    public void addProduct(String productID, float measure) throws IllegalArgumentException {
        if (!stock.isAvailable(productID)) {
            throw new IllegalArgumentException("Product out of stock");
        }
        this.emit(new ProductAddedEvent(productID, measure));
    }

    /**
     * 
     * @param event 
     */
    public void apply(ProductAddedEvent event) {
        Optional<CartItem> item = _getItem(event.productID);
        if (item.isPresent()) {
            item.get().updateMeasure(event.measure);
        } else {
            items.add(new CartItem(event.productID, event.measure));
        }
        this._onChange();
    }

    /**
     *
     * @param productID
     */
    public void removeProduct(String productID) {
        //TODO: emit event and split
        Optional<CartItem> product = _getItem(productID);
        product.ifPresent(theProduct -> items.remove(theProduct));
    }

    private void _onChange() {
        _recalculateTotalAmmount();
    }

    private void _recalculateTotalAmmount() {
        //this.items.stream().collect(Collectors.summingInt(CartItem::get)));
    }

    public boolean contains(String productID) {
        return items.stream().anyMatch((item) -> {
            return item.productID.equals(productID);
        });
    }

    public boolean contains(String productID, Float f) {
        return items.stream().anyMatch((item) -> {
            return item.productID.equals(productID) && item.getMeasure().equals(f);
        });
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    private Optional<CartItem> _getItem(String productID) {
        return items.stream().filter((item) -> {
            return item.productID.equals(productID);
        }).findFirst();
    }

    public Float getTotalAmmount() {
        return totalAmmount;
    }

}
