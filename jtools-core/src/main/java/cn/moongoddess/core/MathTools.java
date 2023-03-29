package cn.moongoddess.core;

import cn.moongoddess.core.metrology.RoundingOff;

public class MathTools {
    public static String getRoundingOff(String value, String level) { return new RoundingOff().get(value, level); }

}
