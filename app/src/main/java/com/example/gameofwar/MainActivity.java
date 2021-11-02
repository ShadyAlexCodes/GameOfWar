package com.example.gameofwar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private final int[] playerOneCards = new int[26];
    private final int[] playerTwoCards = new int[26];

    TextView tvComputerCard, tvPlayerCard, tvValue;

    int playerOne, playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateArray(playerOneCards, 26, 1, 12);
        generateArray(playerTwoCards, 26, 1, 12);

        tvComputerCard = findViewById(R.id.txtComputerCard);
        tvPlayerCard = findViewById(R.id.txtPlayerCard);
        tvValue = findViewById(R.id.txtValue);
    }

    public void btnPlayGame(View view) {

        if (playerOneCards.length > 0 && playerTwoCards.length > 0) {
            playerOne = playerOneCards[0];
            playerTwo = playerTwoCards[0];

            System.out.println("PLAYER ONE: " + playerOne + "\nPLAYER TWO: " + playerTwo);
        } else if (playerOneCards.length > playerTwoCards.length) {
            System.out.println("PLAYER ONE WON");
        } else if (playerOneCards.length < playerTwoCards.length){
            System.out.println("PLAYER TWO WON");
        } else {
            System.out.println("oopsksksks");
        }
    }

    private void generateArray(int[] userArray, int totalCards, int minNumber, int maxNumber) {
        // Iterate through each card
        for (int i = 0; i < totalCards; i++) {
            // Assign number between 1 through twelve
            userArray[i] = generateRandomNumber(minNumber, maxNumber);
        }
        System.out.println("GENERATED: " + Arrays.toString(userArray));
    }

    private int generateRandomNumber(int min, int max) {
        // Generate a random number in the inclusivity range of the min and max
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}