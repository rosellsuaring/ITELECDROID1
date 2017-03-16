package com.example.smsresponder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MySMSResponder extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub

	try {
		Bundle b=arg1.getExtras();
		Object[]pdus=(Object[])b.get("pdus");
		for (int i=0;i<pdus.length;i++){
			SmsMessage sms=SmsMessage.createFromPdu((byte[])pdus[i]);
			String senderNumber=sms.getDisplayOriginatingAddress();
			String message=sms.getDisplayMessageBody();
			
			Toast.makeText(context,"Sender:"+senderNumber+"Message:"+message,Toast.LENGTH_LONG).show();
			
			SmsManager mngr=SmsManager.getDefault();
			mngr.sendTextMessage(senderNumber,"","Your last Message:"+message,null,null);
		
		
		
		}
	} catch (Exception e) {
		
		Log.e("SmsResponder",e.getMessage());
		}

	}

}
