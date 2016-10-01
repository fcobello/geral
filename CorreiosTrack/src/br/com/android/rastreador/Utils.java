package br.com.android.rastreador;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Utilitarios
 * @author Felipe Cobello
 *
 */
public class Utils {
	
	public static boolean checkInternet(Context ctx) 
	{
	    NetworkInfo info = (NetworkInfo) ((ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
	    
	    if (info == null || !info.isConnected()) 
	        return false;
	    if (info.isRoaming())
	        return false;
	    return true;
	}
	
	public static void showMessage(Context context, String msg)
	{	
		OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		};
		
		showMessage(context, msg, "OK", listener);
	}
	
	public static void showMessage(Context context, String msg, String button, OnClickListener listener)
	{
		AlertDialog alert = new AlertDialog.Builder(context).create();
		
		alert.setButton(button, listener);
		alert.setMessage(msg);
		alert.setIcon(android.R.drawable.ic_dialog_info);
		alert.show();
	}
	
	public static void showConfirmation(Context context, String msg, DialogInterface.OnClickListener listener)
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		
		alert.setPositiveButton("Sim", listener);
		alert.setNegativeButton("Não", null);
		alert.setMessage(msg);
		alert.setIcon(android.R.drawable.ic_dialog_alert);
		alert.show();
	}
}
