package win2017;

// STUDENT_NAME:
// STUDENT_ID:

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Scrabble {

	static HashSet<String> myDictionary; // this is where the words of the
											// dictionary are stored

	// DO NOT CHANGE THIS METHOD
	// Reads dictionary from file
	public static void readDictionaryFromFile(String fileName) throws Exception {
		myDictionary = new HashSet<String>();

		BufferedReader myFileReader = new BufferedReader(new FileReader(fileName));

		String word;
		while ((word = myFileReader.readLine()) != null)
			myDictionary.add(word);

		myFileReader.close();
	}

	/*
	 * Arguments: char availableLetters[] : array of characters containing the
	 * letters that remain available String prefix : Word assembled to date
	 * Returns: String corresponding to the longest English word starting with
	 * prefix, completed with zero or more letters from availableLetters. If no
	 * such word exists, it returns the String ""
	 */
	static Set<String> hSet = new HashSet<String>();

	public static String longestWord(char availableLetters[], String prefix) {

		/* WRITE YOUR CODE HERE */
		String longest = "";

		permutation(availableLetters, prefix);
		for (String temp : hSet) {
			if (temp.length() > longest.length())
				longest = temp;
		}
		hSet.clear();
		return longest;

	}

	private static void permutation(char availableLetters[], String prefix) {

		// base case
		if (myDictionary.contains(prefix)) {
			hSet.add(prefix);
		}
		for (int i = 0; i < availableLetters.length; i++) {
			String tmp = new String(availableLetters);
			String newLetters = tmp.substring(0, i) + tmp.substring(i + 1);
			permutation(newLetters.toCharArray(), prefix + availableLetters[i]);
		}

	}

	/*
	 * main method You should not need to change anything here.
	 */
	public static void main(String args[]) throws Exception {

		// First, read the dictionary
		try {
			readDictionaryFromFile("englishDictionary.txt");
		} catch (Exception e) {
			System.out.println("Error reading the dictionary: " + e);
		}

		// Ask user to type in letters
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		char letters[];
		do {
			System.out.println("Enter your letters (no spaces or commas):");

			letters = keyboard.readLine().toCharArray();

			// now, enumerate the words that can be formed
			String longest = longestWord(letters, "");
			System.out.println("The longest word is " + longest);
		} while (letters.length != 0);

		keyboard.close();

	}
}