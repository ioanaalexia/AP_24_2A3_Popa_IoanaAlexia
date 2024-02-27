//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //1.
        System.out.println("Hello World!");

        //2.
        System.out.println();
        String[] languages = {"C", "C++", "C#", "Phyton", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        for (int i = 0; i < languages.length; i++) {
            System.out.print(languages[i] + " ");
        }

        System.out.println("\n");
        //3.

        int n = (int) (Math.random() * 1_000_000);
        System.out.println(n + "\n");

        //4.
        n *= 3;
        n += Integer.parseInt("10101", 2);
        n += Integer.parseInt("FF", 16);
        n *= 6;

        System.out.println(n + "\n");

        //6.
        int suma = 0;
        int result = 0;
        do {
            while (n != 0) {
                suma = suma + n % 10;
                n = n / 10;
            }
            n = suma;
            result = suma;
            suma = 0;
        } while (result >= 10);

        System.out.println(result);

        //5.
        System.out.println("\nWilly-nilly, this semester I will learn " + languages[result]);
    }
}