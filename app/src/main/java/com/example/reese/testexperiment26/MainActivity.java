package com.example.reese.testexperiment26;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.Second);
        final Handler handler = new Handler(){
            @Override
            public  void  handleMessage(Message msg) {
                textView.setText(msg.arg1+"");
            }
        };

        final Runnable myWorker = new Runnable() {//创建接口
            @Override
            public void run() {
                int progress = 0;
                while (progress <= 1000) {
                    Message msg = new Message();
                    msg.arg1 = progress;
                    handler.sendMessage(msg);//发送
                    progress += 1;
                    msg.obj = Thread.currentThread().getName();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage();//接收
                handler.sendMessage(msg);//把message对象发送出去
            }
        };

        // private boolean click=true;
        Button button = (Button) findViewById(R.id.buttontext);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // if(click) click=false;
                Thread workThread = new Thread(null,myWorker,"WorkThread");
                workThread.start();
            }
        });
    }
}