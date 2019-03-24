package com.example.android.implicitintents;


import android.content.Intent;
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
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class game extends AppCompatActivity implements View.OnClickListener {


    int[] id_array = {R.id.box1, R.id.box2,R.id.box3, R.id.box4, R.id.box5, R.id.box6, R.id.box7, R.id.box8, R.id.box9, R.id.box10, R.id.box11, R.id.box12, R.id.box13, R.id.box14, R.id.box15, R.id.box16, R.id.box17, R.id.box18, R.id.box19, R.id.box20, R.id.box21, R.id.box22, R.id.box23, R.id.box24, R.id.box25};

    int[] bingo_id = {R.id.b_id, R.id.i_id,R.id.n_id, R.id.g_id, R.id.o_id};
    int[] bingo_values = new int[25];
    int cou = 0;
    //int[] user_inp =  new int[25];
    //system sys = new system();
    String[] id_str_arr = {"R.id.box1", "R.id.box2","R.id.box3", "R.id.box4", "R.id.box5", "R.id.box6", "R.id.box7", "R.id.box8", "R.id.box9", "R.id.box10", "R.id.box11", "R.id.box12", "R.id.box13", "R.id.box14", "R.id.box15", "R.id.box16", "R.id.box17", "R.id.box18", "R.id.box19", "R.id.box20", "R.id.box21", "R.id.box22", "R.id.box23", "R.id.box24", "R.id.box25"};
    static int[]  buttons_id = {R.id.button_1,R.id.button_2,R.id.button_3,R.id.button_4,R.id.button_5,R.id.button_6,R.id.button_7,R.id.button_8,R.id.button_9,R.id.button_10,R.id.button_11,R.id.button_12,R.id.button_13,R.id.button_14,R.id.button_15,R.id.button_16,R.id.button_17,R.id.button_18,R.id.button_19,R.id.button_20,R.id.button_21,R.id.button_22,R.id.button_23,R.id.button_24,R.id.button_25};

    // System Variables
    int[] textviewid = {R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5,R.id.textView6,R.id.textView7,R.id.textView8,R.id.textView9,R.id.textView10,R.id.textView11,R.id.textView12,R.id.textView13,R.id.textView14,R.id.textView15,R.id.textView16,R.id.textView17,R.id.textView18,R.id.textView19,R.id.textView20,R.id.textView21,R.id.textView22,R.id.textView23,R.id.textView24,R.id.textView25};
    String[] textview_str_id = {"R.id.textView1","R.id.textView2","R.id.textView3","R.id.textView4","R.id.textView5","R.id.textView6","R.id.textView7","R.id.textView8","R.id.textView9","R.id.textView10","R.id.textView11","R.id.textView12","R.id.textView13","R.id.textView14","R.id.textView15","R.id.textView16","R.id.textView17","R.id.textView18","R.id.textView19","R.id.textView20","R.id.textView21","R.id.textView22","R.id.textView23","R.id.textView24","R.id.textView25"};
    int[] bingo_sys_id = {R.id.bs_id, R.id.is_id, R.id.ns_id, R.id.gs_id, R.id.os_id};
    int[] bingo_sys_values = new int[25];
    int[] array = {1, 2, 3, 4,5 ,6, 7 ,8, 9 ,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
    int cous = 0;
    Set<Integer> set = new HashSet<>();
    public void enableButtons() {
        Button b;
        for(int i = 0; i < 25; i++){
            b = (Button) findViewById(buttons_id[i]);
            b.setEnabled(true);
        }
    }

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
        ArrayList<Integer> numbers = new ArrayList<>();
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
//            Intent intent = new Intent(game.this, system.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
            //system sys = new system();
            //sys.setBoard();
            setBoard();

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
                Log.d("On click  bingo values", Arrays.toString(bingo_values));
                check_bingo();
                if(cou > 5){
                    cou = 5;
                }
                for(int k = 0; k < cou; k ++){
                    TextView textid = (TextView) findViewById(bingo_id[k]);
                    textid.setTextColor(Color.GREEN);
                }
                if(cou == 5){
                    Toast.makeText(this,
                            "User won the Game",
                            Toast.LENGTH_SHORT)
                            .show();
                    android.support.v7.widget.GridLayout g = (android.support.v7.widget.GridLayout) findViewById(R.id.user_buttons);
                    g.setVisibility(View.INVISIBLE);
                    Button b = (Button) findViewById(R.id.okay_user_button);
                    b.setVisibility(View.VISIBLE);
                }

                markTextView(number);
                blockButtons();
                flow();
            }
        }
    }
    public void onSetUser(String s){
        String number = s;
       Button button_id = (Button) findViewById(buttons_id[Integer.parseInt(number) - 1]);
        String TAG = "";
        for (int i = 0; i < 25; i++) {
            EditText number_in_blank = (EditText) findViewById(id_array[i]);
            if (number.equals(number_in_blank.getText().toString())) {
                button_id.setVisibility(View.INVISIBLE);
                number_in_blank.setPaintFlags(number_in_blank.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                number_in_blank.setTextColor(Color.RED);
                String[] sam = id_str_arr[i].split("x");
                bingo_values[Integer.parseInt(sam[1]) - 1] = 1;
                Log.d(" on Set User Values ", Arrays.toString(bingo_values));
                check_bingo();
                if(cou > 5){
                    cou = 5;
                }
                for(int k = 0; k < cou; k ++){
                    TextView textid = (TextView) findViewById(bingo_id[k]);
                    textid.setTextColor(Color.GREEN);
                }
                if(cou == 5){
                    Toast.makeText(this,
                            "User won the Game",
                            Toast.LENGTH_SHORT)
                            .show();
                    android.support.v7.widget.GridLayout g = (android.support.v7.widget.GridLayout) findViewById(R.id.user_buttons);
                    g.setVisibility(View.INVISIBLE);
                    Button b = (Button) findViewById(R.id.okay_user_button);
                    b.setVisibility(View.VISIBLE);
                }


            }
        }
    }

    private void blockButtons() {
        Button b;
        for(int i = 0; i < 25; i++){
            b = (Button) findViewById(buttons_id[i]);
            b.setEnabled(false);
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

    // System Methods

    public void setBoard() {
        TextView text;
        array = RandomizeArray(array);
        for(int i = 0; i < 25; i++){
            text = (TextView) findViewById(textviewid[i]);
            text.setText(Integer.toString(array[i]));
        }
    }
    public int[] RandomizeArray(int[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }
    public void markTextView(String s){
        // TextView button_id = (TextView) findViewById(view.getId());
        Log.d("", "mark text view string s "+ s);
        String number = s;
        String TAG = "";
        set.add(Integer.parseInt(s));
        for (int i = 0; i < 25; i++) {
            TextView number_in_blank = (TextView) findViewById(textviewid[i]);
            if (number.equals(number_in_blank.getText().toString())) {
                //button_id.setVisibility(View.INVISIBLE);
                number_in_blank.setPaintFlags(number_in_blank.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                number_in_blank.setTextColor(Color.RED);
                String[] sam = textview_str_id[i].split("w");
                Log.d("", "Integer.parseInt in mark text view "+ sam[1]);
                bingo_sys_values[Integer.parseInt(sam[1]) - 1] = 1;
                Log.d("Bingo system es - view ", Arrays.toString(bingo_sys_values));
                check_sys_bingo();
                if(cous > 5){
                    cous = 5;
                }
                for(int k = 0; k < cous; k ++){
                    TextView textid = (TextView) findViewById(bingo_sys_id[k]);
                    textid.setTextColor(Color.GREEN);
                }
                if(cous== 5){
                    Toast.makeText(this,
                            "System won the Game",
                            Toast.LENGTH_SHORT)
                            .show();
                    LinearLayout l = (LinearLayout) findViewById(R.id.system_linear);
                    l.setVisibility(View.VISIBLE);
                    l = (LinearLayout) findViewById(R.id.user_linear);
                    l.setVisibility(View.INVISIBLE);
                    Button b = (Button) findViewById(R.id.okay_button);
                    b.setVisibility(View.VISIBLE);
                }

                //enableButtons();
            }
        }
    }
    public void marktext(String s){
        String number = s;
        String TAG = "";
        Log.d("", "mark text string s "+ s);
        set.add(Integer.parseInt(s));
        for (int i = 0; i < 25; i++) {
            TextView number_in_blank = (TextView) findViewById(textviewid[i]);
            if (number.equals(number_in_blank.getText().toString())) {
                //button_id.setVisibility(View.INVISIBLE);
                number_in_blank.setPaintFlags(number_in_blank.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                number_in_blank.setTextColor(Color.RED);
                String[] sam = textview_str_id[i].split("w");
                Log.d("", "Integer.parseInt in mark text "+ sam[1]);
                bingo_sys_values[Integer.parseInt(sam[1]) - 1] = 1;
                Log.d("mark text sys val ", Arrays.toString(bingo_sys_values));
                check_sys_bingo();
                if(cous > 5){
                    cous = 5;
                }
                for(int k = 0; k < cous; k ++){
                    TextView textid = (TextView) findViewById(bingo_sys_id[k]);
                    textid.setTextColor(Color.GREEN);
                }
                if(cous== 5){
                    Toast.makeText(this,
                            "System won the Game",
                            Toast.LENGTH_SHORT)
                            .show();
                    LinearLayout l = (LinearLayout) findViewById(R.id.system_linear);
                    l.setVisibility(View.VISIBLE);
                    l = (LinearLayout) findViewById(R.id.user_linear);
                    l.setVisibility(View.INVISIBLE);
                    Button b = (Button) findViewById(R.id.okay_button);
                    b.setVisibility(View.VISIBLE);
                }
                onSetUser(number);
                enableButtons();
            }
        }
    }
    //TAG = "";
    private void check_sys_bingo() {
        cous = 0;
        for(int i = 0; i < 5; i++){
            int samp1 = 0;
            int samp2 = 0;
            for(int j = 0; j < 5; j ++){
                if(bingo_sys_values[(5*i) + j ]== 1){
                    samp1++;
                }
                if(bingo_sys_values[(5*j) + i ]== 1){
                    samp2++;
                }
            }
            if(samp1 == 5){
                Log.d(TAG, "This is Horizontal section\n");
                cous ++;
            }
            if(samp2 == 5){
                Log.d(TAG, "This is Vertical section\n");
                cous ++;
            }
        }
        int samp = 0;
        for(int i = 0;i < 5; i++){

            if(bingo_sys_values[(i*5)+i] == 1){
                samp++;
            }
        }
        if(samp == 5){
            Log.d(TAG, "This is Front Diagnol section\n");
            cous++;
        }
        samp = 0;
        for(int i = 4,j = 0;j < 5; i--,j++){
            if(bingo_sys_values[(5*j)+i]==1){

                samp++;
            }
        }
        if(samp == 5){
            Log.d(TAG, "This is Back Diagnol section\n");
            cous++;
        }
    }

    public void flow() {
        Random rgen = new Random();


        Set<Integer> sample = new HashSet<Integer>(Arrays.asList(new Integer[] {1, 2, 3, 4,5 ,6, 7 ,8, 9 ,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25}));
        sample.removeAll(set);
        Log.d("", "sample after removing duplicates"+ sample.toString());
        Integer[] rem_array = Arrays.copyOf(sample.toArray(), sample.size(), Integer[].class);
        Log.d("", " rem Array --- "+ Arrays.toString(rem_array));
        int ran = rgen.nextInt(rem_array.length);
        Log.d("", " ran ----"+ ran);
        int randomNumber = rem_array[rgen.nextInt(rem_array.length)];
        Log.d("", "flow --- randomNumber --  "+ randomNumber);
        marktext(Integer.toString(randomNumber));

    }
    public void onClickOkay(View view){
        Intent intent = new Intent(game.this, MainActivity.class);
        startActivity(intent);
    }
}
