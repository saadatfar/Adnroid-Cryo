package com.Tirax.RF.Options.Auto.Lipolysis;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;
import com.Tirax.RF.Storage.Values;

/**
 * Created by Lenovo on 8/3/2016.
 */
public class AutoLipolysisWomanBodyFirstOption extends Mode {



    public AutoLipolysisWomanBodyFirstOption(){
        super();
        power =25;
        frequency =300;
        pulseFrq=10;
        pulseLength=50;
        monobi = "BLF";
       type=Types.LF;

    }
}
