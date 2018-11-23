package model;

import java.io.Serializable;

/**
 * Card Manager
 */
public class Card implements Serializable{

	private static final long serialVersionUID = 1012842278301009514L;
	
	private String cardName;
	
	
	
    private int armyValue = 0;

    /**
     * constructor
     * @param new_cardName name of territory
     * @param new_armyValue value of territory
     */
    public Card(String new_cardName, int new_armyValue)
    {
        this.armyValue = new_armyValue;
        this.cardName = new_cardName;
    }

    public String getCardName() 
    { 
    	return this.cardName; 
    }
    
    public int getCardValue() 
    { 
    	return this.armyValue; 
    }

}
