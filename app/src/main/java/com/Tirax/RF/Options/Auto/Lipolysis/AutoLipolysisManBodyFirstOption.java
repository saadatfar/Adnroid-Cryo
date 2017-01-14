package com.Tirax.RF.Options.Auto.Lipolysis;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;
import com.example.cryo.R;

/**
 * Created by Lenovo on 8/3/2016.
 */
public class AutoLipolysisManBodyFirstOption extends Mode {

    public AutoLipolysisManBodyFirstOption(){
        super();
        power =50;
        frequency =300;
        monobi = "BLF";
        type=Types.LF;
        time =40;
        handpieceImg = R.drawable.bihandler;
        isPulse = false;

    }
}
