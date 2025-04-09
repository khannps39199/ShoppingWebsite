package poly.edu.Entity;

public class app {
    public boolean isEvenNum(int input) {
        if (input % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public double getVatOnAmount(double input) {
        return input * 0.1;
    }

}
