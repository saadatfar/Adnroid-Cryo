package com.Tirax.RF;

import android.animation.ValueAnimator;
import android.graphics.pdf.PdfDocument;
import android.util.Log;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Storage.Pages;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.R;

/**
 * Mother of type of diffrent options in program
 */
public class Mode {

    public int power =0;
    public int frequency =1000;
    public int pulseFrq=0;
    public int pulseLength=0;
    public Types type=Types.HF;
    public String monobi;
    public int time=45;
    public boolean autoPedal;
    public boolean continuePulse;
    public int vacuumLevel=0;
    public Mode secondMode=null;
    public int handpieceImg;

    public Mode(){
        power = Values.power;
        frequency = Values.frequency;
        pulseFrq= Values.pulseFrq;
        pulseLength=Values.pulseLength;
        type=Values.type;
        monobi = getMonoBi();
        time=Values.time;
        autoPedal=Values.continuePulse;
        continuePulse= Values.autoPedal;
        vacuumLevel = Values.vacuumLevel;
        handpieceImg = getImage();
    }

    private int getImage() {
        if (Pages.bi_mono== Pages.MONOPOLAR) {
            return R.drawable.monohandler;
        } else
            if (Pages.bi_mono== Pages.BIPOLAR_HIGH || Pages.bi_mono== Pages.BIPOLAR_LOW || Pages.bi_mono== Pages.BIPOLAR)
                return R.drawable.bihandler;
            else
                return 0;

    }

    private String getMonoBi() {
        String monobi="";
        if(Pages.bi_mono== Pages.BIPOLAR_HIGH)
            monobi = "BHF";
        else if(Pages.bi_mono== Pages.BIPOLAR_LOW)
            monobi = "BLF";
        else if(Pages.bi_mono== Pages.BIPOLAR)
            monobi = "Bipolar";
        else if(Pages.bi_mono== Pages.MONOPOLAR)
            monobi = "Monopolar";
        else if(Pages.bi_mono == Pages.MANUAL_CAVITATION)
            monobi = "Cavitation";
        return  monobi;
    }

    public String completeName(){
        if(monobi.equals("BHF"))
            return "Bipolar High Frequency";
        else
            if(monobi.equals("BLF"))
                return "Bipolar Low Frequency";
            else
                return monobi;
    }


}
