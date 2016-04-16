package com.example.orgware.deletesms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Orgware on 4/16/2016.
 */
public class SMS  extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            if (extras != null) {
                Object[] pdus = (Object[])extras.get("pdus");

                if (pdus.length < 1) return; // Invalid SMS. Not sure that it's possible.

                StringBuilder sb = new StringBuilder();
                String sender = null;
                for (int i = 0; i < pdus.length; i++) {
                    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    if (sender == null) sender = message.getOriginatingAddress();
                    String text = message.getMessageBody();
                    if (text != null) sb.append(text);
                }
                if (sender != null && sender.equals("+919994267918")) {
                    // Process our sms...
                    Toast.makeText(context,"Message Received from raja",Toast.LENGTH_LONG).show();
                    abortBroadcast();
                }
                return;
            }
        }

    }
}
