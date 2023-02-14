package com.example.tic_tae_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean game_active = true;
    // Player Representation
    // 0 - X;
    // 1 - O;
    int activePlayer = 0; // It is determined that X will start the game;
    int gameState[] = { 2, 2, 2, 2, 2, 2, 2, 2, 2 }; // Initial condition of the gameArray
    // State Meaning
    /*
     * 0- x
     * 1- o
     * 2- Null
     */
    int WinningPos[][] = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // row
            { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // column
            { 0, 4, 8 }, { 2, 4, 6 } };// diagonals

    public void game_reset(View view) {
        game_active = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2; // Re-initialising the array;
        }

        // Creating null images on existing space;
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn : Tap to Play");
    }

    int draw = 0;

    public void PlayerTap(View view) {

        ImageView img = (ImageView) view; // img provides the view tapped.
        int tapped_img = Integer.parseInt(img.getTag().toString()); // provides the tag
        if (!game_active) {
            game_reset(view);

        }

        if (gameState[tapped_img] == 2) // if position is NUll
        {
            gameState[tapped_img] = activePlayer;
            img.setTranslationY(-1000f); // Corresponds a view from up to down (Transition)
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.cross);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn : Tap to Play");
            } else {

                img.setImageResource(R.drawable.zero);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn : Tap to Play");

            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        // Check if any player has won
        for (int[] win_pos : WinningPos) {
            if (gameState[win_pos[0]] == gameState[win_pos[1]] && gameState[win_pos[1]] == gameState[win_pos[2]]
                    && gameState[win_pos[0]] != 2) {
                String win;
                if (gameState[win_pos[0]] == 0) {
                    win = "X has won";
                } else{
                    win = "0 has won";
                }
            // Update the status bar for the winner categorisation
            TextView status = findViewById(R.id.status);
            status.setText(win);
            game_active = false;
            break;
            }

        }
        // Checks if Draw
        int flag = 0;
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[i] == 2 && flag == 0) {
                flag = 1;
            }
        }
        if (flag == 0) {
            TextView status = findViewById(R.id.status);
            status.setText("Draw");
            game_active = false;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}