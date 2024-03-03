import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Lab1 {
    private static List<List<Integer>> cicluri;
    private static List<Integer>[] vecini;
    private static int hubNode;

    public static void main(String args[]) {
        Lab1 lab1 = new Lab1();
        lab1.compulsory();
        lab1.homework(args);
        lab1.bonus(4);
    }

    void compulsory() {

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

    void homework(String[] args) {
        if (args.length != 3) {
            System.out.println("Trebuie dat inputul asa: java Lab1 <a> <b> <c>");
            return;
        }

        int a, b, k;

        try {
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[1]);
            k = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Input invalid. Introduceti valori pentru a, b si k");
            return;
        }

        if (a > b) {
            System.out.println("Input invalid. a trebuie sa fie mai mic decat b");
            return;
        }

        StringBuilder result = new StringBuilder();  //pentru stocarea numerelor k-reductibile

        long TimpStart = System.nanoTime();  //incepem sa masuram timpul

        for (int i = a; i <= b; i++) {
            if (isKReductible(i, k)) {
                result.append(i).append(" ");
            }
        }

        long TimpFinal = System.nanoTime();
        long durata = (TimpFinal - TimpStart) / 1_000_000;

        System.out.println("Numerele k-reductibile intre " + a + " si " + b + " cu k = " + k + " : " + result);
        System.out.println("Timpul de executie:" + durata + " milisecunde ");
    }

    private static boolean isKReductible(int numar, int k) {
        while (numar >= 10) {
            int suma = 0;
            while (numar > 0) {
                int c = numar % 10;
                suma += c * c;
                numar /= 10;
            }
            numar = suma;
        }
        return numar == k;
    }


    void bonus ( int n ) {
        //n = 4;
        int[][] matriceAdiacenta = matriceWheelGraph(n);
        afisareMatrice(matriceAdiacenta);
        List<List<Integer>> vecini = initVecini(matriceAdiacenta);
        List<List<Integer>> cicluri = new ArrayList<>();

        hubNode = gasireHubNode(n);

        for (int i = 1; i < n; i++) {
            gasireCicluriNod(i, new ArrayList<>(), new boolean[n], vecini, cicluri, matriceAdiacenta);
        }

        System.out.println("Ciclurile pe care le-am gasit");
        for (List<Integer> ciclu : cicluri) {
            //if(esteCicluComplet(ciclu, matriceAdiacenta)) {
                System.out.println("Ciclu" + ciclu);
        }
    }

    /*
    public static boolean esteCicluComplet(List<Integer> ciclu, int[][] matriceAdiacenta){
        int primulNod= ciclu.get(0);
        int ultimulNod=ciclu.get(ciclu.size()-1);
        return matriceAdiacenta[primulNod][ultimulNod] == 1;
    }
    */
    public static int[][] matriceWheelGraph(int n){
        int [][] matriceAdiacenta = new int[n][n];

        for(int i=1; i<n-1; i++){
            matriceAdiacenta[i][i+1] = 1;
            matriceAdiacenta[i+1][i] = 1;
        }

        matriceAdiacenta[1][n-1]=1;
        matriceAdiacenta[n-1][1]=1;

        for(int i=1; i<n; i++){
            matriceAdiacenta[0][i]=1;
            matriceAdiacenta[i][0]=1;
        }

        return matriceAdiacenta;
    }
    public static void afisareMatrice(int[][] matrice) {
        for (int[] linie : matrice) {
            for (int valoare : linie) {
                System.out.print(valoare + " ");
            }
            System.out.println();
        }
    }

    public static int gasireHubNode(int n){
        if(n%2==0){
            System.out.println("Graful nu are un nod central");
            return -1;
        }
        return n/2;
    }

    public static List<List<Integer>> initVecini(int[][] matriceAdiacenta){
        int n = matriceAdiacenta.length;
        List<List<Integer>> vecini = new ArrayList<>();
        for(int i=0; i<n; i++){
            List<Integer> noduriVecine= new ArrayList<>();
            for(int j=0; j<n; j++){
                if(matriceAdiacenta[i][j] == 1){
                    noduriVecine.add(j);
                }
            }
            vecini.add(noduriVecine);
        }
        return vecini;
    }
    public static void gasireCicluriNod(int nodCurent, List<Integer> cicluCurent, boolean[] vizitat, List<List<Integer>> vecini, List<List<Integer>> cicluri, int[][] matriceAdiacenta){
        //System.out.println("Nod curent: " + nodCurent);
        //System.out.println("Ciclu curent: " + cicluCurent);
        //System.out.println("Vizitat: " + Arrays.toString(vizitat));

        if(vizitat[nodCurent]){
            if(cicluCurent.contains(nodCurent)){
                int index = cicluCurent.indexOf(nodCurent);
                List<Integer> ciclu = cicluCurent.subList(index, cicluCurent.size());
                    cicluri.add(new ArrayList<>(cicluCurent));
            }
            return;
        }

        cicluCurent.add(nodCurent);
        vizitat[nodCurent] = true;

        for(int vecin:vecini.get(nodCurent)){
            gasireCicluriNod(vecin, new ArrayList<>(cicluCurent), Arrays.copyOf(vizitat, vizitat.length), vecini, cicluri, matriceAdiacenta);
        }

    }
}