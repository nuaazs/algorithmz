package class01;

import java.util.Arrays;

public class InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
                swap(arr, j, minIndex);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int size = (int) Math.random() * (maxSize + 1);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) Math.random() * (maxValue + 1) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] comparator(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        int size = arr.length;
        int[] arr2 = new int[size];
        for (int i = 0; i < size; i++) {
            arr2[i] = arr[i];
        }
        return arr2;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }

        if (arr1 == null && arr2 == null) {
            return true;
        }

        if (arr1.length != arr2.length) {
            return false;
        }

        int size = arr1.length;
        for (int i = 0; i < size; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 1000;
        int maxValue = 200;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);

            insertionSort(arr1);
            arr2 = comparator(arr1);
            if (!isEqual(arr1, arr2)) {
                System.out.print("Oops");
                break;
            }

        }
        System.out.println("okay");
    }
}
