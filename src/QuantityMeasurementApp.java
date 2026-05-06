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

        // ❌ This WON'T COMPILE (GOOD THING)
        // w1.equals(l1);

        // ✅ If forced (unsafe)
        System.out.println("Cross-category: " + ((Object) w1).equals(l1));
    }
}