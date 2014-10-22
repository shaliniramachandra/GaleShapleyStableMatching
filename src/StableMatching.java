/**
 * This is the main class file which implements the stable matching algorithm
 * of the given set of participants.
 */

import java.util.LinkedHashMap;

/**
 * This is the main program for implementation of the stable matching algorithm.
 * 
 * @author Shalini Ramachandra #1465487
 * 
 */
public class StableMatching {
	/**
	 * This is the main program for implementation of the stable matching
	 * algorithm.
	 * 
	 * @param args
	 *            - Command line arguments passed. This is not used in our
	 *            program.
	 */
	public static void main(String[] args) {

		IInputParser parser = new FileInputParser("program1data.txt");
		parser.getPeople();
		IStableMatcher stableMatcher = new GaleShapelyStableMatcher();
		LinkedHashMap<String, String> stableMatchedSet = stableMatcher
				.performStableMatching(parser.getNumberOfParticipants(),
						parser.getPeople(), parser.getPets());
		printStableMatchesInInputOrder(stableMatchedSet, parser.getPeople());
	}

	/**
	 * This method prints the stable matched set of pairs in the console.
	 * 
	 * @param stableMatchedSet
	 *            - The set of matched people and pets.
	 * @param people
	 *            - An array of people objects.
	 */
	private static void printStableMatchesInInputOrder(
			final LinkedHashMap<String, String> stableMatchedSet,
			final Person[] people) {
		for (int i = 0; i < stableMatchedSet.size(); i++) {
			System.out.println(people[i].getName() + " / "
					+ stableMatchedSet.get(people[i].getName()));

		}
	}

}
