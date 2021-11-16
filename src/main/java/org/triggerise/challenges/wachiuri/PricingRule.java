/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.triggerise.challenges.wachiuri;

/**
 *
 * @author timot
 */
public class PricingRule {
    
    private double defaultPrice;

    public PricingRule(double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
    
    public double getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
