package strategy;

public class AmendeRoman implements StrategieAmende {
    @Override
    public double calculerAmende(int joursDeRetard) {
        return joursDeRetard * 1.5; // 1.5€ par jour de retard
    }
}
