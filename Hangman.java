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
		theWord = "abcdef";
		currentWord = createWord(theWord);
		// theWord = getRandomWord();
		playGame(theWord);
	}
	
	private void playGame(String word) {
		println("Welcome to Hangman");
		while (guessesRemaining > 0) {
			println("Your word now looks like this: " + currentWord);
			println("You now have " + guessesRemaining + " guesses left");
			getLetterFromUser();
			// hasLetterBeenGuessed(upperLetter);
			if (checkLetter(upperLetter)) {
				//do something
			} else {
				guessesRemaining --;
			}
		}
	}
	
	private String createWord(String theWord) {
		// need to run for loop for length of theWord
		for (int i = 0; i <= theWord.length(); i++) {
			currentWord += " - ";
		}
		return currentWord;
	} 
	
	private char getLetterFromUser() {
		/*
		 * try this when I get here
		 * 
		 */
		String letter = readLine("Please enter a letter: ");
		println(letter);
		// System.out.println("Please enter a letter: ");
		//letter = input.next().charAt(0);
		/*
		if (!Character.isLetter(letter)) {
			println("Please enter only a letter");
		}
		upperLetter = Character.toUpperCase(letter);
		checkLetter(upperLetter);
		*/
		numberOfGuesses ++;
		return upperLetter;
	}
	
	private void hasLetterBeenGuessed(char upperLetter) {
		for (int i = 0; i <= numberOfGuesses; i++) {
			if (upperLetter == correctGuesses.charAt(i)) {
				println("You have already guessed that letter.");
				guessesRemaining ++;
			} else if (upperLetter == incorrectGuesses.charAt(i)) {
				guessesRemaining --;
			}
		}
	}
	
	private boolean checkLetter(char upperLetter) {
		for (int i = 0; i < theWord.length(); i++) {
			if (upperLetter == theWord.charAt(i)) {
				// most likely delete this ----> guessedLetters[i] = theWord.charAt(i);
				if (correctGuesses.indexOf(upperLetter) == -1) {
					correctGuesses += upperLetter;
				}
			}
		}
		if (theWord.indexOf(upperLetter) == -1) {
			return false;
		} else {
			return true;
		}
	}
	
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
