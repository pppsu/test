import java.util.Scanner;

class PrimeFactor{
    public static void main(String args[]) {
        int number;
        Scanner scanner = new Scanner(System.in);
        System.out.println("0 >> Exit");
        do {
            System.out.print("Enter number: ");
            number = scanner.nextInt();
            int count = 0;
            for (int i = 2; i <= (number); i++) {
                while (number % i == 0) {
                    number /= i;
                    count++;
                    if (count == 1) {
                        System.out.print(" " + i);
                    } else {
                        System.out.print(" x " + i);
                    }
                }
            }System.out.println("");
            System.out.println("======================");
            
        } while (number != 0);
    }
}