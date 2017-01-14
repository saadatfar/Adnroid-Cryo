package com.Tirax.RF.Options.Auto.Lipolysis;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;
import com.example.cryo.R;

/**
 * Created by Lenovo on 8/3/2016.
 */
public class AutoLipolysisManNeckOption extends Mode {

    public AutoLipolysisManNeckOption(){
        super();
        power =50;
        isPulse = false;
        frequency =4000;
        monobi = "BHF";
        type=Types.HF;
        time =45;
        handpieceImg = R.drawable.bihandler;
    }
}
