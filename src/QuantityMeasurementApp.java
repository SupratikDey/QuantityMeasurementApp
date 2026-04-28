import java.util.*;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // 🔹 LENGTH (UC8)
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println("Length Equal: " + l1.equals(l2));
        System.out.println("Length Add: " + l1.add(l2, LengthUnit.FEET));
        System.out.println("Length Convert: " + l1.convertTo(LengthUnit.INCHES));

        System.out.println("------------");

        // 🔹 WEIGHT (UC9)
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equal: " + w1.equals(w2));

        QuantityWeight sum = w1.add(w2);
        System.out.println("Weight Add: " + sum);

        QuantityWeight converted = w1.convertTo(WeightUnit.POUND);
        System.out.println("Weight Convert: " + converted);

        // Cross-category check
        System.out.println("Weight vs Length: " + w1.equals(l1)); // false
    }
}