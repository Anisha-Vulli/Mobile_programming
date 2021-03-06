package com.example.android.implicitintents;


import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class game extends AppCompatActivity implements View.OnClickListener {

    int[] id_array = {R.id.box1, R.id.box2,R.id.box3, R.id.box4, R.id.box5, R.id.box6, R.id.box7, R.id.box8, R.id.box9, R.id.box10, R.id.box11, R.id.box12, R.id.box13, R.id.box14, R.id.box15, R.id.box16, R.id.box17, R.id.box18, R.id.box19, R.id.box20, R.id.box21, R.id.box22, R.id.box23, R.id.box24, R.id.box25};
    ArrayList<Integer> numbers = new ArrayList<>();
    int[] bingo_id = {R.id.b_id, R.id.i_id,R.id.n_id, R.id.g_id, R.id.o_id};
    int[] bingo_values = new int[25];
    int cou = 0;
    //int[] user_inp =  new int[25];
    String[] id_str_arr = {"R.id.box1", "R.id.box2","R.id.box3", "R.id.box4", "R.id.box5", "R.id.box6", "R.id.box7", "R.id.box8", "R.id.box9", "R.id.box10", "R.id.box11", "R.id.box12", "R.id.box13", "R.id.box14", "R.id.box15", "R.id.box16", "R.id.box17", "R.id.box18", "R.id.box19", "R.id.box20", "R.id.box21", "R.id.box22", "R.id.box23", "R.id.box24", "R.id.box25"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_game);

    }

    public boolean isNumber(String str) {
        try {
            int num = Integer.parseInt(str);
           if (num >= 1 && num <= 25) {
               return true;
           } else {
               return false;
           }
        } catch(NumberFormatException e){
            return false;
        }
    }


    public void onClickSetBoard(View view) {
        Button set_board = (Button) findViewById(R.id.set_board_button);
        LinearLayout bingo_layout = (LinearLayout) findViewById(R.id.bingo_id);
        int count = 0;
        Boolean check = true;
        for (int i = 0; i < 25; i++) {
            EditText boxID = (EditText) findViewById(id_array[i]);
            Log.d(TAG,Integer.toString(i));
            if (!TextUtils.isEmpty(boxID.getText().toString())) {
                String text = boxID.getText().toString();
                if (isNumber(text)) {
                    if (!numbers.contains(Integer.parseInt(text))) {
                        Log.d(TAG,"Numbers is ----->" + text);
                        numbers.add(Integer.parseInt(text));
                        count++;

                    } else {
                        Toast.makeText(this,
                                "Don't repeat the numbers and enter from 1 to 25 only",
                                Toast.LENGTH_SHORT)
                                .show();
                        check = false;
                        break;

                    }
                } else {
                    Toast.makeText(this,
                            "Please enter only numbers",
                            Toast.LENGTH_SHORT)
                            .show();
                    check = false;
                    break;
                }
            } else {
                check = false;
                break;
            }
        }

        if (count == 25) {
            for (int i = 0; i < 25; i++) {
                EditText boxID = (EditText) findViewById(id_array[i]);
                boxID.setFocusable(false);
                boxID.setGravity(Gravity.CENTER);
            }
        }


        if (check) {
            set_board.setVisibility(View.INVISIBLE);
            bingo_layout.setVisibility(View.VISIBLE);

        } else {
            Toast.makeText(this,
                    "Enter all numbers",
                    Toast.LENGTH_SHORT)
                    .show();
            set_board.setVisibility(View.VISIBLE);
        }

    }



    @Override
    public void onClick(View view) {
        Button button_id = (Button) findViewById(view.getId());

        String number = button_id.getText().toString();
        String TAG = "";
        for (int i = 0; i < 25; i++) {
            EditText number_in_blank = (EditText) findViewById(id_array[i]);
            if (number.equals(number_in_blank.getText().toString())) {
                button_id.setVisibility(View.INVISIBLE);
                number_in_blank.setPaintFlags(number_in_blank.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                number_in_blank.setTextColor(Color.RED);
                String[] sam = id_str_arr[i].split("x");
                bingo_values[Integer.parseInt(sam[1]) - 1] = 1;
                Log.d(TAG, Arrays.toString(bingo_values));
                check_bingo();
                for(int k = 0; k < cou; k ++){
                    TextView textid = (TextView) findViewById(bingo_id[k]);
                    textid.setTextColor(Color.GREEN);
                }
            }
        }
    }
    String TAG = "";
    private void check_bingo() {
        cou = 0;
        for(int i = 0; i < 5; i++){
            int samp1 = 0;
            int samp2 = 0;
            for(int j = 0; j < 5; j ++){
                if(bingo_values[(5*i) + j ]== 1){
                    samp1++;
                }
                if(bingo_values[(5*j) + i ]== 1){
                    samp2++;
                }
            }
            if(samp1 == 5){
                Log.d(TAG, "This is Horizontal section\n");
                cou ++;
            }
            if(samp2 == 5){
                Log.d(TAG, "This is Vertical section\n");
                cou ++;
            }
        }
        int samp = 0;
        for(int i = 0;i < 5; i++){

            if(bingo_values[(i*5)+i] == 1){
                samp++;
            }
        }
        if(samp == 5){
            Log.d(TAG, "This is Front Diagnol section\n");
            cou++;
        }
        samp = 0;
        for(int i = 4,j = 0;j < 5; i--,j++){
            if(bingo_values[(5*j)+i]==1){

                samp++;
            }
        }
        if(samp == 5){
            Log.d(TAG, "This is Back Diagnol section\n");
            cou++;
        }
    }
}
