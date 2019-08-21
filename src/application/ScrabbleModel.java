package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class ScrabbleModel {
	
	private ArrayList<Letter> letters = new ArrayList<Letter>();
	private ArrayList<String> previousWords = new ArrayList<String>();
	private ArrayList<Letter> unavailableLetters = new ArrayList<Letter>();
	
	private String errorMessage = "Error!";
	private int totalPoints;
	
	public ScrabbleModel() {
		setLetters(this.letters);
	}
	
	public boolean processWord(String word) {
		
		for(int i=0; i<word.length(); i++) {
			char currentLetter = Character.toUpperCase(word.charAt(i));
			try {
				Letter foundLetter = letters.stream()
						.filter(letter -> letter.character == currentLetter)
						.collect(Collectors.toList()).get(0);
				if(unavailableLetters.contains(foundLetter)) {
					errorMessage += "\nWord contains letter that is no longer available \"in bag\"";
					return false;
				}
				this.totalPoints += foundLetter.point;
				
				//decrease the letter quantity from the bag
				foundLetter.decreaseQuantity();
				//if the quantity of that word is 0 then put it in notAvailableWords
				if(foundLetter.quantity == 0 && !unavailableLetters.contains(foundLetter)) {
					unavailableLetters.add(foundLetter);
				}
				
				System.out.println(currentLetter + ", " + foundLetter.character + ", " + foundLetter.point + ", " + foundLetter.quantity);
			}
			catch(Exception e) {
				System.err.println("letter not found: " + e);
				return false;
			}
		}
		//add the word to the previous words
		previousWords.add(word);
		return true;
	}
	
	public boolean validateWord(String word) {
		word = word.trim();
		if(word.length() == 0) {
			errorMessage += "\nEntered word is blank.";
			return false;
		}
		if(!word.matches("^[a-zA-Z]+$")) {
			errorMessage += "\nPlease enter a valid word.";
			return false;
		}
		if(word.length()<2 || word.length()>8) {
			errorMessage += "\nWord length should be between 2 and 8.";
			return false;
		}
		if(!hasVowel(word)) {
			errorMessage += "\nEntered word doesn't have any vowels in it.";
			return false;
		}
		if(previousWords.contains(word)) {
			errorMessage += "\nThis word is already used.";
			return false;
		}
		if(checkLettersNotAvailable(word)) {
			errorMessage += "\nWord contains letter that is no longer available \"in bag\"";
			return false;
		}
		return true;
	}
	private boolean checkLettersNotAvailable(String word) {
		
		ArrayList<Letter> tempLetters = new ArrayList<Letter>();
		setLetters(tempLetters);
		for(int i=0; i<word.length(); i++) {
			char currentLetter = Character.toUpperCase(word.charAt(i));
			
			Letter foundLetter = tempLetters.stream()
					.filter(letter -> letter.character == currentLetter)
					.collect(Collectors.toList()).get(0);
			if(foundLetter.quantity < 1) {
				return true;
			}
			if(getUnavailableLetters().contains(Character.toString(currentLetter))) {
				return true;
			}
			foundLetter.decreaseQuantity();
		}
		tempLetters.clear();
		return false;
	}
	
	private boolean hasVowel(String word) {
		char[] vowels = {'a', 'e', 'i', 'o', 'u'};
		for(char c : vowels) {
			if(word.toLowerCase().contains(Character.toString(c))) 
				return true;
		}
		return false;
	}
	
	public boolean onlyConsonentsLeft() {
		int countVowels = 0;
		for(Letter letter: this.unavailableLetters) {
			if(Character.toString(letter.character).matches("[AEIOU]")) {
				countVowels++;
			}
		}
		if(countVowels == 5) {
			return true;
		}
		return false;
	}
	
	public boolean gameOver() {
		if(this.unavailableLetters.size()>=25) {
			return true;
		}
		if(onlyConsonentsLeft()) {
			return true;
		}
		return false;
	}
	
	public int getPoints() {
		return this.totalPoints;
	}
	
	public String getPreviousWords() {
		return this.previousWords.stream()
				.sorted(Collections.reverseOrder())
				.collect(Collectors.joining(", "));
	}
	
	public ArrayList<String> getUnavailableLetters(){
		return (ArrayList<String>) this.unavailableLetters.stream()
				.map(letter -> Character.toString(letter.character))
				.collect(Collectors.toList());
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	public void clearErrorMessage() {
		this.errorMessage = "Error!";
	}
	
	private void setLetters(ArrayList<Letter> letters) {
		letters.add(new Letter('A', 1, 9));
		letters.add(new Letter('B', 3, 2));
		letters.add(new Letter('C', 3, 2));
		letters.add(new Letter('D', 2, 4));
		letters.add(new Letter('E', 1, 12));
		letters.add(new Letter('F', 4, 2));
		letters.add(new Letter('G', 2, 3));
		letters.add(new Letter('H', 4, 2));
		letters.add(new Letter('I', 1, 8));
		letters.add(new Letter('J', 8, 1));
		letters.add(new Letter('K', 5, 1));
		letters.add(new Letter('L', 1, 4));
		letters.add(new Letter('M', 3, 2));
		letters.add(new Letter('N', 1, 6));
		letters.add(new Letter('O', 1, 8));
		letters.add(new Letter('P', 3, 2));
		letters.add(new Letter('Q', 10, 1));
		letters.add(new Letter('R', 1, 6));
		letters.add(new Letter('S', 1, 4));
		letters.add(new Letter('T', 1, 6));
		letters.add(new Letter('U', 1, 4));
		letters.add(new Letter('V', 4, 2));
		letters.add(new Letter('W', 4, 2));
		letters.add(new Letter('X', 8, 1));
		letters.add(new Letter('Y', 4, 2));
		letters.add(new Letter('Z', 10, 1));
	}
}
