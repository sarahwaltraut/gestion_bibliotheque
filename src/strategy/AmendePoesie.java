package strategy;

public class AmendePoesie implements StrategieAmende {
    @Override
    public double calculerAmende(int joursDeRetard) {
        return joursDeRetard * 2.5; // 2.5€ par jour de retard
    }
}
