/**
 * This class file inherits the stable matchable participant class properties 
 * which defines the pet name and their preferences.
 */

/**
 * This class inherits the stable matchable participant class, which gets and
 * sets the pet's name and preferences. This class also has the method which
 * inverses the preference lists of pet, which will be used by the Gale Shapely
 * stable matcher to index the pet preference corresponding to a person in order
 * of 1.
 * 
 * @author Shalini Ramachandra #1465487
 * 
 */
public class Pet extends StableMatchableParticipant {

	private Integer[] inversePreferences;

	/**
	 * This method is used to find the inverse preferences of a pet. Inverse
	 * preference list contains the indices of person as per preference order of
	 * a pet.
	 * 
	 * @return - Returns an array that contains the inverted preferences of a
	 *         pet.
	 */
	public Integer[] getInversePreferences() {
		if (this.inversePreferences != null) {
			return this.inversePreferences;
		} else {

			this.inversePreferences = new Integer[this.preferences.length];
			for (int i = 0; i < this.preferences.length; i++) {
				this.inversePreferences[this.preferences[i]] = i;
			}

			return this.inversePreferences;
		}
	}

}
