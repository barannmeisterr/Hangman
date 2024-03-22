
public class Player {
	// Title: Player class
	// Author: Arda Baran
   // Description: In this class , a player who plays the hangman game is defined.

//-----------------------------------------------------	
	
	
	
int score,wrongGuess,correctGuess,maxAttempt,front,rear;
char guess;
char[] letterGuessedByPlayer;
int live;

public Player(int maxAttempt) {
//player has maximum attemp
//queue is used to store player's guesses

	this.front=-1;
	this.rear=-1;
	this.maxAttempt=maxAttempt;
    
	this.score=0;
    this.correctGuess=0;
    this.wrongGuess=0;
    this.letterGuessedByPlayer=new char[maxAttempt];   

}

public char getGuess() {
	return guess;
}

public void setGuess(char guess) {
	this.guess = guess;
}

public int getMaxAttempt() {
	return maxAttempt;
}

public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
public int getWrongGuess() {
	return wrongGuess;
}
public void setWrongGuess(int wrongGuess) {
	this.wrongGuess = wrongGuess;
}
public int getCorrectGuess() {
	return correctGuess;
}
public void setCorrectGuess(int correctGuess) {
	this.correctGuess = correctGuess;
}
public int getAttempt() {
	return maxAttempt;
}
public void setMaxAttempt(int maxAttempt) {
	this.maxAttempt = maxAttempt;
}

public char[] getLetterGuessedByPlayer() {
	return letterGuessedByPlayer;
}
public void setLetterGuessedByPlayer(char[] letterGuessedByPlayer) {
	this.letterGuessedByPlayer = letterGuessedByPlayer;
}
public boolean isFull() {
	return (rear==maxAttempt-1);
}
public boolean isEmpty() {
	return (front==-1 && rear==-1);
}
public void addGuess(char guess) {
    if (isFull()) {
        System.out.println("Queue is Full...");
    } else if (front == -1 && rear == -1) {
        front = rear = 0;
        letterGuessedByPlayer[rear] = guess;
    } else {
        rear++;
        letterGuessedByPlayer[rear] = guess;
    }
}
public char peek() {
	
return letterGuessedByPlayer[front];

}
public boolean isGuessLimitReached() {
    return (wrongGuess >= maxAttempt);
}



}
