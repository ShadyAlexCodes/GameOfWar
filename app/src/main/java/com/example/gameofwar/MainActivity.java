package com.example.gameofwar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Integer> playerOneCards = new ArrayList<>();
    private final List<Integer> playerTwoCards = new ArrayList<>();

    TextView tvComputerCard, tvPlayerCard, tvValue, tvPlayerLeft, tvComputerLeft;

    int playerOne, playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateArray(playerOneCards, 5, 1, 12);
        generateArray(playerTwoCards, 5, 1, 12);

        tvComputerCard = findViewById(R.id.txtComputerCard);
        tvPlayerCard = findViewById(R.id.txtPlayerCard);
        tvValue = findViewById(R.id.txtValue);
        tvPlayerLeft = findViewById(R.id.tvPlayerLeft);
        tvComputerLeft = findViewById(R.id.tvComputerLeft);
    }

    public void btnPlayGame(View view) {
        Button button = (Button) findViewById(R.id.btnGenerate);
        if (playerOneCards.size() > 0 && playerTwoCards.size() > 0) {
            playerOne = playerOneCards.get(0);
            playerTwo = playerTwoCards.get(0);

            tvPlayerCard.setText(Integer.toString(playerOne));
            tvComputerCard.setText(Integer.toString(playerTwo));

            if(playerOne == playerTwo) {
                tvValue.setTextColor(Color.RED);
                tvValue.setText("There was a tie! Your deck has been shuffled!");
                System.out.println("UNSHUFFLED PLAYER: " + Arrays.toString(playerOneCards.toArray()) + "\nUNSHUFFLED COMPUTER: " + Arrays.toString(playerTwoCards.toArray()));
                Collections.shuffle(playerOneCards);
                Collections.shuffle(playerTwoCards);
                System.out.println("SHUFFLED PLAYER: " + Arrays.toString(playerOneCards.toArray()) + "\nSHUFFLED COMPUTER: " + Arrays.toString(playerTwoCards.toArray()));
            } else
            if(playerOne > playerTwo) {
                tvValue.setText("Player won this round!");
                playerOneCards.add(playerTwoCards.get(0));
                playerOneCards.add(playerOneCards.get(0));
                playerOneCards.remove(playerOneCards.get(0));
                playerTwoCards.remove(playerTwoCards.get(0));
                tvPlayerLeft.setText("Player has " + playerOneCards.size() + " cards left!");
                tvComputerLeft.setText("Computer has " + playerTwoCards.size() + " cards left!");
                System.out.println("P: " + Arrays.toString(playerOneCards.toArray()) + "\nPC: " + Arrays.toString(playerTwoCards.toArray()));
            } else {
                tvValue.setText("Computer won this round!");
                playerTwoCards.add(playerOneCards.get(0));
                playerTwoCards.add(playerTwoCards.get(0));
                playerTwoCards.remove(playerTwoCards.get(0));
                playerOneCards.remove(playerOneCards.get(0));
                tvPlayerLeft.setText("Player has " + playerOneCards.size() + " cards left!");
                tvComputerLeft.setText("Computer has " + playerTwoCards.size() + " cards left!");
                System.out.println("C: " + Arrays.toString(playerTwoCards.toArray()) + "\nCP: " + Arrays.toString(playerOneCards.toArray()));
            }
            System.out.println("PLAYER ONE: " + playerOne + "\nPLAYER TWO: " + playerTwo);
        } else if (playerOneCards.size() > playerTwoCards.size()) {
            button.setEnabled(false);
            tvValue.setTextColor(Color.GREEN);
            tvValue.setText("THE PLAYER WON");
            System.out.println("PLAYER ONE WON");
        } else if (playerOneCards.size() < playerTwoCards.size()){
            button.setEnabled(false);

            tvValue.setTextColor(Color.GREEN);
            tvValue.setText("THE COMPUTER WON");
            System.out.println("PLAYER TWO WON");
        } else {
            System.out.println("oopsksksks");
        }
    }

    private void generateArray(List<Integer> userArray, int totalCards, int minNumber, int maxNumber) {
        // Iterate through each card
        for (int i = 0; i < totalCards; i++) {
            // Assign number between 1 through twelve
            userArray.add(generateRandomNumber(minNumber, maxNumber));
        }
        System.out.println("GENERATED: " + Arrays.toString(userArray.toArray()));
    }

    private int generateRandomNumber(int min, int max) {
        // Generate a random number in the inclusivity range of the min and max
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}