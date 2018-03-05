package com.aliriomendes.cardevaluator;

/**
 *
 * @author aliriomendes
 */
public final class CardEvaluator {
    private static final String DIGIT_REGEX = "[1-9][0-9]+";
    
    private static final int VISA_DELIMITER_LOW = 400000;
    private static final int VISA_DELIMITER_HIGH = 499999;
    
    private static final int MASTERCARD_DELIMITER_LOW_ONE = 222100;
    private static final int MASTERCARD_DELIMITER_HIGH_ONE = 272099;
    
    private static final int MASTERCARD_DELIMITER_LOW_TWO = 510000;
    private static final int MASTERCARD_DELIMITER_HIGH_TWO = 559999;
    
    private static final int MAESTRO_DELIMITER_LOW_ONE = 500000;
    private static final int MAESTRO_DELIMITER_HIGH_ONE = 509999;
    
    private static final int MAESTRO_DELIMITER_LOW_TWO = 560000;
    private static final int MAESTRO_DELIMITER_HIGH_TWO = 699999;
    
    private static final int CHINA_UNION_PAY_DELIMITER_LOW = 620000;
    private static final int CHINA_UNION_PAY_DELIMITER_HIGH = 629999;

    /**
     * Checks if the card number matches digits regular expression
     * 
     * @param cardNumber
     *           {@link String} card number
     * @return result {@link boolean} true of false
     */
    public static boolean isCardNumberValidLong(String cardNumber){
        return cardNumber.matches(DIGIT_REGEX);
    }
    
    /**
     * Checks if the card number has between 12 and 19 digits inclusive
     * 
     * @param cardNumber
     *           {@link String} card number
     * @return result {@link boolean} true of false
     */
    public static boolean isCardNumberLengthValid(String cardNumber){
        return cardNumber.length() >= 12 && cardNumber.length() <= 19;
    }
    
    /**
     * Checks if the card is valid
     * 
     * @param cardNumber
     *           {@link String} card number
     * @return result {@link boolean} true of false
     */
    public static boolean cardNumberPassesLuhnCheck(String cardNumber) {
        if (cardNumber == null)
            return false;
        char checkDigit = cardNumber.charAt(cardNumber.length() - 1);
        String digit = calculateCheckDigit(cardNumber.substring(0, cardNumber.length() - 1));
        return checkDigit == digit.charAt(0);
    }
    
    /**
     * Calculates the last digits for the card number received as parameter
     * 
     * @param cardNumber
     *           {@link String} number
     * @return {@link String} the check digit
     */
    private static String calculateCheckDigit(String cardNumber) {
        if (cardNumber == null)
            return null;
        String digit;
        /* convert to array of int*/
        int[] digits = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            digits[i] = Character.getNumericValue(cardNumber.charAt(i));
        }
        
        /* double every other starting from right - jumping from 2 in 2 */
        for (int i = digits.length - 1; i >= 0; i -= 2)    {
            digits[i] += digits[i];
            
            /* taking the sum of digits grater than 10 - simple trick by substract 9 */
            
            if (digits[i] >= 10) {
                digits[i] = digits[i] - 9;
            }
        }
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i];
        }
        /* multiply by 9 step */
        sum = sum * 9;
        
        /* convert to string to be easier to take the last digit */
        digit = sum + "";
        return digit.substring(digit.length() - 1);
    }
    
    /**
     * Checks the Card brand for the card number received as parameter
     * 
     * @param cardNumber
     *           {@link String} number
     * @return {@link CardBrand} the brand of the card
     */
    public static CardBrand checkCardBrand(String cardNumber) {
        if (cardNumber == null)
            return CardBrand.NOT_VALID;
        int brandIdentifier = -1;
        try{
            brandIdentifier = Integer.parseInt(cardNumber.substring(0,6));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        if(brandIdentifier >= VISA_DELIMITER_LOW && brandIdentifier <=  VISA_DELIMITER_HIGH)
            return CardBrand.VISA;
        
        if(brandIdentifier >= MASTERCARD_DELIMITER_LOW_ONE && brandIdentifier <=  MASTERCARD_DELIMITER_HIGH_ONE || brandIdentifier >= MASTERCARD_DELIMITER_LOW_TWO && brandIdentifier <= MASTERCARD_DELIMITER_HIGH_TWO)
            return CardBrand.MASTERCARD;
        
        if(brandIdentifier >= CHINA_UNION_PAY_DELIMITER_LOW && brandIdentifier <=  CHINA_UNION_PAY_DELIMITER_HIGH)
            return CardBrand.CHINA_UNION_PAY;
        
        if(brandIdentifier >= MAESTRO_DELIMITER_LOW_ONE && brandIdentifier <=  MAESTRO_DELIMITER_HIGH_ONE || brandIdentifier >= MAESTRO_DELIMITER_LOW_TWO && brandIdentifier <= MAESTRO_DELIMITER_HIGH_TWO)
            return CardBrand.MAESTRO;
        
        return CardBrand.NOT_VALID;
    }
}
