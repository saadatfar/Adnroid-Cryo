package com.Tirax.RF.Options.Auto.Lipolysis;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;

/**
 * Created by Lenovo on 8/3/2016.
 */
public class AutoLipolysisManBodyFirstOption extends Mode {

    public AutoLipolysisManBodyFirstOption(){
        super();
        power =30;
        frequency =300;
        pulseFrq=10;
        pulseLength=50;
        monobi = "BLF";
        Types type=Types.LF;

    }
}
