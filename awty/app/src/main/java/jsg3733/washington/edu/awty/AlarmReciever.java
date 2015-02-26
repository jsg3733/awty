package jsg3733.washington.edu.awty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jsg3733 on 2/23/15.
 */
public class AlarmReciever extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {


        Log.i("AWTYtest", "In Alarm");
        Toast.makeText(context, "(425) 555-1212: Are we there yet?", Toast.LENGTH_SHORT).show();
    }
}
