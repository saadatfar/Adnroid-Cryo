package com.Tirax.cryo;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

public class ReadWriteSerialPort extends AsyncTask<Void, Void, Void>{

	private final static Integer RESENDTIME = 300;

	protected final static Integer REGISTERS_NUMBER = 16;
	
	private final static char WRITE_STARTBIT =255;
	private final char ACK_STARTBIT =254;
	
	private final int WAIT_START = 0;
	private final int START_RECIEVED = 1;
	private final int WAIT_VALUE=2;
	
	private static ArrayList<Character> registers;
	private static ArrayList<Character> changedRegisters = new ArrayList<Character>();

	
	private int writeState = 0;
	private int ackState = 0;
	private char writeReg;
	private static char waited_ack;
	
	private static int resendTimer = 0;
	private static int resetTimer=10;
	private Handler ResendRunnableHandler=new Handler();

	//TODO remov and use better idea
	public final static char regMachineState = 15;
	@Override
	protected Void doInBackground(Void... voids) {
		try {
			initialRegisters();
			ResendRunnableHandler.postDelayed(ResendRunnable, 0);

			while (true) {
				readSerialPort();
				if (reset())
					senAllRegisters();
			}
		} catch (Exception ex) {
			Log.e("TIRAX ERROR", "error accured" + ex);
		}

		return null;
	}
	private void senAllRegisters() {
		Log.e("TIRAX", "******************************reset micro***********************************");
		offReset();
		for(int i=0;i<REGISTERS_NUMBER;i++) {
			addToChangedRegisters((char) i);
		}

	}
	protected boolean reset() {
		return false;
	}
	protected void offReset(){}
	protected void onReset(){}
	private void initialRegisters() {
		registers = new ArrayList<Character>(REGISTERS_NUMBER);
		if(SharedPrefrences.getReseted()){
				Log.e("TIRAX","initiate registers from sharedprefrences");
				registers = SharedPrefrences.getRegisters();
				SharedPrefrences.setReseted(false);
				logRegisters();
		}else {

			for (int i = 0; i < REGISTERS_NUMBER; i++) {
				registers.add((char) 0);
			}


		}
		senAllRegisters();
	}
	protected void readSerialPort(){

		if(SerialPort.isData()){
			char c= SerialPort.read();

			if(c==WRITE_STARTBIT){
				writeState = START_RECIEVED;
				ackState = WAIT_START;
			}else if(writeState==START_RECIEVED){
				writeReg = c;
				writeState = WAIT_VALUE;
			}else if(writeState==WAIT_VALUE){
				if(writeReg==regMachineState && c==(char)32)//TODO remove and better idea
					onReset();
				else
					if(writeReg<REGISTERS_NUMBER)
						registers.set(writeReg, c);
					else{
						writeState = WAIT_START;
						Log.e("TIRAX Error","Reisters Out of bound exception.");
					}
				sendAck(calcAckCode(writeReg, c));
				writeState = WAIT_START;
			}			
			if(c==ACK_STARTBIT){

				ackState = START_RECIEVED;
				writeState = WAIT_START;
			}else if(ackState == START_RECIEVED){

				if(waited_ack == c){
					waited_ack=0;
					changedRegisters.remove(0);
					resendTimer= 0;
					resetTimer =10;
					Log.e("TIRAx", "Ack Accepted");
					sendRegister();
					ackState = WAIT_START;

				}
			}
		}
	}
	private static char calcAckCode(char r,char d){
		return (char) ((r ^ d) & 0x7f);
	}
	private void sendAck(char code){
		SerialPort.sendByte(ACK_STARTBIT);
		SerialPort.sendByte(code);	
	}
	private static void sendRegister(){
		if(changedRegisters.size()==0)
			return;
		char writeRegister=changedRegisters.get(0); 
		SerialPort.sendByte(WRITE_STARTBIT);
		SerialPort.sendByte(writeRegister);
		SerialPort.sendByte(registers.get(writeRegister));
		//Log.e("TIRAX", "calculated ack code");
		waited_ack = calcAckCode(writeRegister, registers.get(writeRegister));
		resendTimer=RESENDTIME;
	}
	
	public static void setRegister(char number, char value){
		if(number<REGISTERS_NUMBER) {
			registers.set(number, value);
			addToChangedRegisters(number);
		}
	}
	
	private static void addToChangedRegisters(char number) {
		changedRegisters.add(number);
		if(changedRegisters.size()==1)	
			sendRegister();
	}
	
	public static char getRegister(char number){
		return registers.get(number);	
	}
	
	public static boolean getBit(char number,char p){
		return (registers.get(number) & 1<<p) > 0;
	}
	
	public static void setBit(char number,char p, boolean v){
		if(number<REGISTERS_NUMBER) {
			if (v) {
				registers.set(number, (char) (registers.get(number) | 1 << p));
			} else {
				registers.set(number, (char) (registers.get(number) & (~(1 << p))));
			}
			addToChangedRegisters(number);
		}
	}

	public static void setBitNoSend(char number,char p, boolean v){
		if (v){
			registers.set(number, (char) (registers.get(number) | 1<<p));
		}else{
			registers.set(number, (char) (registers.get(number) & (~(1 << p))));
		}
	}

	public static void logRegisters(){
		for(int i=0;i<REGISTERS_NUMBER;i++)
			Log.e("TIRAX","Register "+i+" Value "+(int)registers.get(i));
	}

	public static ArrayList<Character> getAllRegisters() {
		return registers;
	}

	Runnable ResendRunnable = new Runnable(){

		@Override
		public void run() {
			if(resendTimer==1) {
				if (resetTimer == 1) {
					//logRegisters();
					ResetTask.resetSystem();
				}
				else {
					sendRegister();
					resetTimer--;
				}
			}
			if(resendTimer>0)
				resendTimer--;
			ResendRunnableHandler.postDelayed(ResendRunnable, 1);

			
		}
	
	
	};


}

