package com.Tirax.RF.Options.Auto.Lipolysis;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;

/**
 * Created by Lenovo on 8/3/2016.
 */
public class AutoLipolysisWomanLegsOption extends Mode {

    public AutoLipolysisWomanLegsOption(){
        super();
        power =20;
        frequency =300;
        pulseFrq=10;
        pulseLength=50;
        monobi = "BLF";
        type=Types.LF;
    }
}
