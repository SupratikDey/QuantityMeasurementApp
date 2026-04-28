import java.util.*;

public class QuantityMeasurementApp {

    // Base unit = FEET
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // Quantity class
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // ✅ UC6: Instance addition (result in THIS unit)
        public Quantity add(Quantity other) {
            if (other == null) throw new IllegalArgumentException("Other quantity cannot be null");

            double sumFeet = this.toFeet() + other.toFeet();
            double resultValue = this.unit.fromFeet(sumFeet);

            return new Quantity(resultValue, this.unit);
        }

        // ✅ Static addition (explicit target unit)
        public static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
            if (q1 == null || q2 == null) {
                throw new IllegalArgumentException("Operands cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumFeet = q1.toFeet() + q2.toFeet();
            double resultValue = targetUnit.fromFeet(sumFeet);

            return new Quantity(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;
            return Math.abs(this.toFeet() - other.toFeet()) < 1e-6;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // Main method (demo)
    public static void main(String[] args) {

        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity i12 = new Quantity(12.0, LengthUnit.INCH);
        Quantity y1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity cm = new Quantity(2.54, LengthUnit.CENTIMETER);

        // Same unit
        System.out.println(f1.add(new Quantity(2.0, LengthUnit.FEET))); // 3 ft

        // Cross unit
        System.out.println(f1.add(i12)); // 2 ft
        System.out.println(i12.add(f1)); // 24 inch

        // Yard + feet
        System.out.println(y1.add(new Quantity(3.0, LengthUnit.FEET))); // 2 yard

        // cm + inch
        System.out.println(cm.add(new Quantity(1.0, LengthUnit.INCH))); // ~5.08 cm

        // Static method
        System.out.println(Quantity.add(f1, i12, LengthUnit.FEET)); // 2 ft
    }
}