package com.Tirax.RF.Options.Auto.Lipolysis;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.R;

/**
 * Created by Lenovo on 8/3/2016.
 */
public class AutoLipolysisWomanBodyFirstOption extends Mode {



    public AutoLipolysisWomanBodyFirstOption(){
        super();
        power =50;
        frequency =300;
        isPulse = false;
        monobi = "BLF";
        type=Types.LF;
        time =40;
        handpieceImg = R.drawable.bihandler;

    }
}
