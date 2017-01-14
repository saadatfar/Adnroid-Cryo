package com.Tirax.RF.Options;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Mode;
import com.Tirax.RF.Storage.Pages;

/**
 * Created by a.irani on 12/13/2016.
 */
public class CavitationOption extends Mode {


    public CavitationOption(){

        super();
        if(Pages.auto_manual == Pages.AUTO){
            power = 50;
            handpieceImg =0;

        }
        isPulse = false;
        monobi = "Cavitation";
        type = Types.CAVITATION;
    }


}

