package com.cidi.nettytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NettyUDPSocket.ConnectState {

    private Button btn;
    private static final String IP = "192.168.8.150";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NettyUDPSocket.ConnectStateListener(this);
        NettyUDPSocket.initNettyUdpSocket();
        NettyUDPSocket.connect(IP,8889);


    }

    @Override
    public void connectSuccess(byte[] result, int port) {
        Log.e("数据",port+"---->"+new String(result));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NettyUDPSocket.onClose();
    }


}
