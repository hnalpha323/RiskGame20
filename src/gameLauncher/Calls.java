package gameLauncher;

/**		public class Calls 
	 * This class is used to implement the callback mechanism	4	
	 * {
	 * <pre>		
	 * {@code		
	 *   object.getCallback(new Callback(){        		
	 *          public void called(int numberOfPlayers){		
	 *             //called whenever getCallback calls as		
	 *             // callback.called(value);  		
	 *          }		
	 *   })			
	 * }		
	 * </pre>		
	 * @author Waleed		
	 */

public class Calls 
{
	/**
	* @param value is the integers want to pass to caller
	*/
	
	@Deprecated
	public void called(int value)
	{ 	
	}
	
	/**
	* This callback passes two arguments to callback listener as stated bellow
	* @param numberOfPlayers tells number of player going to play the game 
	* @param strategies tells strategies they are going to follow
	*/
	public void called(int numberOfPlayers, String strategies) 
	{	
	}
		
	/**
	* This call back method can be used to pass any java Object to listeners
	* @param object which is generic type
	* @param <T> is nay generic java object
	*/
	public <T> void called(T object)
	{
		
	}

}
