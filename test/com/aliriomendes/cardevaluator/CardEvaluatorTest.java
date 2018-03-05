/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aliriomendes.cardevaluator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aliriomendes
 */
public class CardEvaluatorTest {
    
    

    @Test
    public void testIsCardNumberValidLong() {
        assertEquals(CardEvaluator.isCardNumberValidLong(""), false);
        assertEquals(CardEvaluator.isCardNumberValidLong("q"), false);
        assertEquals(CardEvaluator.isCardNumberValidLong("4929804463622139"), true);
    }
    
    @Test
    public void testIsCardNumberLengthValid() {
        assertEquals(CardEvaluator.isCardNumberLengthValid(""), false);
        assertEquals(CardEvaluator.isCardNumberLengthValid("q"), false);
        assertEquals(CardEvaluator.isCardNumberLengthValid("49298044636"), false);
        assertEquals(CardEvaluator.isCardNumberLengthValid("49298044636221394343"), false);
        assertEquals(CardEvaluator.isCardNumberLengthValid("4929804463622139"), true);
    }
    
    @Test
    public void testCardNumberPassesLuhnCheck() {
        assertEquals(CardEvaluator.cardNumberPassesLuhnCheck("49298044636221394343"), false);
        assertEquals(CardEvaluator.cardNumberPassesLuhnCheck("4929804463622139"), true);   
    }
    
    @Test
    public void testCheckCardBrand() {
        
        assertEquals(CardEvaluator.checkCardBrand("04929804463622139"), CardBrand.NOT_VALID);
        assertEquals(CardEvaluator.checkCardBrand("4929804463622139"), CardBrand.VISA);  
    }
}