package ru.academits.eliseev.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range otherInterval) {
        double maxFrom = Math.max(this.from, otherInterval.from);
        double minTo = Math.min(this.to, otherInterval.to);

        if (maxFrom >= minTo) {
            return null;
        }

        return new Range(maxFrom, minTo);
    }

    public Range[] getUnion(Range otherInterval) {
        if (this.to < otherInterval.from || otherInterval.to < this.from) {
            return new Range[]{new Range(this.from, this.to), new Range(otherInterval.from, otherInterval.to)};
        }

        double minFrom = Math.min(this.from, otherInterval.from);
        double maxTo = Math.max(this.to, otherInterval.to);

        return new Range[]{new Range(minFrom, maxTo)};
    }

    public Range[] getDifference(Range otherInterval) {
        if (otherInterval.to <= this.from || otherInterval.from >= this.to) {
            return new Range[]{new Range(this.from, this.to)};
        }

        if (otherInterval.from <= this.from && otherInterval.to >= this.to) {
            return new Range[]{};
        }

        if (otherInterval.from > this.from && otherInterval.to < this.to) {
            return new Range[]{new Range(this.from, otherInterval.from), new Range(otherInterval.to, this.to)};
        }

        if (otherInterval.from <= this.from) {
            return new Range[]{new Range(otherInterval.to, this.to)};
        }

        return new Range[]{new Range(this.from, otherInterval.from)};
    }

    public String toString() {
        return "[" + from + ", " + to + "]";
    }
}
