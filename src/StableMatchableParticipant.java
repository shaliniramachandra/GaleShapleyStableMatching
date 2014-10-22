/**
 * This class file defines fields and the methods, which will be inherited by
 * a participant class, such as person or pet.
 * 
 */

/**
 * This abstract class defines the fields and list of methods which will be
 * inherited by each of the participant class in the stable matching.
 * 
 * @author Shalini Ramachandra #1465487
 */
public abstract class StableMatchableParticipant {

	protected String name;
	protected Integer[] preferences;

	/**
	 * This method is used to get the name of the participant.
	 * 
	 * @return - Returns the participant's name.
	 */

	public String getName() {
		return this.name;
	}

	/**
	 * This method is used to get participant's preferences.
	 * 
	 * @return - Returns an integer array of participant's preferences.
	 */

	public Integer[] getPreferences() {
		return this.preferences;
	}

	/**
	 * This method is used to set the participant's name.
	 * 
	 * @param name
	 *            - Name of the participant.
	 */

	public void setName(final String name) {
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		this.name = name;
	}

	/**
	 * This method is used to set the participant's preferences.
	 * 
	 * @param preferences
	 *            - Preference array of the participant.
	 */

	public void setPreferences(final Integer[] preferences) {

		if (preferences == null) {
			throw new IllegalArgumentException("Preferences cannot be null");
		} else if (preferences.length <= 0) {
			throw new IllegalArgumentException("Preferences cannot be empty");
		}
		this.preferences = preferences;

	}
}
