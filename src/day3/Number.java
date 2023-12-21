package day3;

import java.util.Objects;

public class Number {
    private int line;
    private int startIndex;
    private int endIndex;
    private  int value;

    public Number(int line, int startIndex, int endIndex) {
        this.line = line;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number = (Number) o;
        return line == number.line && startIndex == number.startIndex && endIndex == number.endIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, startIndex, endIndex);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setValue(String stringValue) {
        this.value = Integer.parseInt(stringValue);
    }
}
