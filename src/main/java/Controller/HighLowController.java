package Controller;


import java.util.Random;

public class HighLowController {
  private int correct;
  private int guess;
  
  public void setGuess(int guess) {
    this.guess = guess;
  }
  
  public void reset() {
    this.correct = new Random().nextInt(0, 10);
  }
  
  public int judge() {
    return Integer.compare(guess, correct);
  }
  
  public String feedback() {
    
    if(judge() == 0)
    {
    	return "correct";
    }
    else if(judge() == 1)
    {
    	return "guess lower";
    }
    return "guess higher";
  }
}