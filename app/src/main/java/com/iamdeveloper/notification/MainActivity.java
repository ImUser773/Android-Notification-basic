package com.iamdeveloper.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    String[] message = {"คุณได้รับข้อความใหม่","กรุณากดเพื่อดูข้อความ"
            ,"กรุณากดเพื่อดูข้อความ","กรุณากดเพื่อดูข้อความ"
            ,"กรุณากดเพื่อดูข้อความ","กรุณากดเพื่อดูข้อความ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                NotificationCompat.Builder notification = new NotificationCompat.Builder(MainActivity.this);
                notification.setSmallIcon(R.mipmap.ic_launcher);
                NotificationCompat.InboxStyle inbox = new NotificationCompat.InboxStyle();
                inbox.setBigContentTitle("New Message");
                for(String i :message){
                    inbox.addLine(i);
                }
                notification.setStyle(inbox).setAutoCancel(true);


                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                Intent intentNotification= new Intent(MainActivity.this,NotificationActivity.class);
                TaskStackBuilder builder = TaskStackBuilder.create(MainActivity.this);
                builder.addParentStack(MainActivity.class);
                builder.addNextIntent(intent);
                builder.addNextIntent(intentNotification);
                PendingIntent pIntent = builder.getPendingIntent(0,PendingIntent.FLAG_CANCEL_CURRENT);
                notification.setContentIntent(pIntent);


                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(1,notification.build());

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
