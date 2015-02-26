package jsg3733.washington.edu.awty;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AWTY extends ActionBarActivity {

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awty);

        final Button submit = (Button) findViewById(R.id.btnAction);
        submit.setEnabled(false);

        final EditText message = (EditText) findViewById(R.id.edtxtMessage);
        final EditText phone = (EditText) findViewById(R.id.edtxtPhone);
        final EditText minsBetween = (EditText) findViewById(R.id.edtxtBetweenNag);

        TextWatcher chan = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String output = s.toString();
                //Log.i("AWTY", output);
                if(output.equals("")) {
                    submit.setEnabled(false);
                }
                if(!message.getText().toString().equals("") && !phone.getText().toString().equals("") && !minsBetween.getText().toString().equals("")) {
                    boolean checkInteger = false;
                    int mins = Integer.parseInt((minsBetween.getText().toString()));
                    if(mins >= 0) {
                        checkInteger = true;
                    }
                    if (checkInteger){
                        submit.setEnabled(true);
                    }
                }
            }
        };

        message.addTextChangedListener(chan);
        phone.addTextChangedListener(chan);
        minsBetween.addTextChangedListener(chan);

        Intent alarmIntent = new Intent(AWTY.this, AlarmReciever.class);
        pendingIntent = PendingIntent.getBroadcast(AWTY.this, 0, alarmIntent, 0);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //long time = 100;

                if(submit.getText().toString().equals("Start")) {
                    int mins = Integer.parseInt((minsBetween.getText().toString()));
                    submit.setText("Stop");
                    start(mins);
                }else {
                    submit.setText("Start");
                    cancel();

                }
            }
        });




    }

    public void start(int mins){
        //Intent alarmIntent = new Intent(AWTY.this, AlarmReciever.class);
        //pendingIntent = PendingIntent.getBroadcast(AWTY.this, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * mins;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        pendingIntent.cancel();
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_awty, menu);
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
