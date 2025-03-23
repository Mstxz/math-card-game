package utils;

public class Calculation {
    public static double mapToLogarithmicScale(float percent){
        return Math.log10(1+9*percent);
    }
    public static float percentOfRange(float start,float end,float percent){
        return (end - start) * percent + start;
    }

    public static float percentOfRangeLog10(float start,float end,float percent){
        return (end - start) * (float) mapToLogarithmicScale(percent) + start;
    }
}
