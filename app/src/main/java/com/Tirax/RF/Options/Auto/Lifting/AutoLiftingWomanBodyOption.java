package com.Tirax.RF.Options.Auto.Lifting;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;
import com.example.cryo.R;

/**
 * Created by Lenovo on 8/3/2016.
 */
public class AutoLiftingWomanBodyOption extends Mode {

    public AutoLiftingWomanBodyOption(){
        super();
        power =50;
        frequency =4000;
        monobi = "BHF";
        type=Types.HF;
        time =45;
        isPulse = false;
        handpieceImg = R.drawable.bihandler;
    }
}
