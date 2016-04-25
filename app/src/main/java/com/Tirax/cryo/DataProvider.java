package com.Tirax.cryo;

import android.util.Log;

public class DataProvider extends ReadWriteSerialPort {
	
	public final static char[] regTemp = {0,1,2,3,4,5,6,7};
	public final static char[] regRefTemp = {8,9,10,11};
	public final static char regMaxTemp = 12;
	public final static char regPress = 13;
	public final static char regLpgFrqns = 14;
	//bit 7-->on off bit 6-->cryo lpg 5-->reset 4-->r handpiece 3--> l handpiece 2--> vaccum handpiece
	public final static char regMachineState = 15;

	private static char START_PRESSURE=3;
	public static final int VACCUM_TEMP=0;
	public static final int RIGHT_TEMP=2;
	public static final int LEFT_TEMP=3;

	public static int FINAL_TEMP=-10;
	public static final int STABILITY_TIME=5;
	//set registers
	public static void setTemp(int number, char value){
		setRegister(regTemp[number], value);
	}
	public static void setTempRefrence(int number, char value){
		FINAL_TEMP = value-30;
		//if temp<-5 set temp -1
		if(value<=25) {
			setRegister(regRefTemp[number], (char) 29);
		}
		else
			//if temp<0 set temp 1
			if(value<=30)
				setRegister(regRefTemp[number], (char)31);
			else
				setRegister(regRefTemp[number], value);
	}
	public static void setMaxTemp(char value){
		setRegister(regMaxTemp, value);
	}
	public static void setPresure(char value){
		setRegister(regPress, value);
	}
	public static void setLpgFrqns(char value){
		setRegister(regLpgFrqns, value);
	}
	public static void setMachineState(char value){
		setRegister(regMachineState, value);
	}
	
	//get registers
	public static int getTemp(int number){
		return (int)getRegister(regTemp[number])-30;
	}
	public static char getTempRefrence(int number){
		return getRegister(regRefTemp[number]);
	}
	public static char getMaxTemp(){
		return getRegister(regMaxTemp);
	}
	public static char getPresure(){
		return getRegister(regPress);
	}
	public static char getLpgFrqns(){
		return getRegister(regLpgFrqns);
	}
	public static char getMachineState(){
		return getRegister(regMachineState);
	}

	public static boolean isDeviceOn() {
		return getBit(regMachineState, (char) 7);
	}

	public static boolean getCryo() {
		return getBit(regMachineState, (char) 6);
	}


	protected boolean reset() {
		return getBit(regMachineState, (char) 5);
	}
	protected void offReset() { setBitNoSend(regMachineState, (char) 5,false);}
	protected void onReset() { setBitNoSend(regMachineState, (char) 5, true);}
	public static void setLpg(){
		setBit(regMachineState, (char) 6, false);
	}
	public static void setCryo(){setBit(regMachineState, (char) 6,true);}

	public static void deviceOff(){setBit(regMachineState, (char) 7,false);}
	public static void deviceOn(){setBit(regMachineState, (char) 7,true);}
	public static void setHandpiece(boolean right,boolean left,boolean vaccum){
		setBitNoSend(regMachineState, (char) 4, right);
		setBitNoSend(regMachineState, (char) 3, left);
		setBit(regMachineState, (char) 2,vaccum);
	}
	public static void setStartPresure() {
		setPresure(START_PRESSURE);
	}

	public static int getDisplayTemp(int number,int time){

		int result = (int)getRegister(regTemp[number])-30;
		if((number==VACCUM_TEMP &&  getBit(regMachineState,(char)2)) ||
				(number==LEFT_TEMP &&  getBit(regMachineState,(char)3))||
				(number==RIGHT_TEMP &&  getBit(regMachineState,(char)4))) {
			if (time >= STABILITY_TIME)
				result = FINAL_TEMP;
			else
				result = Math.round(result + (FINAL_TEMP - result) * time / STABILITY_TIME);
		}
		return result;

	}


}
