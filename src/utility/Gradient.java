package utility;

import java.util.ArrayList;

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
     * provides a random color
     * @return color
     */
    public Gradient getRandomColor()
    {
        this.lastIndex++;
        return Grd.get(this.lastIndex);

    }

    /**
     * returns hexa decimal code of the color
     * @return hexa decimal code of the color. like #232323
     */
    public String getCode(){ return this.code; }

    /**
     * returns name of the color
     * @return name of the color. like Green
     */
    public String getName(){ return this.name; }

    /**
     * fills color repository
     */
    private void fillColors(){
        Grd.add(new Gradient("Red", "#E81123"));
        Grd.add(new Gradient("Green", "#107C10"));
        Grd.add(new Gradient("Blue", "#0063B1"));
        Grd.add(new Gradient("Orange", "#F7630C"));
        Grd.add(new Gradient("Black", "#000000"));
        Grd.add(new Gradient("White", "#ffffff"));
    }
}
