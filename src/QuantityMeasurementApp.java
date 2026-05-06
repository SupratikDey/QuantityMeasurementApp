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
        System.out.println("------------");

// 🔹 VOLUME (UC11)
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

// Equality
        System.out.println("Volume Equal (L vs mL): " + v1.equals(v2));
        System.out.println("Volume Equal (L vs Gallon): " + v1.equals(v3));

// Conversion
        System.out.println("Convert L → mL: " + v1.convertTo(VolumeUnit.MILLILITRE));
        System.out.println("Convert Gallon → L: " + v3.convertTo(VolumeUnit.LITRE));

// Addition
        System.out.println("Add (L + mL): " + v1.add(v2));
        System.out.println("Add (L + Gallon → mL): " + v1.add(v3, VolumeUnit.MILLILITRE));
    }
}