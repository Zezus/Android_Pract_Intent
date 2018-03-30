package com.example.azia.pract2_intent;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button calendar;
    private Button timer;
    private Button browser;
    private Button mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = findViewById(R.id.am_calendar_btn);
        timer = findViewById(R.id.am_timer_btn);
        browser = findViewById(R.id.am_browser_btn);
        mail = findViewById(R.id.am_mail_btn);


    }

    public void implicitCall(View view) {
        calendar.setOnClickListener(view2 -> {
            Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
            builder.appendPath("time");
            ContentUris.appendId(builder, Calendar.getInstance().getTimeInMillis());
            Intent intent = new Intent(Intent.ACTION_VIEW)
                    .setData(builder.build());
            startActivity(intent);
        });

        timer.setOnClickListener(view1 -> {
            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            i.putExtra(AlarmClock.EXTRA_MESSAGE, "На работу успеть чтоб, встать ты должен");
            i.putExtra(AlarmClock.EXTRA_HOUR, 6);
            i.putExtra(AlarmClock.EXTRA_MINUTES, 6);
            startActivity(i);
        });

        browser.setOnClickListener(view1 -> {
            Uri web = Uri.parse("http://www.google.com");
            Intent i = new Intent(Intent.ACTION_VIEW, web);
            startActivity(Intent.createChooser(i, "Выберите программу"));
        });

        mail.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Zezus");
            intent.putExtra(Intent.EXTRA_TEXT, "Hello bro");
            startActivity(intent);
        });

    }

}
