
// to win this game : Start by guessing the middle of the range (e.g., 50 in this case).
//  This way, you quickly narrow down the possibilities.Based on the feedback (too high or too low), adjust your guess accordingly. 
//  For example, if the feedback is "too high," guess a number lower than your previous guess, and vice versa.
//  RefinE your guess based on the feedback until you correctly guess the target number or run out of attempts.
//it is generated randomly 


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGameGUI extends JFrame {
    private int lowerBound = 1;
    private int upperBound = 100;
    private int maxAttempts = 10;
    private int targetNumber;
    private int attempts;
    private int score;
    private int rounds;

    private JLabel instructionLabel;
    private JTextField guessField;
    private JButton submitButton;
    private JLabel feedbackLabel;
    private JLabel scoreLabel;
    private JLabel roundLabel;
    private JButton playAgainButton;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        instructionLabel = new JLabel("Guess the number between " + lowerBound + " and " + upperBound + ":");
        add(instructionLabel);

        guessField = new JTextField();
        add(guessField);

        submitButton = new JButton("Submit Guess");
        add(submitButton);

        feedbackLabel = new JLabel("");
        add(feedbackLabel);

        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);

        roundLabel = new JLabel("Round: 0");
        add(roundLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setEnabled(false);
        add(playAgainButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        resetGame();
    }

    private void resetGame() {
        targetNumber = (int) (Math.random() * (upperBound - lowerBound + 1)) + lowerBound;
        attempts = 0;
        rounds++;
        feedbackLabel.setText("");
        guessField.setText("");
        submitButton.setEnabled(true);
        playAgainButton.setEnabled(false);
        roundLabel.setText("Round: " + rounds);
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess == targetNumber) {
                feedbackLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                score += maxAttempts - attempts + 1;
                scoreLabel.setText("Score: " + score);
                submitButton.setEnabled(false);
                playAgainButton.setEnabled(true);
            } else if (userGuess < targetNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else {
                feedbackLabel.setText("Too high! Try again.");
            }

            if (attempts >= maxAttempts) {
                feedbackLabel.setText("Sorry, you've run out of attempts. The correct number was: " + targetNumber);
                submitButton.setEnabled(false);
                playAgainButton.setEnabled(true);
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Invalid input. Please enter a number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGameGUI().setVisible(true);
            }
        });
    }
}

