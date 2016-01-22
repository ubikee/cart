package com.ubikee.cart;

/**
 * CartItem.
 *
 *
 * @author jeroldan
 */
class CartItem {

    public final String productID;
    private Float measure;

    CartItem(String productID, Float measure) {
        this.productID = productID;
        this.measure = measure;
    }

    public Float getMeasure() {
        return this.measure;
    }

    public void updateMeasure(Float measure) {
        this.measure = measure;
    }
}
