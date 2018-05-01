import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("K Way Merge Test\n");

        /** Accept k and n **/
        System.out.println("Enter K and N");
        int K = scan.nextInt();
        int N = scan.nextInt();

        int[][] arr = new int[K][N];
        /** Accept all elements **/
        System.out.println("Enter " + K + " sorted arrays of length " + N);

        for (int i = 0; i < K; i++)
            for (int j = 0; j < N; j++)
                arr[i][j] = scan.nextInt();

        Sorter kwm = new Sorter();

        int[] mergedArray = kwm.iterativeMergeSort(arr);
        /** Print merged array **/
        System.out.println("\nMerged Array : ");
        for (int i = 0; i < mergedArray.length; i++)
            System.out.print(mergedArray[i] + " ");
        System.out.println();
    }

}
