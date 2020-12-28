package com.pratik.multimediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    enum Player{
        ONE,TWO,NO
    }
    Player currentPlayer = Player.ONE;
    private int no = 0;

    Player[] playerChoices = new Player[9];
    int [][] umpire = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i=0;i<playerChoices.length;i++){
            playerChoices[i] = Player.NO;
        }

    }


    public void imageIsTapped(View image) {

            ImageView tappedImage = (ImageView) image;
            int imTag = Integer.parseInt(tappedImage.getTag().toString());
            if (playerChoices[imTag] == Player.NO) {
                tappedImage.setTranslationX(-2000);
                playerChoices[imTag] = currentPlayer;
                if (currentPlayer == Player.ONE) {
                    tappedImage.setImageResource(R.drawable.tiger);
                    currentPlayer = Player.TWO;
                } else if (currentPlayer == Player.TWO) {
                    tappedImage.setImageResource(R.drawable.lion);
                    currentPlayer = Player.ONE;
                }

                Toast.makeText(this, tappedImage.getTag().toString() + "is Tapped", Toast.LENGTH_SHORT).show();
                tappedImage.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);
                for (int[] winnerColumns : umpire) {
                    no = no + 1;
                    if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]]
                            && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]
                            && playerChoices[winnerColumns[0]] != Player.NO && no <= 8) {
                        String winner = new String();
                        if (currentPlayer == Player.ONE) {
                            winner = "Player Two";
                        } else if (currentPlayer == Player.TWO) {
                            winner = "Player One";
                        }
                        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                                .setTitle(winner + " is winner")
                                .setCancelable(false)
                                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                        dialog.show();

                    }else if (no >= 9){

                        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                                .setTitle("Match Draw")
                                .setCancelable(false)
                                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });

                }
            }
        }
    }
}