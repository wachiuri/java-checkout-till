/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.triggerise.challenges.wachiuri;

import org.triggerise.challenges.wachiuri.pricingrule.Bulk;
import org.triggerise.challenges.wachiuri.pricingrule.XFor1;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 *
 * @author timot
 */
public class Checkout {

    private final HashMap<String, PricingRule> pricingRules;
    private final HashMap<String, TillItem> items = new HashMap<>();
    private double totalDouble = 0.00;
    public BigDecimal total = new BigDecimal(this.totalDouble).setScale(0, RoundingMode.CEILING);

    public Checkout(HashMap<String, PricingRule> pricingRules) {
        this.pricingRules = pricingRules;
    }
    private static final Logger logger = Logger.getLogger(Checkout.class.getName());

    public Checkout scan(String itemName) {
        TillItem tillItem;
        if (this.items.containsKey(itemName)) {
            tillItem = this.items.get(itemName);
            tillItem.setCount(tillItem.getCount() + 1);
        } else {
            PricingRule pricingRule = this.pricingRules.get(itemName);
            tillItem = new TillItem(1, pricingRule.getDefaultPrice());
            this.items.put(itemName, tillItem);
            this.totalDouble += pricingRule.getDefaultPrice();
            this.total = new BigDecimal(this.totalDouble).setScale(2, RoundingMode.CEILING);
            return this;
        }

        PricingRule pricingRule = this.pricingRules.get(itemName);

        if (pricingRule instanceof Bulk) {
            Bulk bulkPricingRule = (Bulk) pricingRule;
            double grossTotal = tillItem.getCount() * bulkPricingRule.getDefaultPrice();
            if (tillItem.getCount() >= bulkPricingRule.getQuantity()) {
                tillItem.setTotal(grossTotal * ((100 - bulkPricingRule.getDiscountPercentage()) / 100));
            } else {
                tillItem.setTotal(grossTotal);
            }
        } else if (pricingRule instanceof XFor1) {
            XFor1 xFor1PricingRule = (XFor1) pricingRule;
            if (tillItem.getCount() >= xFor1PricingRule.getCount()) {
                int payFor = tillItem.getCount() / xFor1PricingRule.getCount();
                int remainder = tillItem.getCount() % xFor1PricingRule.getCount();
                double payForAmount = payFor * xFor1PricingRule.getDefaultPrice();
                double remainderAmount = remainder * xFor1PricingRule.getDefaultPrice();
                tillItem.setTotal(payForAmount + remainderAmount);
            } else {
                tillItem.setTotal(tillItem.getCount() * xFor1PricingRule.getDefaultPrice());
            }
        } else {
            tillItem.setTotal(tillItem.getCount() * pricingRule.getDefaultPrice());
            this.totalDouble += tillItem.getTotal();
            this.total = new BigDecimal(this.totalDouble).setScale(2, RoundingMode.CEILING);
            return this;
        }

        this.totalDouble = 0.00;

        this.items.values().forEach(item -> {
            this.totalDouble += item.getTotal();
        });

        this.total = new BigDecimal(this.totalDouble).setScale(2, RoundingMode.CEILING);

        return this;
    }

    public Checkout printItems() {

        for (String itemName : this.items.keySet()) {
            TillItem item = this.items.get(itemName);
            System.out.printf("%s %d %f %n", itemName, item.getCount(), item.getTotal());
        }

        System.out.println("===============");

        return this;
    }
}
