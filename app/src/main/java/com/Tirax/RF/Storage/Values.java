package com.Tirax.RF.Storage;

import android.util.Log;

import com.Tirax.RF.Enums.Types;

/**
 * Created by a.irani on 11/1/2016.
 */
public class Values {
    public static int PAUSE = 0;
    public static int power =0;
    public static int frequency =1000;
    public static int pulseFrq=0;
    public static int pulseLength=0;
    public static int time=45;
    public static Types type = Types.HF;
    public static boolean autoPedal;
    public static boolean isPulse;
    public static int vacuumLevel=0;
    public static int weight=0;
    public static int height=0;


    public static int getBMI(){
        Log.e("TIRAX BMI","bmi is: "+ (int)Math.floor(weight/(height*height/10000)));
        return (int)Math.floor(weight/(height*height/10000));
    }
}
