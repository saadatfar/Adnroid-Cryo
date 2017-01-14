package com.Tirax.RF.Options.Auto.Lipolysis;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;
import com.example.cryo.R;

/**
 * Created by Lenovo on 8/3/2016.
 */
public class AutoLipolysisWomanFaceOption extends Mode {

    public AutoLipolysisWomanFaceOption(){
        super();
        power =50;
        frequency =4000;
        type=Types.HF;
        time =45;
        monobi = "BHF";
        handpieceImg = R.drawable.bihandler;
        isPulse = false;
    }
}
