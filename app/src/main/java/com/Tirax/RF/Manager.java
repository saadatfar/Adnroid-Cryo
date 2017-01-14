package com.Tirax.RF;

import android.util.Log;

import com.Tirax.RF.Enums.BodyPart;
import com.Tirax.RF.Enums.VersionTypes;
import com.Tirax.RF.Options.Auto.Lipolysis.AutoLipolysisManBodyFirstOption;
import com.Tirax.RF.Options.Auto.Lipolysis.AutoLipolysisManBodySecondOption;
import com.Tirax.RF.Options.Auto.Lipolysis.AutoLipolysisManFaceOption;
import com.Tirax.RF.Options.Auto.Lipolysis.AutoLipolysisManLegsOption;
import com.Tirax.RF.Options.Auto.Lipolysis.AutoLipolysisManNeckOption;
import com.Tirax.RF.Options.Auto.Lipolysis.AutoLipolysisWomanBodyFirstOption;
import com.Tirax.RF.Options.Auto.Lipolysis.AutoLipolysisWomanBodySecondOption;
import com.Tirax.RF.Options.Auto.Lipolysis.AutoLipolysisWomanLegsOption;
import com.Tirax.RF.Options.Auto.Lipolysis.AutoLipolysisWomanNeckOption;
import com.Tirax.RF.Options.Auto.Lipolysis.AutoLypolisisManOption;
import com.Tirax.RF.Options.Auto.Lipolysis.AutoLipolysisWomanFaceOption;
import com.Tirax.RF.Options.Auto.Lifting.AutoLiftingManBodyOption;
import com.Tirax.RF.Options.Auto.Lifting.AutoLiftingManFaceOption;
import com.Tirax.RF.Options.Auto.Lifting.AutoLiftingWomanBodyOption;
import com.Tirax.RF.Options.Auto.Lifting.AutoLiftingWomanFaceOption;
import com.Tirax.RF.Options.Auto.Tightening.AutoTighteningManBodyOption;
import com.Tirax.RF.Options.Auto.Tightening.AutoTighteningManFaceOption;
import com.Tirax.RF.Options.Auto.Tightening.AutoTighteningWomanBodyOption;
import com.Tirax.RF.Options.Auto.Tightening.AutoTighteningWomanFaceOption;
import com.Tirax.RF.Options.CavitationOption;
import com.Tirax.RF.Options.ManualOption;
import com.Tirax.RF.SerialPortsHardware.DataProvider;
import com.Tirax.RF.Storage.Pages;

import main.activity.Auto.AutoActivity;
import main.activity.Auto.TightLipoActivity;
import main.activity.MainActivity;
import main.activity.Manual.ManualHighLowActivity;
import main.activity.Manual.ManualLpgVaccumBhfMonoActivity;

/**
 * Created by a.irani on 11/1/2016.
 */
public class Manager {
    public static Mode getType(){
        if(DataProvider.VERSION == VersionTypes.CAVITATION){
            return getTypeCavitation();
        }
        if (Pages.auto_manual == Pages.MANUAL) {
            if (Pages.bi_mono == Pages.MONOPOLAR) {

                return new ManualOption();

            } else {
                if(Pages.bi_mono == Pages.BIPOLAR_HIGH) {
                    return new ManualOption();
                }else
                {
                    return new ManualOption();
                }

            }
        } else {
            if (Pages.auto_type == Pages.TIGHTENING) {
                if (Pages.part_of_body == BodyPart.FACE) {
                    if (Pages.woman_man == Pages.MAN) {

                        return new AutoTighteningManFaceOption();

                    } else {

                        return new AutoTighteningWomanFaceOption();
                    }
                } else {
                    if (Pages.woman_man == Pages.MAN) {

                        return new AutoTighteningManBodyOption();

                    } else {

                        return new AutoTighteningWomanBodyOption();
                    }
                }
            } else if (Pages.auto_type == Pages.LIFTING) {
                if (Pages.part_of_body == BodyPart.FACE) {
                    if (Pages.woman_man == Pages.MAN) {

                        return new AutoLiftingManFaceOption();

                    } else {

                        return new AutoLiftingWomanFaceOption();
                    }
                } else {
                    if (Pages.woman_man == Pages.MAN) {

                        return new AutoLiftingManBodyOption();

                    } else {

                        return new AutoLiftingWomanBodyOption();
                    }
                }

            } else if (Pages.auto_type == Pages.LYPOLISI) {
                if (Pages.woman_man == Pages.MAN) {
                    Log.e("TIRAX ST", "iamhere");
                    return new AutoLypolisisManOption();

                } else {

                    return new AutoLipolysisWomanFaceOption();
                }
            }
            else if (Pages.auto_type == Pages.LIFTING) {
                if (Pages.part_of_body == BodyPart.FACE) {
                    if (Pages.woman_man == Pages.MAN) {

                        return new AutoLiftingManFaceOption();

                    } else {

                        return new AutoLiftingWomanFaceOption();
                    }
                } else {
                    if (Pages.woman_man == Pages.MAN) {
                        return new AutoLiftingManBodyOption();


                    } else {

                        return new AutoLiftingWomanBodyOption();
                    }
                }
            }

        }
        return null;
    }


    public static Class<?> getNextPage(Class<?> nowPage) {


        if(nowPage == MainActivity.class){

            if(Pages.auto_manual == Pages.AUTO){

                if(DataProvider.VERSION == VersionTypes.CRYORF){

                    return TightLipoActivity.class;

                }
                else{

                    return AutoActivity.class;

                }

            }else if(Pages.auto_manual == Pages.MANUAL){

                if(DataProvider.VERSION == VersionTypes.CRYORF){

                    return ManualLpgVaccumBhfMonoActivity.class;

                }
                else{

                    return ManualHighLowActivity.class;

                }
            }
        }
        return null;
    }
    public static Mode getTypeCavitation(){
        if (Pages.auto_manual == Pages.MANUAL) {


                return new ManualOption();


        } else {

            if (Pages.auto_type == Pages.TIGHTENING) {
                if (Pages.part_of_body == BodyPart.FACE) {
                    if (Pages.woman_man == Pages.MAN) {

                        return new AutoTighteningManFaceOption();


                    } else {

                        return new AutoTighteningWomanFaceOption();
                    }
                } else {
                    if (Pages.woman_man == Pages.MAN) {

                        return new AutoTighteningManBodyOption();

                    } else {

                        return new AutoTighteningWomanBodyOption();
                    }
                }
            } else if (Pages.auto_type == Pages.LIFTING) {
                if (Pages.part_of_body == BodyPart.FACE) {
                    if (Pages.woman_man == Pages.MAN) {

                        return new AutoLiftingManFaceOption();

                    } else {

                        return new AutoLiftingWomanFaceOption();
                    }
                } else {
                    if (Pages.woman_man == Pages.MAN) {

                        return new AutoLiftingManBodyOption();

                    } else {

                        return new AutoLiftingWomanBodyOption();
                    }
                }

            } else if (Pages.auto_type == Pages.LYPOLISI) {

                return getLypolisisCavitationPages();

            }
            else if (Pages.auto_type == Pages.LIFTING) {
                if (Pages.part_of_body == BodyPart.FACE) {
                    if (Pages.woman_man == Pages.MAN) {

                        return new AutoLiftingManFaceOption();

                    } else {

                        return new AutoLiftingWomanFaceOption();
                    }
                } else {
                    if (Pages.woman_man == Pages.MAN) {
                        return new AutoLiftingManBodyOption();


                    } else {

                        return new AutoLiftingWomanBodyOption();
                    }
                }
            }else if (Pages.auto_type == Pages.CAVITATION) {
                return new CavitationOption();
            }

        }
        return null;
    }

    private static Mode getLypolisisCavitationPages() {
        if(Pages.part_of_body == BodyPart.FACE){
            if(Pages.woman_man == Pages.MAN){
                return new AutoLipolysisManFaceOption();
            }
            else {
                return new AutoLipolysisWomanFaceOption();
            }
        }
        if(Pages.part_of_body == BodyPart.NECK){
            if(Pages.woman_man == Pages.MAN){

                return new AutoLipolysisManNeckOption();
            }
            else {

                return new AutoLipolysisWomanNeckOption();
            }
        }
        if(Pages.part_of_body == BodyPart.BODY){
            if(Pages.woman_man == Pages.MAN){
                if(Pages.auto_stage == Pages.FIRST)
                    return new AutoLipolysisManBodyFirstOption();
                else
                    return new AutoLipolysisManBodySecondOption();
            }
            else {
                if(Pages.auto_stage == Pages.FIRST)
                    return new AutoLipolysisWomanBodyFirstOption();
                else
                    return new AutoLipolysisWomanBodySecondOption();
            }
        }
        if(Pages.part_of_body == BodyPart.LEGS){
            if(Pages.woman_man == Pages.MAN){
                return new AutoLipolysisManLegsOption();

            }
            else {
                return new AutoLipolysisWomanLegsOption();
            }
        }
        return null;
    }


};

