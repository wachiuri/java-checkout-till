/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.triggerise.challenges.wachiuri;

import org.triggerise.challenges.wachiuri.pricingrule.Bulk;
import org.triggerise.challenges.wachiuri.pricingrule.XFor1;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 *
 * @author timot
 */
public class Triggerise {

    public static void main(String args[]) {

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

        System.out.printf("price %f%n", price.doubleValue());
        
        checkout = new Checkout(pricingRules);
        price = checkout
                .scan("MUG")
                .scan("TSHIRT")
                .scan("MUG").total;

        System.out.printf("price %f%n", price.doubleValue());
        
        checkout = new Checkout(pricingRules);
        price = checkout
                .scan("TSHIRT")
                .scan("TSHIRT")
                .scan("TSHIRT")
                .scan("MUG")
                .scan("TSHIRT").total;

        System.out.printf("price %f%n", price.doubleValue());
         
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

        System.out.printf("price %f%n", price.doubleValue());
    }
}
