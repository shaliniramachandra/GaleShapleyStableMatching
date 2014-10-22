/**
 * This class file is used to implement the Gale Shapely Stable Matching 
 * for the given set of participants, and gives the set of matched pairs.
 */

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

/**
 * This class implements the stable matching algorithm for the given input
 * participants.
 * 
 * @author Shalini Ramachandra #1465487
 */
public class GaleShapelyStableMatcher implements IStableMatcher {

	/**
	 * This method computes the stable matches from the preferences lists of the
	 * participants, the stable matched set is people-optimal.
	 * 
	 * @param numberOfParticipants
	 *            - Number of participants (n).
	 * @param people
	 *            -An array of people objects, containing their names and
	 *            preferences.
	 * 
	 * @param pets
	 *            -An array of pet objects, containing their names and
	 *            preferences.
	 * 
	 * @return - Returns a {@code LinkedHashMap} containing the stable matching
	 *         pairs.
	 */
	@Override
	public LinkedHashMap<String, String> performStableMatching(
			final int numberOfParticipants, final Person[] people,
			final Pet[] pets) {
		LinkedHashMap<Integer, Integer> stableMatchedIndicesSet = new LinkedHashMap<Integer, Integer>(
				numberOfParticipants);
		Stack<Integer> freePeople = new Stack<Integer>();
		Integer[] next = new Integer[numberOfParticipants];

		// Pushing free people into stack. Person proposes the pet according to
		// their preference order, thus people-optimal.
		for (int i = 0; i < numberOfParticipants; i++) {
			next[i] = 0;
			freePeople.push(i);
		}

		while (!freePeople.empty()) {

			// Pops a free person to match him with a pet.
			int personIndex = freePeople.pop();
			Integer[] personPreferences = people[personIndex].getPreferences();

			for (int i = next[personIndex]; i < numberOfParticipants; i++) {

				// Stores the next highest ranked preference of the person.
				next[personIndex] = next[personIndex] + 1;
				int petIndex = personPreferences[i];

				// If pet does not have a partner, then create a matched set.
				if (!stableMatchedIndicesSet.containsKey(petIndex)) {
					stableMatchedIndicesSet.put(petIndex, personIndex);
					break;
				} else {
					// If a pet is matched and has a current person, then check
					// if the pet prefers the proposing person than the current
					// person.
					int petCurrentPerson = stableMatchedIndicesSet
							.get(petIndex);

					// Traverses the inverse preference list to get the
					// preference of pet, corresponding to a person, in the
					// order of 1.
					Integer[] petInverserPreferences = pets[petIndex]
							.getInversePreferences();
					if (petInverserPreferences[petCurrentPerson] > petInverserPreferences[personIndex]) {
						stableMatchedIndicesSet.put(petIndex, personIndex);
						freePeople.push(petCurrentPerson);
						break;
					}
				}
			}
		}

		return this.GetStableMatchedNamesSet(stableMatchedIndicesSet, people,
				pets);
	}

	/**
	 * This method returns a stable matched set with indices of people/pets
	 * mapped to its corresponding names.
	 * 
	 * @param stableMatchedIndicesSet
	 *            - A {@code LinkedHashMap} stable matched set that contains the
	 *            person and pet indices.
	 * @param people
	 *            - An array of people objects.
	 * @param pets
	 *            - An array of pet objects
	 * @return - Returns a {@code LinkedHashMap} stable matched set that
	 *         contains the person and pets pairs.
	 */
	private LinkedHashMap<String, String> GetStableMatchedNamesSet(
			final LinkedHashMap<Integer, Integer> stableMatchedIndicesSet,
			final Person[] people, final Pet[] pets) {
		LinkedHashMap<String, String> stableMatchedSet = new LinkedHashMap<String, String>(
				people.length);

		Iterator<Map.Entry<Integer, Integer>> stableMatchedIndicesIterator = stableMatchedIndicesSet
				.entrySet().iterator();

		while (stableMatchedIndicesIterator.hasNext()) {
			int petIndex = stableMatchedIndicesIterator.next().getKey();
			stableMatchedSet.put(
					people[stableMatchedIndicesSet.get(petIndex)].getName(),
					pets[petIndex].getName());
		}

		return stableMatchedSet;
	}

}
