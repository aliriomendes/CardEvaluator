/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aliriomendes.cardevaluator;

/**
 *
 * @author aliriomendes
 */

public enum CardBrand { 
    NOT_VALID("Not valid"), VISA("Visa"), MAESTRO("Maestro"), MASTERCARD("Mastercard"), CHINA_UNION_PAY("China Union Pay");

    private final String text;

    /**
     * @param text
     */
    CardBrand(final String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return text;
    }
}
