package com.example.android.implicitintents;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class paired_devices extends AppCompatActivity {
    //RecyclerView list;

    static final int STATE_LISTENING = 1;
    static final int STATE_CONNECTING = 2;
    static final int STATE_CONNECTED = 3;
    static final int STATE_CONNECTION_FAILED = 4;
    static final int STATE_MESSAGE_RECIEVED = 5;

    private static  final String APP_NAME = "Bingo";
    private static  final UUID MY_UUID = UUID.fromString("7deceefa-4c60-11e9-8646-d663bd873d93");

    BluetoothDevice[] btArray;
    ListView listView;
    ListView availabledevices;
    BluetoothAdapter bluetoothAdapter;
    ArrayAdapter<String> availabledevadapter;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paireddevices);
        availabledevices  = (ListView) findViewById(R.id.available_devices);
        //list = (RecyclerView) findViewById(R.id.);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> bt = bluetoothAdapter.getBondedDevices();
        String[] strings = new String[bt.size()];
        btArray = new BluetoothDevice[bt.size()];
        int index = 0;
        if(bt.size() > 0) {
            for(BluetoothDevice device : bt) {
                Log.d("Debugging", "I am here");
                btArray[index] = device;
                strings[index] = device.getName();
                index++;

            }
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_list_item, strings);

            listView  = (ListView) findViewById(R.id.paired_devices_list);
            listView.setAdapter(arrayAdapter);


//            list.setText(arrayAdapter.getItem(0));
        }

        bluetoothAdapter.startDiscovery();
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(myReceiver, intentFilter);
        availabledevadapter = new ArrayAdapter<>(this, R.layout.available_devices_list, arrayList);
        for(int i = 0; i<availabledevadapter.getCount(); i++) {
            Log.e("arrauyyyy", "" + availabledevadapter.getItem(i));

        }

        availabledevices.setAdapter(availabledevadapter);
        Intent discovwwerability_intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discovwwerability_intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,100);
        startActivity(discovwwerability_intent);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClientClass clientClass = new ClientClass(btArray[i]);
                clientClass.start();

                Toast.makeText(getApplicationContext(), "connecting", Toast.LENGTH_LONG).show();
            }
        });
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

    private class ClientClass extends Thread {
        private BluetoothDevice device;
        private BluetoothSocket socket;
        public ClientClass(BluetoothDevice device) {
            this.device = device;
            try {
                socket = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public  void run() {
            try {
                socket.connect();
                Message message = Message.obtain();
                message.what = STATE_CONNECTED;
                handler.sendMessage(message);
                Intent connectIntent = new Intent(paired_devices.this, game.class);
                startActivity(connectIntent);
            }
            catch (IOException e) {
                e.printStackTrace();
                Message message = Message.obtain();
                message.what = STATE_CONNECTION_FAILED;
                handler.sendMessage(message);
            }
        }
    }


    BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                arrayList.add(device.getName());
                availabledevadapter.notifyDataSetChanged();
            }
        }
    };

}
