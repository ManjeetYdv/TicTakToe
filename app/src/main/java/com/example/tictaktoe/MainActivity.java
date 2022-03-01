package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean gameactive = true;
    int draw =0;
    int activePlayer =0;                                    //playerrepresentation -> 0->X  , 1->0
    int[] gamestate ={2,2,2,2,2,2,2,2,2};                  //0->X , 1->0 , 2->blank
    int[][] winningpos={{0,1,2} , {3,4,5} , {6,7,8} ,{0,3,6} ,{1,4,7} , {2,5,8} , {0 ,4,8} ,{2,4,6}};



    public void playerTap(View view){
        if(!gameactive){
            reset(view);
        }
        ImageView img =  (ImageView)view;
        int tapped = Integer.parseInt(img.getTag().toString());

        if(gamestate[tapped]==2){
            gamestate[tapped]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0){
                img.setImageResource(R.drawable.x);
                TextView status = findViewById(R.id.status);
                status.setText(getString(R.string.Oturn));
                activePlayer=1;
                draw++;
            }
            else {
                img.setImageResource(R.drawable.o);
                TextView status = findViewById(R.id.status);
                status.setText(getString(R.string.Xturn));
                activePlayer = 0;
                draw++;
            }
            img.animate().translationYBy(1000f).setDuration(200);
        }

        for(int[] winPosition: winningpos){
            if(gamestate[winPosition[0]] == gamestate[winPosition[1]] &&
                    gamestate[winPosition[1]] == gamestate[winPosition[2]] &&
                    gamestate[winPosition[0]]!=2){
                 String winner ;
                 if( gamestate[winPosition[0]]==1){
                     winner ="0 has won";
                 }
                 else{
                     winner ="X has won";
                 }
                gameactive= false;
                 TextView status = findViewById(R.id.status);
                 status.setText(winner);
             }



        }
        if(draw==9){
            reset(view);
            TextView status = findViewById(R.id.status);
            status.setText("Draw!! "+ getString(R.string.Xturn));


        }




    }

    public void reset(View view){
        gameactive= true;
        activePlayer =0;
//
//        for(int i=0 ;i<gamestate.length ; i++){
//            gamestate[i]=2;
//        }
        Arrays.fill(gamestate ,2);

        TextView status = findViewById(R.id.status);
        status.setText(getString(R.string.Xturn));

        ( (ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ( (ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ( (ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ( (ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ( (ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ( (ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ( (ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ( (ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ( (ImageView) findViewById(R.id.imageView8)).setImageResource(0);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}