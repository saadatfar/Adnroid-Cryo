package com.Tirax.RF.SerialPortsHardware;

import com.Tirax.RF.Enums.VersionTypes;
import com.Tirax.RF.Errors.*;
import com.Tirax.RF.Errors.Error;
import com.Tirax.RF.SerialPortsHardware.ReadWriteSerialPort;

import java.util.ArrayList;
import java.util.List;

public class DataProvider extends ReadWriteSerialPort {
	
	public static final VersionTypes VERSION = VersionTypes.CAVITATION;
	//Registers
	public static char RMER = 0;//Micro Error
	public static char RUER = 1;//UI Error
	public static char RMST = 10;//Micro Status
	public static char RUST = 11;//UI Status
	public static char RMKY = 12;//Micro Keys
	public static char RTYP0 = 20;//Type0
	public static char RTYP1 = 21;//Type1
	public static char RFRQ = 22;//Freq. (Freq/100)
	public static char RPWR = 23;//Power
	public static char RPFRQ = 24;//Pulse Freq.
	public static char RPLEN = 25;//Pulse Length (Pulse/10)ms
	public static char RSNUM = 26;//Shots Num.
	public static char RVCLV = 27;//Vacu. Level
	public static char RCRN = 40;//Current (Current/50)mA
	public static char RTMP = 50;//Temperature

	public static String[] registersName = new String[]{"RMER", "RUER","2","3","4","5","6","7","8","9","RMST","RUST","RMKY","13","14","15","16","17","18","19","RTYP0","RTYP1","RFRQ","RPWR","RPFRQ","RPLEN","RSNUM","RVCLV","28","29","30","31","32","33","34","35","36","37","38","39","RCRN1","RCRN2","RCRN3","43","44","45","46","47","48","49","RTMP1","RTMP2","RTMP3"};
	public static boolean[] registersUseful = new boolean[]{true, true,false,false,false,false,false,false,false,false,true,true,true,false,false,false,false,false,false,false,true,true,true,true,true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,false,false,false,false,false,false,false,true,true,true};
	//Bits
	public static char RST_ON= 0;//OFF/ON
	public static char RST_BUSY= 1;//Busy

	public static char RTYP0_HF= 0;//HF
	public static char RTYP0_LF= 1;//LF
	public static char RTYP0_VACUM= 2;//Vaccum
	public static char RTYP0_FRAC= 3;//Frac

	public static char RTYP1_CNT= 0;//Cnt/Pulse
	public static char RTYP1_AUTO= 1;//Auro/Pedal

	public static char RMKY_PAUSE= 0;//Cnt/Pulse

	public static ArrayList<com.Tirax.RF.Errors.Error> errors;
	private static boolean beforeErr=false;

	//set registers

	public static boolean isDeviceOn() {
		return false;
	}

	public static void initialize_errors(){
		errors = new ArrayList<Error>();
		errors.add(new Error124());
	}

	public static boolean isError() {
		if(getRegister(RMER)>0){
			beforeErr = true;
			return true;
		}

		beforeErr = false;
		return false;
	}

	public static Error getError(){
		int error_num = getRegister(RMER);
		for(int i=0;i<errors.size();i++){
			if(errors.get(i).number==error_num){
				return errors.get(i);
			}
		}
		return null;
	}



	public static boolean isNotInError() {
		return !beforeErr;
	}


	public static String getRegisterName(int num){
		if(num<registersName.length){
			return registersName[num];
		}
		else
			return "Aut of Range Register";
	}

}
