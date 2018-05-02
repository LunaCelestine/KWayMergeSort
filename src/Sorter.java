import java.util.*;
import java.util.Arrays;
import java.util.Random;

public class Sorter {

    /**
     * Given an array of integers and an integer k, sort the array
     * (ascending order) using k-way mergesort.
     * @param data  an array of integers
     * @param k     the k in k-way mergesort
     */
    public static void kwayMergesort (int[] data, int k) {
        kwayMergesortRecursive (data, 0, data.length - 1, k);
    }

    /**
     * The recursive part of k-way mergesort.
     * Given an array of integers (data), a low index, high index, and an integer k,
     * sort the subarray data[low..high] (ascending order) using k-way mergesort.
     * @param data  an array of integers
     * @param low   low index
     * @param high  high index
     * @param k     the k in k-way mergesort
     */
    public static void kwayMergesortRecursive (int[] data, int low, int high, int k) {
        if (low < high) {
            for (int i = 0; i < k; i++) {
                kwayMergesortRecursive (data,
                        low + i*(high-low+1)/k,
                        low + (i+1)*(high-low+1)/k - 1,
                        k);
            }
            merge (data, low, high, k);
        }
    }


    /**
     * Given an array of integers (data), a low index, a high index, and an integer k,
     * sort the subarray data[low..high].  This method assumes that each of the
     * k subarrays  data[low + i*(high-low+1)/k .. low + (i+1)*(high-low+1)/k - 1],
     * for i = 0..k-1, are sorted.
     * @param data  an array of integers
     * @param low   low index
     * @param high  high index
     * @param k     the k in k-way mergesort
     */
    public static void merge (int[] data, int low, int high, int k) {

        if (high < low + k) {
            // the subarray has k or fewer elements
            // just make one big heap and do deleteMins on it
            Comparable[] subarray = new MergeSortHeapNode[high - low + 1];
            for (int i = 0, j = low; i < subarray.length; i++, j++) {
                subarray[i] = new MergeSortHeapNode(data[j], 0);
            }
            BinaryHeap heap = BinaryHeap.buildHeap(subarray);

            for (int j = low; j <= high; j++) {
                try {
                    data[j] = ((MergeSortHeapNode) heap.deleteMin()).getKey();
                }
                catch (EmptyHeapException e) {
                    System.out.println ("Tried to delete from an empty heap.");
                }
            }

        } else {
            // do a k-way merge on the k sorted subarrays

        }
    }


    public static int[] iterativeMergeSort(int[][] arr) {
        int K = arr.length;
        int N = arr[0].length;

        /** array to keep track of non considered positions in subarrays **/
        int[] curPos = new int[K];

        /** final merged array **/
        int[] mergedArray = new int[K * N];
        int p = 0;

        while (p < K * N) {
            int min = Integer.MAX_VALUE;
            int minPos = -1;
            /** search for least element **/
            for (int i = 0; i < K; i++) {
                if (curPos[i] < N) {
                    if (arr[i][curPos[i]] < min) {
                        min = arr[i][curPos[i]];
                        minPos = i;
                    }
                }
            }
            curPos[minPos]++;
            mergedArray[p++] = min;
        }
        return mergedArray;
    }
    public static void mergesort (int[] data) {
        mergesortRecursive (data, 0, data.length - 1);
    }

    public static void mergesortRecursive (int[] data, int low, int high) {
        if (low < high) {
            mergesortRecursive (data,
                    low,
                    low + (high-low)/2 );
            mergesortRecursive (data,
                    low + (high-low)/2 + 1,
                    high);
            merge (data, low, high);
        }
    }


    public static void merge (int[] data, int low, int high) {
        // create a new array for the sorted data
        int[] temp = new int[high - low + 1];

        int lowIndex = low;
        int highIndex = low + (high-low)/2 + 1;
        int midIndex = low + (high-low)/2;
        int tempIndex = 0;

        while ((lowIndex <= midIndex) &&
                (highIndex <= high)) {
            if (data[lowIndex] < data[highIndex]) {
                temp[tempIndex] = data[lowIndex];
                tempIndex++;
                lowIndex++;
            } else {
                temp[tempIndex] = data[highIndex];
                tempIndex++;
                highIndex++;
            }
        }

        if (lowIndex > midIndex) {
            // low subarray finished first
            while (highIndex <= high) {
                temp[tempIndex] = data[highIndex];
                tempIndex++;
                highIndex++;
            }

        } else {
            // high subarray finished first
            while (lowIndex <= midIndex) {
                temp[tempIndex] = data[lowIndex];
                tempIndex++;
                lowIndex++;
            }
        }

        // copy data back from temp to data
        int dataIndex;
        for (tempIndex = 0, dataIndex = low;
             dataIndex <= high;
             tempIndex++, dataIndex++) {

            data[dataIndex] = temp[tempIndex];
        }

    }

    /**
     * Given an integer size, produce an array of size random integers.
     * The integers of the array are between 0 and size (inclusive) with
     * random uniform distribution.
     * @param size  the number of elements in the returned array
     * @return      an array of integers
     */
    public static int[] getRandomArrayOfIntegers(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = (int) ((size + 1) * Math.random());
        }
        return data;
    }


    /**
     * Given an integer size, produce an array of size random integers.
     * The integers of the output array are between 0 and size-1 with
     * exactly one of each in the array.  Each permutation is generated
     * with random uniform distribution.
     * @param size  the number of elements in the returned array
     * @return      an array of integers
     */
    public static int[] getRandomPermutationOfIntegers(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
        // shuffle the array
        for (int i = 0; i < size; i++) {
            int temp;
            int swap = i + (int) ((size - i) * Math.random());
            temp = data[i];
            data[i] = data[swap];
            data[swap] = temp;
        }
        return data;
    }


    /**
     * Perform checks to see if the algorithm has a bug.
     */
    public static void testCorrectness(int size) {
        int[] data = getRandomPermutationOfIntegers(size);

        for (int i = 0; i < data.length; i++) {
            System.out.println("data[" + i + "] = " + data[i]);
        }

        int k = 5;
        // mergesort(data);
        kwayMergesort(data, k);


        // verify that data[i] = i
        for (int i = 0; i < data.length; i++) {
            if (data[i] != i) {
                System.out.println ("Error!  data[" + i + "] = " + data[i] + ".");
            }
        }
        for (int i = 0; i < data.length; i++) {

            System.out.println ("  data[" + i + "] = " + data[i] + ".");

        }
    }


    /**
     * Perform timing experiments.
     */
    public static void testTiming (int N, int kvalue ) {



        int n = N;    // n = size of the array
        int k = kvalue;         // k = k in k-way mergesort
        int[] data = getRandomArrayOfIntegers(n);


        //insertionSort(data);
        //mergesort(data);


        int iterativeMergeData[][] = new int[kvalue][n];
        Random rand = new Random();
        for (int i = 0; i < kvalue; i++)
            for (int j = 0; j < N; j++)
                iterativeMergeData[i][j] = rand.nextInt();


        //iterativeMergeSort(iterativeMergeData);



        //kwayMergesort(data, k);

        long startTime2 = System.nanoTime();
        Arrays.sort(data);
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);

//        System.out.println("** Results for k-way recursive mergesort:");
//        System.out.println("** Results for iterative mergesort:");
//        System.out.println("** Results for sort:");
//        System.out.println("** Results for mergesort:");
        System.out.println(duration2/1000000 + "ms");
    }

}
