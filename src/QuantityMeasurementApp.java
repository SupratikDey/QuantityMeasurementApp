import java.util.*;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        // Conversion
        System.out.println(q1.convertTo(LengthUnit.INCH)); // 12 inches

        // Equality
        System.out.println(q1.equals(q2)); // true

        // Addition (UC6)
        System.out.println(q1.add(q2)); // 2 feet

        // Addition with target (UC7)
        System.out.println(q1.add(q2, LengthUnit.YARD)); // ~0.667 yard

        // Direct enum usage (UC8 feature)
        System.out.println(LengthUnit.INCH.convertToBaseUnit(12)); // 1 foot
    }
}

