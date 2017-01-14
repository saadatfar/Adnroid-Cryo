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
        power=50;
        frequency = 500;
        time =20;
        type = Types.LF;
        monobi ="Monopolar";
        handpieceImg  = R.drawable.monohandler;
        handpieceImg = R.drawable.monohandler;
        isPulse = false;

    }
}
