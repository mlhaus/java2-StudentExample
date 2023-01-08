package lambda;

public class MyNumberPredicates <T extends Number> {
    public static <T extends Number> boolean isPrime(T n) {
        if(n.intValue() < 2) return false;
        for(int i = 2; i <= n.intValue() / i; i++) {
            if((n.intValue() % i) == 0) {
                return false;
            }
        }
        return true;
    }

    public static <T extends Number> boolean isEven(T n) {
        return (n.intValue() % 2) == 0;
    }

    public static <T extends Number> boolean isPositive(T n) {
        return n.intValue() > 0;
    }

    public boolean isFactor(T n, T d) {
        return (n.intValue() % d.intValue()) == 0;
    }
}
