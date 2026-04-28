import java.util.*;

public class QuantityMeasurementApp {

    // Enum with all units (base = FEET)
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084); // 1 cm ≈ 0.0328084 feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toBase(double value) {
            return value * toFeetFactor;
        }
    }

    // Generic Quantity class
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toBase(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }
    }

    // Main method (test cases)
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);
        Quantity q3 = new Quantity(36.0, LengthUnit.INCH);
        Quantity q4 = new Quantity(1.0, LengthUnit.CENTIMETER);
        Quantity q5 = new Quantity(0.393701, LengthUnit.INCH);

        System.out.println("1 yard vs 3 feet: " + q1.equals(q2)); // true
        System.out.println("1 yard vs 36 inch: " + q1.equals(q3)); // true
        System.out.println("2 yard vs 2 yard: " +
                new Quantity(2.0, LengthUnit.YARD).equals(new Quantity(2.0, LengthUnit.YARD))); // true

        System.out.println("1 cm vs 0.393701 inch: " + q4.equals(q5)); // true
        System.out.println("1 cm vs 1 ft: " + q4.equals(new Quantity(1.0, LengthUnit.FEET))); // false
    }
}