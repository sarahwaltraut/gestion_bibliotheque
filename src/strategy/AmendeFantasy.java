package strategy;

public class AmendeFantasy implements StrategieAmende {
    @Override
    public double calculerAmende(int joursDeRetard) {
        return joursDeRetard * 2; // 2€ par jour de retard
    }
}
