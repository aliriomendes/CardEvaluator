package com.aliriomendes.cardevaluator;

/**
 *
 * @author aliriomendes
 */
public class Card {
    
    private CardBrand brand = CardBrand.NOT_VALID;
    
    private String cardHolder;

    private String cardNumber;

    private String validityDate;
    
    private boolean valid;

    public Card() {
    }
    
    /**
     * @param cardNumber
     */
    public Card(String cardNumber) {
        this.cardNumber = cardNumber;
        this.validate();
    }
    
    /**
     * Get the value of brand
     *
     * @return the value of brand
     */
    public CardBrand getBrand() {
        return brand;
    }
    
    /**
     * Get the value of cardHolder
     *
     * @return the value of cardHolder
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     * Set the value of cardHolder
     *
     * @param cardHolder new value of cardHolder
     */
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    /**
     * Get the value of cardNumber
     *
     * @return the value of cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Set the value of cardNumber
     *
     * @param cardNumber new value of cardNumber
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        this.validate();
    }
    
    /**
     * Get the value of validityDate
     *
     * @return the value of validityDate
     */
    public String getValidityDate() {
        return validityDate;
    }

    /**
     * Set the value of validityDate
     *
     * @param validityDate new value of validityDate
     */
    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    /**
     * Get the value of valid
     *
     * @return the value of valid
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * Validates card's number and brand, and sets it's properties 
     * 
     */
    private void validate() {
        this.valid = CardEvaluator.isCardNumberValidLong(this.cardNumber) && CardEvaluator.isCardNumberLengthValid(this.cardNumber) && CardEvaluator.cardNumberPassesLuhnCheck(this.cardNumber);
        
        this.brand = CardEvaluator.checkCardBrand(this.cardNumber);
        
        this.valid &= this.brand != CardBrand.NOT_VALID;
    }
}
