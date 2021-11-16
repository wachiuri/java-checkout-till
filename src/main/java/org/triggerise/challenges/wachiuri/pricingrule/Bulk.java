/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.triggerise.challenges.wachiuri.pricingrule;

import org.triggerise.challenges.wachiuri.PricingRule;

/**
 *
 * @author timot
 */
public class Bulk extends PricingRule {
    
    private int quantity;
    
    private double discountPercentage;

    public Bulk(double defaultPrice) {
        super(defaultPrice);
    }

    public Bulk(double defaultPrice,int quantity, double discountPercentage) {
        super(defaultPrice);
        this.quantity = quantity;
        this.discountPercentage = discountPercentage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    
    
}
