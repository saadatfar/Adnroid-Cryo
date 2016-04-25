package com.Tirax.cryo;


import android.util.Log;

import com.friendlyarm.AndroidSDK.HardwareControler;

import main.activity.*;

public class SerialPort {
	public static int fd=-1;
	
	public static void turnOn() {

		try{
			fd = HardwareControler.openSerialPort("/dev/s3c2410_serial0", 9600, 8, 1);
			Log.e("File Descriptor","fd: "+fd);
			if(fd==-1){
				//display error can not connect
				android.os.SystemClock.sleep(2000);
				turnOn();
			}
		}catch(Exception ex){
			Log.e("ERROR TIRAX ","in turn on serial port "+ex);
		}
			
	}
	
	public static void sendByte(char c){
		try{
			if(fd>-1){
				byte bt[]={Tools.charToByte(c)};
				//Log.e("TIRAX","send byte "+(int)c);
				HardwareControler.write(fd, bt);
			}
		}catch(Exception ex){
			Log.e("ERROR TIRAX ","in send on serial port "+ex);
		}
	}

	public static char read(){
		try{
			if(fd>-1){
				byte[] buf=new byte[1];
				HardwareControler.read(fd, buf, 1);
				char d=Tools.byteToChar(buf[0]);
				Log.e("TIRAX","read char "+(int)d);
				return d;
			}
			else
				return '\t';
		}catch(Exception ex){
			Log.e("ERROR TIRAX ","in read from serial port "+ex);
			return '\t';
		}
	}
	
	public static void turnOff(){
		try{
			HardwareControler.close(fd);
			fd=-1;
		}catch(Exception ex){
			Log.e("ERROR TIRAX ","in turn off on serial port "+ex);
		}
	}
	
	public static boolean isData(){
		try{
			int sts= HardwareControler.select(fd, 1, 0);
			if(sts==1)
				return true;
			else if(sts==0)
				return false;
			else
				return false;
		}catch(Exception ex){
			Log.e("ERROR TIRAX ","in check is data on serial port "+ex);
			return false;
		}
	}

	

}

