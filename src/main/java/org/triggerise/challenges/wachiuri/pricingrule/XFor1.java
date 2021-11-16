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
public class XFor1 extends PricingRule {

    private int count;

    public XFor1(double defaultPrice) {
        super(defaultPrice);
    }

    public XFor1(double defaultPrice, int count) {
        super(defaultPrice);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
