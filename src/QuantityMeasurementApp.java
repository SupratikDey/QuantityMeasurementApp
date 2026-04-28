import java.util.*;

public class QuantityMeasurementApp {

    // Feet class
    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Inches class
    static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Static method for Feet equality
    public static boolean checkFeetEquality(double a, double b) {
        Feet f1 = new Feet(a);
        Feet f2 = new Feet(b);
        return f1.equals(f2);
    }

    // Static method for Inches equality
    public static boolean checkInchesEquality(double a, double b) {
        Inches i1 = new Inches(a);
        Inches i2 = new Inches(b);
        return i1.equals(i2);
    }

    // Main method
    public static void main(String[] args) {

        // Feet comparison
        System.out.println("1.0 ft vs 1.0 ft: " +
                checkFeetEquality(1.0, 1.0)); // true

        // Inches comparison
        System.out.println("1.0 inch vs 1.0 inch: " +
                checkInchesEquality(1.0, 1.0)); // true

        // Different values
        System.out.println("1.0 inch vs 2.0 inch: " +
                checkInchesEquality(1.0, 2.0)); // false
    }
}