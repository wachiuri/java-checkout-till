/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.triggerise.challenges.wachiuri;

import java.math.BigDecimal;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.triggerise.challenges.wachiuri.pricingrule.Bulk;
import org.triggerise.challenges.wachiuri.pricingrule.XFor1;

/**
 *
 * @author timot
 */
public class CheckoutTest {
    
    @Test
    public void checkoutTest(){
        HashMap<String, PricingRule> pricingRules = new HashMap<>();

        pricingRules.put("TSHIRT", new Bulk(21.00, 3, 30));
        pricingRules.put("MUG", new XFor1(4.00, 2));
        pricingRules.put("USBKEY", new PricingRule(10.00));

        Checkout checkout;
        BigDecimal price;

        
        checkout = new Checkout(pricingRules);
        price = checkout
                .scan("MUG")
                .scan("USBKEY")
                .scan("TSHIRT").total;

        assertEquals(35.00, price.doubleValue());
        
        checkout = new Checkout(pricingRules);
        price = checkout
                .scan("MUG")
                .scan("TSHIRT")
                .scan("MUG").total;

        assertEquals(25.00, price.doubleValue());
        
        checkout = new Checkout(pricingRules);
        price = checkout
                .scan("TSHIRT")
                .scan("TSHIRT")
                .scan("TSHIRT")
                .scan("MUG")
                .scan("TSHIRT").total;

        assertEquals(62.80, price.doubleValue());
         
        checkout = new Checkout(pricingRules);
        price = checkout
                .scan("MUG")
                .scan("TSHIRT")
                .scan("MUG")
                .scan("MUG")
                .scan("USBKEY")
                .scan("TSHIRT")
                .scan("TSHIRT")
                .total;

        assertEquals(62.10, price.doubleValue());
    }
}
