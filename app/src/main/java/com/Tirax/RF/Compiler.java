package com.Tirax.RF;

import android.util.Log;

import com.Tirax.RF.SerialPortsHardware.DataProvider;

/**
 * Created by a.irani on 11/1/2016.
 */
public class Compiler {

    public static void setRegisters(Mode mode){


        DataProvider.setRegister(DataProvider.RPWR, (char) mode.power);

        DataProvider.setRegister(DataProvider.RFRQ, (char) (mode.frequency / 100));

        DataProvider.setRegister(DataProvider.RPFRQ, (char) mode.pulseFrq);

        DataProvider.setRegister(DataProvider.RPLEN, (char) (mode.pulseLength / 10));

        DataProvider.setRegister(DataProvider.RTYP1, getType1(mode.continuePulse, mode.autoPedal));

        DataProvider.setRegister(DataProvider.RTYP0, getType0((char) mode.type.getValue()));

        if (LogCatEnabler.compilerSetRegLog) {
            Log.e("TIRAXREG", "POWER" + mode.power);
            Log.e("TIRAXREG", "RFRQ" + (mode.frequency / 100));
            Log.e("TIRAXREG", "RPFRQ" + mode.pulseFrq);
            Log.e("TIRAXREG", "RPLEN" + (mode.pulseLength / 10));
            Log.e("TIRAXREG", "RTYP1" + (int) getType1(mode.continuePulse, mode.autoPedal));
            Log.e("TIRAXREG", "RTYP0" + (int)getType0((char) mode.type.getValue()));
        }
    }

    private  static char getType0(char bit) {
        return  (char)(1 << bit);
    }

    private static char getType1(boolean cp, boolean ap){

        char type1 = (char)((cp?1:0 << 0) | ( ap?1:0 << 1 ));
        return type1;

    }


    public static void setRTYPRegister(Mode mode) {
        DataProvider.setRegister(DataProvider.RTYP0,getType0((char) mode.type.getValue()));
        Log.e("TIRAXREG", "RTYP0" + (int)getType0((char) mode.type.getValue()));
    }
}
