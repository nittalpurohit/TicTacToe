package com.example.nittal.purohit.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button[][] buttons = new Button[3][3];
    private boolean p1turn = true;
    private int roundCount;
    private int p1Points;
    private int p2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.tvp1);
        textViewPlayer2 = findViewById(R.id.tvp2);




        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String id = "b"+i+j;
                int resId = getResources().getIdentifier(id,"id",getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(((Button)v).getText().toString().equals(""))
        {
            if(p1turn==true)
            {
                ((Button) v).setText("X");
            }
            else
            {
                ((Button) v).setText("O");
            }

            roundCount++;

            if(checkForWin())
            {
                if(p1turn)
                {
                    player1Wins();
                }
                else
                {
                    player2Wins();
                }
            }
            else if(roundCount==9)
            {
                draw();
            }
            else
            {
                p1turn = !p1turn;
            }
        }
        return;
    }

    private void player1Wins()
    {
        p1Points++;
        resetBoard();
        updatePoints();
        Toast.makeText(this,"player 1 wins the game",Toast.LENGTH_SHORT).show();
    }

    private void player2Wins()
    {
        p2Points++;
        resetBoard();
        updatePoints();
        Toast.makeText(this,"player 2 wins the game",Toast.LENGTH_SHORT).show();
    }
    private void draw()
    {
        Toast.makeText(this,"match draw!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void resetBoard()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        p1turn=true;
    }
    private void updatePoints()
    {
        textViewPlayer1.setText("player1 points : "+p1Points);
        textViewPlayer2.setText("player2 points : "+p2Points);
    }

    private boolean checkForWin()
    {
        String[][] field = new String[3][3];

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i=0;i<3;i++)
        {
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][2].equals(""))
            {
                return true;
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[2][i].equals(""))
            {
                return true;
            }
        }
        if(field[0][0].equals(field[1][1]) && field[1][1].equals(field[2][2]) && !field[0][0].equals(""))
        {
            return true;
        }
        if(field[0][2].equals(field[1][1]) && field[1][1].equals(field[2][0]) && !field[0][2].equals(""))
        {
            return true;
        }
        return false;
    }
}
