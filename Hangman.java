/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman extends ConsoleProgram {

	/***********************************************************
	 *              CONSTANTS                                  *
	 ***********************************************************/
	
	/* The number of guesses in one game of Hangman */
	private static final int N_GUESSES = 7;
	/* The width and the height to make the karel image */
	private static final int KAREL_SIZE = 150;
	/* The y-location to display karel */
	private static final int KAREL_Y = 230;
	/* The width and the height to make the parachute image */
	private static final int PARACHUTE_WIDTH = 300;
	private static final int PARACHUTE_HEIGHT = 130;
	/* The y-location to display the parachute */
	private static final int PARACHUTE_Y = 50;
	/* The y-location to display the partially guessed string */
	private static final int PARTIALLY_GUESSED_Y = 430;
	/* The y-location to display the incorrectly guessed letters */
	private static final int INCORRECT_GUESSES_Y = 460;
	/* The fonts of both labels */
	private static final String PARTIALLY_GUESSED_FONT = "Courier-36";
	private static final String INCORRECT_GUESSES_FONT = "Courier-26";
	
	/* The word returned from getRandomWord which will serve as main word in game */
	private String theWord;
	/* this will store the correct guesses by user */
	private String correctGuesses = "";
	/* stores string of letters incorrectly guessed */
	private String incorrectGuesses = "";
	/* this will be used for displaying the word with dashes */
	private String currentWord = "";
	/* will be used in process of printing out the word with dashes */
	private char[] charArray;
	
	private String updatedWord;
	/* will help with counter to determine how many guesses user has left */
	private int guessesRemaining = N_GUESSES;
	/* letter that user inputs into console */
	private char letter;
	/* use this to convert user input char to upper case */
	private char upperLetter;
	/* keep a counter to help with checking if user has previously guessed letter */
	private int numberOfGuesses = 0;
	
	
	/***********************************************************
	 *              Instance Variables                         *
	 ***********************************************************/
	
	/* An object that can produce pseudo random numbers */
	private RandomGenerator rg = new RandomGenerator();
	
	private GCanvas canvas = new GCanvas();
	
	Scanner input = new Scanner(System.in);
	
	
	/***********************************************************
	 *                    Methods                              *
	 ***********************************************************/
	
	public void run() {
		theWord = "ABCDEF";
		// theWord = getRandomWord();
		currentWord = createWord(theWord);
		playGame(theWord);
	}
	
	private void playGame(String word) {
		println("Welcome to Hangman");
		while (guessesRemaining > 0) {
			println("Your word now looks like this: " + currentWord);
			println("You now have " + guessesRemaining + " guesses left");
			getLetterFromUser();
		}
	}
	
	private String createWord(String theWord) {
		// need to run for loop for length of theWord
		for (int i = 0; i < theWord.length(); i++) {
			currentWord += "-";
		}
		return currentWord;
	} 
	
	private void getLetterFromUser() {
		String letter = readLine("Please enter a letter: ");
		if (letter.length() > 1) {
			println("Please only input one letter");
		} else if (!letter.chars().allMatch(Character::isLetter)) {
			println("Please only input letters");
		} else {
			char letterToChar = letter.charAt(0);
			upperLetter = Character.toUpperCase(letterToChar);
			checkLetter(upperLetter);
			numberOfGuesses ++;
		}
	}
	
	private void checkLetter(char upperLetter) {
		for (int i = 0; i < theWord.length(); i++) {
			if (upperLetter == theWord.charAt(i)) {
				if (correctGuesses.indexOf(upperLetter) == -1) {
					correctGuesses += upperLetter;
					StringBuilder str = new StringBuilder(currentWord);
					str.setCharAt(i, upperLetter);
					currentWord = str.toString();
					numberOfGuesses ++;
				}
			} else {
				incorrectGuesses += upperLetter;
				numberOfGuesses ++;
				guessesRemaining --;
			}
		}
	}
	/*
	private boolean hasLetterBeenGuessed(char upperLetter) {
		
	}
	*/
	
	/**
	 * Method: Get Random Word
	 * -------------------------
	 * This method returns a word to use in the hangman game. It randomly 
	 * selects from among 10 choices.
	 */
	private String getRandomWord() {
		int index = rg.nextInt(10);
		if(index == 0) return "BUOY";
		if(index == 1) return "COMPUTER";
		if(index == 2) return "CONNOISSEUR";
		if(index == 3) return "DEHYDRATE";
		if(index == 4) return "FUZZY";
		if(index == 5) return "HUBBUB";
		if(index == 6) return "KEYHOLE";
		if(index == 7) return "QUAGMIRE";
		if(index == 8) return "SLITHER";
		if(index == 9) return "ZIRCON";
		throw new ErrorException("getWord: Illegal index");
	}

}
