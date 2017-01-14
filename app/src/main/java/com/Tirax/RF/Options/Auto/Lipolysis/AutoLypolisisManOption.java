package com.Tirax.RF.Options.Auto.Lipolysis;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;
import com.example.cryo.R;

/**
 * Created by Lenovo on 8/3/2016.
 */
    public class AutoLypolisisManOption extends Mode {
//TODO Delete
    public AutoLypolisisManOption(){
        super();
        power =50;
        frequency =500;
        monobi = "monopolar";
        type=Types.LF;
        time =45;
        handpieceImg = R.drawable.monohandler;
    }
}
