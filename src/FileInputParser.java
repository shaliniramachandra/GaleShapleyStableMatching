/**
 * This class file is used to implement the File Input Parser,
 * that parses the given input file and gets the number of participants,
 * their names and preferences.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class parses the input file, and creates two object arrays, one for
 * people containing their names and preferences and other object array for pet
 * containing their names and preferences.
 * 
 * @author Shalini Ramachandra #1465487
 * 
 */
public class FileInputParser implements IInputParser {

	private static final String spaceDelimiter = "\\s+";
	private String filePath;
	private int numberOfParticipants;
	private Person[] people;
	private Pet[] pets;

	/**
	 * This constructor sets the value for the class variables.
	 * 
	 * @param filePath
	 *            - The input file, which has to be parsed.
	 */
	public FileInputParser(final String filePath) {

		if (filePath == null || filePath.length() <= 0) {
			throw new IllegalArgumentException(
					"File path cannot be null or empty");
		}

		this.filePath = filePath;
		this.numberOfParticipants = -1;
		this.people = null;
		this.pets = null;
	}

	/**
	 * This method returns the number of participants (n)
	 * 
	 * @return - Returns number of participants
	 */
	@Override
	public int getNumberOfParticipants() {

		if (this.numberOfParticipants != -1) {
			return this.numberOfParticipants;
		} else {
			this.parseFile();
			return this.numberOfParticipants;
		}
	}

	/**
	 * This method returns an array of person objects, containing their names
	 * and preferences.
	 * 
	 * @return - Returns an array of person objects.
	 */
	@Override
	public Person[] getPeople() {

		if (this.people != null) {
			return this.people;
		} else {
			this.parseFile();
			return this.people;
		}

	}

	/**
	 * This method returns an array of pet objects, containing their names and
	 * preferences.
	 * 
	 * @return - Returns an array of pet objects.
	 */
	@Override
	public Pet[] getPets() {

		if (this.pets != null) {
			return this.pets;
		} else {
			this.parseFile();
			return this.pets;
		}
	}

	/**
	 * This method is used to parse the input file and separates people names
	 * and their corresponding preferences, and pet names and their
	 * corresponding preferences.
	 */
	private void parseFile() {

		Exception exception = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(this.filePath);
			bufferedReader = new BufferedReader(fileReader);
			this.numberOfParticipants = Integer.parseInt(bufferedReader
					.readLine());
			this.people = new Person[this.numberOfParticipants];
			this.pets = new Pet[this.numberOfParticipants];

			for (int i = 0; i < this.people.length; i++) {
				this.people[i] = new Person();
			}

			for (int i = 0; i < this.pets.length; i++) {
				this.pets[i] = new Pet();
			}

			setNamesAndPreferences(this.people, bufferedReader);
			setNamesAndPreferences(this.pets, bufferedReader);

		} catch (FileNotFoundException e) {
			System.out.println("Invalid file path or name.");
			exception = e;
		} catch (NumberFormatException e) {
			System.out
					.println("Invalid data in input file, number is expected.");
			exception = e;
		} catch (IOException e) {
			System.out.println("Cannot read the file, I/O Exception occurred.");
			exception = e;
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}

				if (bufferedReader != null) {
					bufferedReader.close();
				}

			} catch (IOException e) {
				System.out.println("Cannot close file or buffered reader.");
				exception = e;
			}

			if (exception != null) {
				System.exit(1);
			}
		}
	}

	/**
	 * This method parses from the buffered input and then sets the name and the
	 * preference in the participant object.
	 * 
	 * @param participants
	 *            - The participant (people/pet) objects - which will be used to
	 *            populate the parsed names and preferences.
	 * @param bufferedReader
	 *            - Buffered reader object reads from the input file.
	 * @throws IOException
	 *             - Handles I/O Exception if thrown by the method.
	 */
	private void setNamesAndPreferences(
			final StableMatchableParticipant[] participants,
			final BufferedReader bufferedReader) throws IOException {

		String[] preferencesAsStringArray = new String[this.numberOfParticipants];

		for (int i = 0; i < this.numberOfParticipants; i++) {
			participants[i].setName(bufferedReader.readLine());
		}

		// Parsing the preferences as string.
		for (int i = 0; i < this.numberOfParticipants; i++) {
			preferencesAsStringArray[i] = bufferedReader.readLine();
		}

		// Converts the preferences which was read as string to Integer and then
		// sets the preferences to the corresponding participant.
		for (int i = 0; i < preferencesAsStringArray.length; i++) {
			String preferencesString = preferencesAsStringArray[i];
			String[] splitArray = preferencesString.trim()
					.split(spaceDelimiter);
			Integer[] preferencesSet = new Integer[this.numberOfParticipants];
			for (int j = 0; j < splitArray.length; j++) {
				preferencesSet[j] = Integer.parseInt(splitArray[j]) - 1;
			}

			participants[i].setPreferences(preferencesSet);
		}
	}
}
