/**
 * This interface defines the methods, that will be used and implemented by the 
 * Gale Shapely Stable Matcher.
 * 
 */

import java.util.LinkedHashMap;

/**
 * This interface defines the method that will be used by Gale Shapely Stable
 * Matcher to perform stable matching, which returns the set of matched pairs.
 * 
 * @author Shalini Ramachandra #1465487
 * 
 */
public interface IStableMatcher {
	/**
	 * This method computes the stable matches from the preferences lists.
	 * 
	 * @param numberOfParticipants
	 *            . - Number of participants (n)
	 * @param people
	 *            - An array of people objects, containing their names and
	 *            preferences.
	 * @param pet
	 *            -An array of pet objects, containing their names and
	 *            preferences.
	 * @return - Returns a {@code LinkedHashMap} containing the stable matched
	 *         pairs.
	 */
	public LinkedHashMap<String, String> performStableMatching(
			final int numberOfParticipants, final Person[] people,
			final Pet[] pets);

}
