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
        power=100;
        frequency = 400;
        type = Types.LF;
        monobi ="Monopolar";
        handpieceImg  = R.drawable.monohandler;

    }
}
