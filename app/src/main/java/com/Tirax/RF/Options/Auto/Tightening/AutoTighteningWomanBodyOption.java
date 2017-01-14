package com.Tirax.RF.Options.Auto.Tightening;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;
import com.example.cryo.R;

/**
 * Created by Lenovo on 8/3/2016.
 */
public class AutoTighteningWomanBodyOption extends Mode {

    public AutoTighteningWomanBodyOption(){
        super();
        power =50;
        isPulse = false;
        frequency =4000;
        monobi = "BHF";
        time =45;
        type=Types.HF;
        handpieceImg = R.drawable.bihandler;
    }
}
