
import java.util.Random;

/**
 * Objective - To determine if an array of integers in a certain range contains
 * duplicate elements.
 *
 * @author Isaias Leos Ayala
 */
public class Lab2 {

    /**
     * Array generated with random numbers
     *
     * @param size size of the array
     * @param range the range from 0 to m - 1
     * @return generated array
     */
    public static int[] randomArray(int size, int range)//O(N)
    {
        int[] generatedArray = new int[size];//generate array
        Random random = new Random();//random object to generate random numbers
        for (int i = 0; i < generatedArray.length; i++) {
            generatedArray[i] = random.nextInt(range);
        }
        return generatedArray;
    }

    /**
     * Checks if there is a duplicate number in the array.
     *
     * @param randomIntArray generated array with random numbers at the range of
     * m - 1
     * @return
     */
    public static boolean hasDuplicates(int[] randomIntArray)//O(N^2)
    {
        boolean duplicates = false;
        for (int i = 0; i < randomIntArray.length; i++) {
            for (int j = 0; j < randomIntArray.length; j++) {
                if (randomIntArray[i] == randomIntArray[j] && i != j) {
                    duplicates = true;
                }
            }
        }
        return duplicates;
    }

    /**
     * Improved method that checks if there is a duplicate number in the array.
     *
     * @param randomIntArray generated array with random numbers at the range of
     * m - 1
     * @return
     */
    public static boolean hasDuplicatesImproved(int[] randomIntArray)//O(N^2)
    {
        for (int i = 0; i < randomIntArray.length; i++) {
            for (int j = i + 1; j < randomIntArray.length; j++) {//i + 1 so it doesn't check itself or the previous index
                if (randomIntArray[i] == randomIntArray[j]) {//remove && i != j
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * A sorting algorithm that sorts an array by repeatedly finding the minimum
     * element from unsorted part and placing it at the beginning.
     *
     * @param randomIntArray generated array with random numbers at the range of
     * m - 1
     */
    public static void selectionSort(int[] randomIntArray) {//O(N^2)
        for (int i = 0; i < randomIntArray.length; i++) {//Move the the unsorted array
            int minimum = i;//Find the minimum element
            for (int j = i + 1; j < randomIntArray.length; j++) {
                if (randomIntArray[j] < randomIntArray[minimum]) {
                    minimum = j;
                }
            }
            int temp = randomIntArray[minimum];//Swap the minimum element with the first
            randomIntArray[minimum] = randomIntArray[i];
            randomIntArray[i] = temp;
        }
    }

    /**
     * QuickSort is a Divide and Conquer algorithm. It picks an element as pivot
     * and partitions the given array around the picked pivot.
     *
     * @param array generated array with random numbers at the range of m - 1
     * @param lowIndex first element of the array
     * @param highIndex second element of the array
     */
    public static void quickSort(int[] array, int lowIndex, int highIndex)//O(N^2)
    {
        int lowPivot = lowIndex;
        int highPivot = highIndex;
        int pivot = array[lowIndex + (highIndex - lowIndex) / 2];//Get the pivot number | Pivot is the middle index
        while (lowPivot <= highPivot) {//Divide into two arrays
            while (array[lowPivot] < pivot) {
                lowPivot++;
            }
            while (array[highPivot] > pivot) {
                highPivot--;
            }
            if (lowPivot <= highPivot) {//If current element is smaller than the pivot
                int temp = array[lowPivot];
                array[lowPivot] = array[highPivot];
                array[highPivot] = temp;
                //move index to next position on both sides
                lowPivot++;
                highPivot--;
            }
        }
        if (lowIndex < highPivot) {
            quickSort(array, lowIndex, highPivot);
            quickSort(array, lowPivot, highIndex);
        }
    }

    /**
     * Checks if the sorted array has any duplicate values.
     *
     * @param A array with randomly generated integers that have been sorted
     * @return
     */
    public static boolean hasDuplicatesSinglePass(int[] A) {//O(N)
        for (int i = 0; i < A.length - 1; i++) {//check the array
            if (A[i] == A[i + 1]) {//compare current to next variable
                return true;//if duplicates are present
            }
        }
        return false;//if duplicates aren't present
    }

    /**
     * Uses a boolean array to check if the provided array has any duplicate
     * numbers.
     *
     * @param A generated array with random numbers at the range of m - 1
     * @param m the range from 0 to m - 1
     * @return
     */
    public static boolean hasDuplicatedBooleanArray(int[] A, int m) {
        boolean[] isNumberPresent = new boolean[m];//keep track of what number are present
        for (int i = 0; i < A.length; i++) {//single pass
            if (isNumberPresent[A[i]] == true) {//to check if the number is already present
                return true;//if it is, end and return true.
            } else {
                isNumberPresent[A[i]] = true;//sets the index to true if not a duplicate
            }
        }
        return false;//no duplicates
    }

    public static void main(String[] args) {
        int range = 9;
        int count = 10;

        int[] originalArray = randomArray(count, range);//random generated
        System.out.println("Unimproved Duplicate Check: " + hasDuplicates(originalArray));//Unimproved Duplicate Pass
        System.out.println("Improved Duplicate Check: " + hasDuplicatesImproved(originalArray));//Improved Duplicate Pass

        int[] selectionSortArray = originalArray;//new array for selection sort
        selectionSort(selectionSortArray);//Selection Sort

        System.out.print("Selection Sort: ");
        for (int i = 0; i < selectionSortArray.length; i++) {//visual proof that its working
            System.out.print(selectionSortArray[i] + " ");
        }
        System.out.println("");
        System.out.println("Single Pass Duplicate Check: " + hasDuplicatesSinglePass(selectionSortArray));//Check the if the array has duplicates

        int[] quickSortArray = originalArray;//new array for quicksort
        quickSort(quickSortArray, 0, quickSortArray.length - 1);//Quicksort
        System.out.print("Quick Sort: ");
        for (int i = 0; i < quickSortArray.length; i++) {//visual proof that its working
            System.out.print(quickSortArray[i] + " ");
        }
        System.out.println("");
        System.out.println("Single Pass Duplicate Check: " + hasDuplicatesSinglePass(quickSortArray));//Check the if the array has duplicates

        int[] booleanArray = originalArray;//new array
        System.out.println("Boolean Array Duplicate Check: " + hasDuplicatedBooleanArray(booleanArray, range));//Check the if the array has duplicates /w boolean array

    }

}
