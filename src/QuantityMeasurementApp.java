public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // 🔹 LENGTH
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("Length Equal: " + l1.equals(l2));
        System.out.println("Length Add: " + l1.add(l2, LengthUnit.FEET));
        System.out.println("Length Convert: " + l1.convertTo(LengthUnit.INCHES));

        System.out.println("------------");

        // 🔹 WEIGHT
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equal: " + w1.equals(w2));
        System.out.println("Weight Add: " + w1.add(w2));
        System.out.println("Weight Convert: " + w1.convertTo(WeightUnit.POUND));

        System.out.println("------------");

        // 🔹 VOLUME (UC11)
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        System.out.println("Volume Equal: " + v1.equals(v2));
        System.out.println("Volume Add: " + v1.add(v2));
        System.out.println("Volume Convert: " + v1.convertTo(VolumeUnit.GALLON));

        System.out.println("------------");

        // 🔹 SUBTRACTION (UC12)
        System.out.println("Length Subtract: " + l1.subtract(l2));
        System.out.println("Weight Subtract: " + w1.subtract(w2));
        System.out.println("Volume Subtract: " + v1.subtract(v2));

        System.out.println("------------");

        // 🔹 DIVISION (UC12)
        System.out.println("Length Divide: " + l1.divide(l2));
        System.out.println("Weight Divide: " + w1.divide(w2));
        System.out.println("Volume Divide: " + v1.divide(v2));

        System.out.println("------------");

        // 🔹 CROSS CATEGORY CHECK
        System.out.println("Cross-category: " + ((Object) w1).equals(l1));
    }
}