/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aliriomendes.cardevaluator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aliriomendes
 */
public class CardTest {
    private List<Card> cardList;
    private final Card card1 = new Card("4929804463622139");
    private final Card card2 = new Card("4929804463622138");
    private final Card card3 = new Card("6762765696545485");
    private final Card card4 = new Card("5212132012291762");
    private final Card card5 = new Card("6210948000000029");
    
    public CardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         cardList = Arrays.asList(card1, card2, card3, card4, card5);
    }
    
    @After
    public void tearDown() {
    }

   
    @Test
    public void testCard() {
        assertEquals(card1.getBrand(), CardBrand.VISA);
        assertEquals(card1.isValid(), true);
        assertEquals(card2.getBrand(), CardBrand.VISA);
        assertEquals(card2.isValid(), false);
        assertEquals(card3.getBrand(), CardBrand.MAESTRO);
        assertEquals(card3.isValid(), true);
        assertEquals(card4.getBrand(), CardBrand.MASTERCARD);
        assertEquals(card4.isValid(), false);
        assertEquals(card5.getBrand(), CardBrand.CHINA_UNION_PAY);
        assertEquals(card5.isValid(), true);
    }
    
    @Test
    public void testCardFilters() {
        List<Card> cardValidList = cardList.stream().filter(card -> card.isValid()).collect(Collectors.toList());
        assertEquals(cardValidList.size(), 3);
        
        List<Card> visaCardList = cardList.stream().filter(card -> card.getBrand() == CardBrand.VISA).collect(Collectors.toList());
        assertEquals(visaCardList.size(), 2);
    }
}
