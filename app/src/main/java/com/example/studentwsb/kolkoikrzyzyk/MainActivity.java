package com.example.studentwsb.kolkoikrzyzyk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0 - zolty, 1 - czerwony, 2 - pusty

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);


            for (int[] winningPositions : winningPositions) {
                if (gameState[winningPositions[0]] == gameState[winningPositions[1]]
                        && gameState[winningPositions[1]] == gameState[winningPositions[2]]
                        && gameState[winningPositions[0]] != 2) {

                    gameActive = false;

                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Zolty";
                    } else {
                        winner = "Czerwony";
                    }
                    Button playAgainButton = findViewById(R.id.playAgain);
                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + "wygrał!");
                    winnerTextView.setVisibility((View.VISIBLE));
                    playAgainButton.setVisibility((View.VISIBLE));
                }
            }


        }
    }

    public void playAgain(View view){
        Button playAgainButton = findViewById(R.id.playAgain);
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility((View.INVISIBLE));
        playAgainButton.setVisibility((View.INVISIBLE));

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            // do stuff with child view
            child.setImageDrawable(null);
        }

       activePlayer = 0;
       for(int i = 0; i<gameState.length; i++){
           gameState[i]=2;
       }
       gameActive = true;
    }
}
