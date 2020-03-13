package com.example.clockmessenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;


import com.example.clockmessenger.Adapter.ItemAdapter;
import com.example.clockmessenger.Model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{

    private RecyclerView rvItemList;
    private FloatingActionButton addFab;
    private ArrayList<Item> itemArrayList;
    private String name, message;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String currentTime = ZonedDateTime.now().toString();
        currentTime = currentTime.substring(0,10);

        rvItemList = findViewById(R.id.rvList);
        addFab = findViewById(R.id.addEvent);

        itemArrayList = new ArrayList<>();
        final ItemAdapter adapter = new ItemAdapter(this, itemArrayList);
        rvItemList.setAdapter(adapter);
        rvItemList.setLayoutManager(new LinearLayoutManager(this));

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder myDialog = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                final View myviewm = inflater.inflate(R.layout.custom_dialog, null);
                myDialog.setView(myviewm);
                final AlertDialog dialog = myDialog.create();
                dialog.setCanceledOnTouchOutside(true);

                final EditText etEventName = myviewm.findViewById(R.id.etEventName);
                final CalendarView calendarView = myviewm.findViewById(R.id.calendarView);
                final EditText etMessage = myviewm.findViewById(R.id.etEventMessage);

                Button btnSave = myviewm.findViewById(R.id.btnSave);
                Button btnCancel = myviewm.findViewById(R.id.btnCancel);

                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
                    {
                        if(month >= 10 && dayOfMonth >= 10)
                        {
                            selectedDate = year + "-" + month + "-" + dayOfMonth;
                        }
                        else if(dayOfMonth < 10 && month >= 10)
                        {
                            selectedDate = year + "-" + month + "-0" + dayOfMonth;
                        }
                        else if(dayOfMonth >= 10 && month < 10)
                        {
                            selectedDate = year + "-0" + month + "-" + dayOfMonth;
                        }
                        else
                        {
                            selectedDate = year + "-0" + month + "-0" + dayOfMonth;
                        }

                    }
                });


                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        name = etEventName.getText().toString();
                        message = etMessage.getText().toString();

                        if(TextUtils.isEmpty(name))
                        {
                            etEventName.setError("Name is empty");
                        }
                        if(TextUtils.isEmpty(message))
                        {
                            etMessage.setError("Message is empty");
                        }
                        if(selectedDate == null)
                        {
                            Toast.makeText(MainActivity.this, "Please select a date", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Item item = new Item(name, selectedDate, message);
                            itemArrayList.add(item);
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}
