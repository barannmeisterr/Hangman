import java.util.Scanner;
public class Main {
	// Title: Main class
	// Author: Arda Baran
   // Description: In this class , the hangman game is played by a player
//-----------------------------------------------------	  
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example random word to be guessed
        String randomWord = "hangman";

        // Create a new player to play the hangman game
        Player arda = new Player(HangmanGame.GUESS_LIMIT);

        
        HangmanGame game = new HangmanGame(arda, randomWord);
//Rules and informations about the game
  game.gameInfo();
        
        
        

        // start and play the game
        game.playGame(scanner,arda, randomWord);

        
        scanner.close();
    }
}
