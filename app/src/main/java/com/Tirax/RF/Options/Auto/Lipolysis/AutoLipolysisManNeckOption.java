package com.Tirax.RF.Options.Auto.Lipolysis;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;

/**
 * Created by Lenovo on 8/3/2016.
 */
public class AutoLipolysisManNeckOption extends Mode {

    public AutoLipolysisManNeckOption(){
        super();
        power =25;
        pulseFrq=10;
        pulseLength=50;
        frequency =700;
        monobi = "BLF";
        type=Types.LF;
    }
}
