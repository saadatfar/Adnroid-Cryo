package com.Tirax.RF.Storage;

import com.Tirax.RF.Enums.BodyPart;

/**
 * Created by a.irani on 11/1/2016.
 */
public class Pages {


    public static final int BIPOLAR_HIGH = 3;
    public static final int BIPOLAR_LOW = 2;
    public static final int VACUUM =4 ;
    public static final int LPG =5 ;
    public static final int MANUAL_CAVITATION = 6;

    public static int AUTO = 0;
    public static int MANUAL = 1;

    public static int BIPOLAR = 1;
    public static int MONOPOLAR = 0;

    public static int WOMAN = 0;
    public static int MAN = 1;

    public static int TIGHTENING = 0;
    public static int LYPOLISI = 1;
    public static int LIFTING = 2;
    public static final int CAVITATION = 3;

    public static int CONTINUE=0;
    public static int PULSE=1;

    public static final int FIRST = 0;
    public static final int SECOND = 1;

    public static int auto_manual = AUTO;
    public static int bi_mono = BIPOLAR;
    public static BodyPart part_of_body = BodyPart.FACE;
    public static int woman_man = WOMAN;
    public static int auto_type = TIGHTENING;
    public static int auto_stage = FIRST;

}
