import java.util.Scanner;
import java.awt.*;
import java.awt.image.BufferedImage;


public class HangmanGame {
	// Title: HangmanGame class
	// Author: Arda Baran
   // Description: In this class , game rules are introduced.Player's scores and status are tracked.The hangman game is
   //initialized.
//-----------------------------------------------------	
	
	
	
public static final int  GUESS_LIMIT=10;
	String randomWord;
Player player;//player is the person who plays hangman game

public Scanner sc;
public HangmanGame(Player player ,String randomWord) {
	this.player=new Player(GUESS_LIMIT);
this.randomWord=randomWord;

sc=new Scanner(System.in);

}
public void gameInfo() {
asciiArt("HANGMAN");
	System.out.println();
	System.out.println();
	System.out.println("Welcome to the Hangman Game, this game is played by guessing  letters of the secret word .");
	System.out.println("You have "+GUESS_LIMIT+" life.Every time you guess an wrong letter,your life decreases.");
    System.out.println("Every time you guess an wrong letter a body part appears on he game.");
    System.out.println("Every time you guess an correct letter,your life does not change.");
    System.out.println("If you dont't guess correctly before you get hung, you  will lose the game");
    System.out.println("If you  guess correctly before you get hung , you  will win the game");
System.out.println();
System.out.println();

}


public boolean checkGuessIsCorrect(char guess,String randomWord) {
//this method controls player's guess is correct or wrong.If player's guess is correct,then adds the guessed letters	
//to player's guessed words.	
	char[]wordConvertedToCharArray=randomWord.toCharArray();
	boolean correctGuess = false; 
	for(int i=0;i<wordConvertedToCharArray.length;i++) {
	if(wordConvertedToCharArray[i]==guess && player.getLetterGuessedByPlayer()[i] != guess) {
		int occurrence = occurenceOfACharacter(guess, randomWord);
		for(int j = 0;j<occurrence;j++) {
		player.addGuess(guess);   		
		
		}
		 correctGuess = true; 
		
	}
}
return correctGuess;
}
public void playGame(Scanner sc,Player player,String randomWord) {
//this method starts the game and takes an input letter from player.if player guesses all letters before get hung , player
//wins ,otherwise player loses the game.	
	
	
	
	char guessPlayer;
		
	int attempt=player.getAttempt();
	int correctGuess=player.getCorrectGuess();
	int wrongGuess=player.getWrongGuess();
	int score=player.getScore();
	
	while (attempt > 0 && correctGuess < randomWord.length() ) {
	System.out.println("Guess a letter of the secret word: ");
		guessPlayer=sc.next().charAt(0);
		
		if (!Character.isLetter(guessPlayer)) {
    //even if player's input is hangman and secret word is hangman,only the first letter is considered.     
			
			System.out.println("Please enter a valid letter.");
            continue;
        }
		
		
		
		if(checkGuessIsCorrect(guessPlayer,randomWord)) {
		System.out.println("Correct Guess...");
		correctGuess=correctGuess + occurenceOfACharacter(guessPlayer,randomWord);//if occurence of guess in secret word is  
	    player.setCorrectGuess(correctGuess);                                     //more than one or one ,updates player's 
	                                                                               //correct guesses according to occurence.
	    wrongGuess = wrongGuess +0;                                               
	    player.setWrongGuess(wrongGuess);
	    score = (player.getCorrectGuess() * 100);//every correct guesses are 100 point.
	    player.setScore(score);
	    attempt=attempt -0;//life does not change if guess is correct    
	    player.setMaxAttempt(attempt);
	}else {
		System.out.println("Wrong Guess...");
		
		correctGuess= correctGuess+0;
		wrongGuess=wrongGuess+1;
		score = score+0;//score does not change if guessed letter is wrong
		attempt= attempt-1;//life decreases by 1 if guess is wrong
		player.setCorrectGuess(correctGuess);
		 player.setWrongGuess(wrongGuess);
		 player.setScore(score);
		 player.setMaxAttempt(attempt);
	}
	
	
	 printScoreBoard(player);
	 printHangman(player);
	}
	 if (checkIfPlayerWins(player,randomWord)) {
	       asciiArt("YOU WIN");//prints with ascii art 
	    } else {
	        asciiArt("GAME OVER");//prints with ascii art
	    }
	
}
public void printScoreBoard(Player player) {
//prints player's statistics.    
	
	System.out.println("Score: " + player.getScore());
    System.out.println("Number Of Guessed Letters Are Correct: " + player.getCorrectGuess());
    System.out.println("Number Of Guessed Letters Are Wrong:   " + player.getWrongGuess());
    System.out.println("Remained Life: " + player.getAttempt());
}
public int occurenceOfACharacter(char letter,String word) {
//helper method for player's point and correct guess counter
	
	char[] wordCharArr=word.toCharArray();	
int occurrence=0;
for(int i =0;i<word.length();i++) {
	boolean occurrenceDetected=false;
if(wordCharArr[i]==letter) {
	occurrenceDetected=true;
}
if(occurrenceDetected) {
	occurrence++;
}


}
return occurrence;
}
public void printGuessedLetters(Player player) {
    char[] guessedLetters = player.getLetterGuessedByPlayer();
    System.out.print("Letters guessed so far: ");
    for (int i = 0; i < player.getCorrectGuess(); i++) {
        System.out.print(guessedLetters[i] + " ");
    }
    System.out.println();
}
public boolean checkIfPlayerWins(Player player,String randomWord) {
//this method checks if the player win the game.if player guesses correct all letter in the secret word ,player wins,
//otherwise GAME OVER	
	int wordLen=randomWord.length();
	int requiredScoreToWinTheGame=wordLen * 100;
int playerScore=player.getScore();
if(playerScore==requiredScoreToWinTheGame) 
	return true;
else 
	return false;


}
public void printHangman(Player player) {
//prints hangman for each number of wrong guesses.
	
	int wrongGuess = player.getWrongGuess();
	switch (wrongGuess) {
	case 1:
	
		System.out.println("------------------------------------                           ");
	break;
	
	
	case 2:
					
		System.out.println("             |                                                    ");				
		System.out.println("             |                                                    ");				
		System.out.println("             |                                                    ");			
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("------------------------------------                           ");
		break;
	case 3 :
		System.out.println("----------------------------                                     ");				
		System.out.println("             |                                                    ");				
		System.out.println("             |                                                    ");				
		System.out.println("             |                                                    ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("------------------------------------                           ");
		break;
	
	case 4 :
		System.out.println("----------------------------------------------------------------");				
		System.out.println("             |                                                    ");				
		System.out.println("             |                                                    ");				
		System.out.println("             |                                                    ");					
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("------------------------------------                           ");	
		break;
	case 5:
		
		System.out.println("----------------------------------------------------------------");				
		System.out.println("             |                                               |     ");				
		System.out.println("             |                                               |     ");				
		System.out.println("             |                                               |     ");					
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("------------------------------------                           ");	
		
		break;
	case 6 :
	
		System.out.println("----------------------------------------------------------------");				
		System.out.println("             |                                               |     ");				
		System.out.println("             |                                               |     ");				
		System.out.println("             |                                               |     ");					
		System.out.println("	     |                                              o  o   ");				
		System.out.println("	     |                                            o     o  ");				
		System.out.println("	     |                                              o  o   ");				
		System.out.println("	     |                                                     ");
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("	     |                                                     ");				
		System.out.println("------------------------------------                           ");	 
		break;
	case 7 :		
		System.out.println("----------------------------------------------------------------");				
		System.out.println("             |                                               |     ");				
		System.out.println("             |                                               |     ");				
		System.out.println("             |                                               |     ");					
		System.out.println("	     |                                              o  o   ");				
		System.out.println("	     |                                            o     o  ");				
		System.out.println("	     |                                              o  o   ");				
		System.out.println("	     |                                                |    ");
		System.out.println("	     |                                                |    ");				
		System.out.println("	     |                                                |    ");				
		System.out.println("	     |                                                |    ");				
		System.out.println("------------------------------------                           ");	            
		break;
	case 8:
		System.out.println("----------------------------------------------------------------");				
		System.out.println("             |                                               |     ");				
		System.out.println("             |                                               |     ");				
		System.out.println("             |                                               |     ");					
		System.out.println("	     |                                              o  o   ");				
		System.out.println("	     |                                            o     o  ");				
		System.out.println("	     |                                              o  o   ");				
		System.out.println("	     |                                                |    ");
		System.out.println("	     |                                             ---|--- ");				
		System.out.println("	     |                                                |    ");				
		System.out.println("	     |                                                |    ");				
		System.out.println("------------------------------------                           ");	
		break;
	case 9:		
		System.out.println("----------------------------------------------------------------");				
		System.out.println("             |                                               |     ");				
		System.out.println("             |                                               |     ");				
		System.out.println("             |                                               |     ");					
		System.out.println("	     |                                              o  o   ");				
		System.out.println("	     |                                            o     o  ");				
		System.out.println("	     |                                              o  o   ");				
		System.out.println("	     |                                                |    ");
		System.out.println("	     |                                             ---|--- ");				
		System.out.println("	     |                                             ---|    ");				
		System.out.println("	     |                                                |    ");				
		System.out.println("------------------------------------                           ");	
		break;
			case 10:	
		

System.out.println("----------------------------------------------------------------");				
System.out.println("             |                                               |     ");				
System.out.println("             |                                               |     ");				
System.out.println("             |                                               |     ");				
System.out.println("	     |                                              o  o   ");				
System.out.println("	     |                                            o     o  ");				
System.out.println("	     |                                              o  o   ");				
System.out.println("	     |                                                |    ");
System.out.println("	     |                                             ---|--- ");				
System.out.println("	     |                                             ---|--- ");				
System.out.println("	     |                                                |    ");				
System.out.println("------------------------------------                           ");				
				
				
				
				break;
	
	
	default:
		return ;
		
	}
	
	
}
public void asciiArt(String input) {
//prints a word with ASCII ART.	
//referance:https://mkyong.com/java/ascii-art-java-example/	
	int width = 150;
    int height = 30;

   
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();
    g.setFont(new Font("SansSerif", Font.BOLD, 15));

    Graphics2D graphics = (Graphics2D) g;
    graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    graphics.drawString(input, 10, 15);
    for (int y = 0; y < height; y++) {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < width; x++) {

            sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");

        }

        if (sb.toString().trim().isEmpty()) {
            continue;
        }

        System.out.println(sb);
    }
	
	
	
}
}
