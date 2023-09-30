import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class NumberGuessingGame {
    private JFrame frame;
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel instructionLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JButton playAgainButton;

    private Random rand = new Random();
    private int randNum;
    private int guess;
    private int tries;

    public NumberGuessingGame() {
        frame = new JFrame("Number Guessing Game");
        panel = new JPanel();
        panel.setBackground(Color.PINK);

        titleLabel = new JLabel("Welcome to the Number Guessing Game!");
        instructionLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.setBackground(Color.GREEN);
        resultLabel = new JLabel("");
        playAgainButton = new JButton("Play Again");
        playAgainButton.setBackground(Color.RED);

        randNum = rand.nextInt(100) + 1;
        tries = 0;

        guessButton.addActionListener(new GuessButtonListener());
        playAgainButton.addActionListener(new PlayAgainButtonListener());

        panel.setLayout(new GridBagLayout()); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1; 
        instructionLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(instructionLabel, gbc);

        gbc.gridy = 2;
        panel.add(guessField, gbc);

        gbc.gridy = 3;
        panel.add(guessButton, gbc);

        gbc.gridy = 4;
        panel.add(resultLabel, gbc);

        gbc.gridy = 5;
        panel.add(playAgainButton, gbc);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private class GuessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                guess = Integer.parseInt(guessField.getText());
                tries++;

                if (guess == randNum) {
                    resultLabel.setText("Awesome! You guessed the number in " + tries + " tries.");
                    guessButton.setEnabled(false);
                    playAgainButton.setEnabled(true);
                } else if (guess > randNum) {
                    resultLabel.setText("Your guess is too high. Try again.");
                } else {
                    resultLabel.setText("Your guess is too low. Try again.");
                }
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid input. Please enter a number.");
            }
        }
    }

    private class PlayAgainButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            randNum = rand.nextInt(100) + 1;
            guessField.setText("");
            resultLabel.setText("");
            tries = 0;
            guessButton.setEnabled(true);
            playAgainButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
}
