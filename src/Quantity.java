public class Quantity {

    private final double value;
    private final LengthUnit unit;

    public Quantity(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    // Convert to base (delegation)
    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    // ✅ Conversion (UC5)
    public Quantity convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = this.toBase();
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity(converted, targetUnit);
    }

    // 🔹 Private helper (UC7 DRY)
    private static Quantity addInternal(Quantity q1, Quantity q2, LengthUnit targetUnit) {
        double sumBase = q1.toBase() + q2.toBase();
        double result = targetUnit.convertFromBaseUnit(sumBase);
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

    // ✅ Static API
    public static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
        if (q1 == null || q2 == null)
            throw new IllegalArgumentException("Operands cannot be null");
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        return addInternal(q1, q2, targetUnit);
    }

    // ✅ Equality (UC1–UC3)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity other = (Quantity) obj;
        return Math.abs(this.toBase() - other.toBase()) < 1e-6;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}