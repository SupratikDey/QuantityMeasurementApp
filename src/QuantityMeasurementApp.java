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

        // 🔹 UC7: Private helper (DRY)
        private static Quantity addInternal(Quantity q1, Quantity q2, LengthUnit targetUnit) {
            double sumFeet = q1.toFeet() + q2.toFeet();
            double result = targetUnit.fromFeet(sumFeet);
            return new Quantity(result, targetUnit);
        }

        // ✅ UC6 (default: first operand unit)
        public Quantity add(Quantity other) {
            if (other == null) throw new IllegalArgumentException("Other cannot be null");
            return addInternal(this, other, this.unit);
        }

        // ✅ UC7 (explicit target unit)
        public Quantity add(Quantity other, LengthUnit targetUnit) {
            if (other == null) throw new IllegalArgumentException("Other cannot be null");
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

            return addInternal(this, other, targetUnit);
        }

        // ✅ Static UC7 API
        public static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
            if (q1 == null || q2 == null)
                throw new IllegalArgumentException("Operands cannot be null");
            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            return addInternal(q1, q2, targetUnit);
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

    // Demo
    public static void main(String[] args) {

        Quantity f1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity i12 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println(Quantity.add(f1, i12, LengthUnit.FEET));   // 2 ft
        System.out.println(Quantity.add(f1, i12, LengthUnit.INCH));   // 24 inch
        System.out.println(Quantity.add(f1, i12, LengthUnit.YARD));   // ~0.667 yard

        System.out.println(f1.add(i12, LengthUnit.YARD)); // instance version

        System.out.println(Quantity.add(
                new Quantity(36, LengthUnit.INCH),
                new Quantity(1, LengthUnit.YARD),
                LengthUnit.FEET)); // 6 ft
    }
}