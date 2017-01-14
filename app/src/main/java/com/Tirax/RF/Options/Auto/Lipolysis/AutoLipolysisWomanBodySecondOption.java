package com.Tirax.RF.Options.Auto.Lipolysis;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.R;

/**
 * Created by Lenovo on 8/3/2016.
 */
public class AutoLipolysisWomanBodySecondOption extends Mode {

    public AutoLipolysisWomanBodySecondOption(){
        super();
        power=50;
        frequency = 500;
        type = Types.LF;
        monobi ="Monopolar";
        time =20;
        handpieceImg  = R.drawable.monohandler;
        isPulse = false;

    }
}
