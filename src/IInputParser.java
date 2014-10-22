/**
 * This interface defines the methods, that will be used and implemented by the 
 * File input parser.
 */

/**
 * This interface defines the methods which will be implemented by the file
 * input parser.
 * 
 * @author Shalini Ramachandra #1465487
 */
public interface IInputParser {
	/**
	 * This method returns the number of participants (n).
	 * 
	 * @return - Returns number of participants.
	 */
	public int getNumberOfParticipants();

	/**
	 * This method is used to get an array of people objects.
	 * 
	 * @return - Array of people objects.
	 */
	public Person[] getPeople();

	/**
	 * This method is used to get an array of pet objects.
	 * 
	 * @return - Returns an array of pet objects.
	 */
	public Pet[] getPets();

}
