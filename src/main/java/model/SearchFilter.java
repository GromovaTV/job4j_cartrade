package model;

import java.util.Arrays;

public class SearchFilter {
    String[] brands = new String[2];
    String[] bodies = new String[2];
    String min;
    String max;

    public SearchFilter() {
    }

    public String[] getBrands() {
        return brands;
    }

    public void setBrands(String[] brands) {
        this.brands = brands;
    }

    public String[] getBodies() {
        return bodies;
    }

    public void setBodies(String[] bodies) {
        this.bodies = bodies;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "SearchFilter{"
                + "brands=" + Arrays.toString(brands)
                + ", bodies=" + Arrays.toString(bodies)
                + ", min=" + min
                + ", max=" + max
                + '}';
    }
}
