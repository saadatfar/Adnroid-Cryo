package com.Tirax.RF.Options.Auto.Lipolysis;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;
import com.example.cryo.R;

/**
 * Created by Lenovo on 8/3/2016.
 */
public class AutoLipolysisManBodySecondOption extends Mode {

    public AutoLipolysisManBodySecondOption(){
        super();
        power=100;
        frequency = 400;
        type = Types.LF;
        monobi ="Monopolar";
        handpieceImg  = R.drawable.monohandler;

    }
}
