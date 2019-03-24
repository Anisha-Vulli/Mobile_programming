package com.example.android.implicitintents;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class connectivity extends AppCompatActivity {
    BluetoothAdapter bluetoothAdapter;
    Button join;
    Intent btEnablingIntent;
    int requestCodeForEnabled;

    static final int STATE_LISTENING = 1;
    static final int STATE_CONNECTING = 2;
    static final int STATE_CONNECTED = 3;
    static final int STATE_CONNECTION_FAILED = 4;
    static final int STATE_MESSAGE_RECIEVED = 5;

    private static  final String APP_NAME = "Bingo";
    private static  final UUID MY_UUID = UUID.fromString("7deceefa-4c60-11e9-8646-d663bd873d93");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectivity);

        join = (Button) findViewById(R.id.join);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btEnablingIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestCodeForEnabled = 1;
        bluetoothOnMethod();
    }


    public void onClickHost(View view) {
        Toast.makeText(getApplicationContext(), "Connecting", Toast.LENGTH_LONG).show();
        ServerClass serverClass = new ServerClass();
        serverClass.start();
        Intent game_intent = new Intent(connectivity.this, game.class);
        startActivity(game_intent);
    }

    public  void onClickJoin(View view) {
        Intent join_intent = new Intent(connectivity.this, paired_devices.class);
        startActivity(join_intent);
    }
    Handler handler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(Message message) {
            switch(message.what) {
                case STATE_LISTENING:
                    Toast.makeText(getApplicationContext(),"listening",Toast.LENGTH_LONG).show();
                    break;
                case STATE_CONNECTING:
                    Toast.makeText(getApplicationContext(),"connecting",Toast.LENGTH_LONG).show();
                    break;
                case STATE_CONNECTED:
                    Toast.makeText(getApplicationContext(),"Connected",Toast.LENGTH_LONG).show();
                    break;
                case STATE_CONNECTION_FAILED:
                    Toast.makeText(getApplicationContext(),"connection failed",Toast.LENGTH_LONG).show();
                    break;
                case STATE_MESSAGE_RECIEVED:
                    Toast.makeText(getApplicationContext(),"message received",Toast.LENGTH_LONG).show();
                    break;

            }
            return true;
        }
    });

    private class ServerClass extends Thread {
        private BluetoothServerSocket serverSocket;

        public ServerClass() {
            try {
                serverSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord(APP_NAME, MY_UUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            BluetoothSocket socket = null;

            while(socket == null) {
                try {
                    Message message = Message.obtain();
                    message.what = STATE_CONNECTING;
                    handler.sendMessage(message);
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    Message message = Message.obtain();
                    message.what = STATE_CONNECTION_FAILED;
                    handler.sendMessage(message);
                    e.printStackTrace();
                }
                if(socket != null) {
                    Message message = Message.obtain();
                    message.what = STATE_CONNECTED;
                    handler.sendMessage(message);
                    break;

                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == requestCodeForEnabled) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "bluetooth is enabled", Toast.LENGTH_LONG).show();
                Intent new_intent = new Intent(connectivity.this, paired_devices.class);
                startActivity(new_intent);
            } else if(requestCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "bluetooth is not enabled", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void bluetoothOnMethod() {
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bluetoothAdapter == null) {
                    Toast.makeText(getApplicationContext(), " bluetooth is not supported in this device", Toast.LENGTH_LONG).show();
                }
                else {
                    if(!bluetoothAdapter.isEnabled()) {
                        startActivityForResult(btEnablingIntent,requestCodeForEnabled);
                    }
                }
            }
        });
    }
}
