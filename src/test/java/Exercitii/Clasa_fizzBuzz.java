package Exercitii;

public class Clasa_fizzBuzz {
    public static void fizzBuzz(int n) {
// first condition multiple of 3 && 5, cond second 3 && !5, etc.
        for (int i = 1; i <= n; ++i) {
            if (i % 3 == 0 && i % 5 == 0)
                System.out.println("FizzBuzz\n");
            else if (i % 3 == 0)
                System.out.println("Fizz\n");
            else if (i % 5 == 0)
                System.out.println("Buzz\n");
            else
                System.out.println("%d\n");
        }

    }


}
