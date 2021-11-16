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
public class TillItem {

    private int count;

    private double total;

    public TillItem(int count, double total) {
        this.count = count;
        this.total = total;
    }

    public TillItem() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
