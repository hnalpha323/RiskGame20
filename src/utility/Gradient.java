package utility;

import java.util.ArrayList;

/**
 * @author Meet_Patel
 * @version 1.0.0
 */
public class Gradient {
	
	private String code;
    private String name;

    private static ArrayList<Gradient> Grd = new ArrayList<Gradient>();
    private int lastIndex = -1;


    public Gradient()
    {
        this.fillColors();
    }

    private Gradient(String name, String code)
    {
        this.code = code;
        this.name = name;
    }

    /**
     * Generates a random color from a pool of predefined colors
     * @return Generated color
     */
    public Gradient getRandomColor()
    {
        this.lastIndex++;
        return Grd.get(this.lastIndex);

    }

    /**
     * @return Hexa Decimal color code
     */
    public String getCode(){ 
    	return this.code; 
    }

    /**
     * @return Color Name 
     */
    public String getName(){ 
    	return this.name; 
    }

    private void fillColors(){
        Grd.add(new Gradient("Red", "#E81123"));
        Grd.add(new Gradient("Green", "#107C10"));
        Grd.add(new Gradient("Blue", "#0063B1"));
        Grd.add(new Gradient("Orange", "#F7630C"));
        Grd.add(new Gradient("Black", "#000000"));
        Grd.add(new Gradient("White", "#ffffff"));
    }
}
