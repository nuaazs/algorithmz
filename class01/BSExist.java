package class01;

import java.util.Arrays;
// 二分查找一个数是否存在

public class BSExist {
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return sortedArr[left] == num;
    }

    // 暴力搜索
    public static boolean test(int[] sortedArr, int num) {
        if (sortedArr == null) {
            return false;
        }
        for (int i = 0; i < sortedArr.length; i++) {
            if (sortedArr[i] == num) {
                return true;
            }
        }
        return false;
    }

    // 生成随机数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 100;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[] sortedArr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(sortedArr);
            int num = (int) (Math.random() * (maxValue));
            if (test(sortedArr, num) != exist(sortedArr, num)) {
                System.out.println("Oops");
                System.out.println(num);
                // System.out.println(sortedArr[0]);
                for (int cur : sortedArr) {
                    System.out.print(cur + " ");
                }
                System.out.println();
                System.out.println(test(sortedArr, num));
                System.out.println(exist(sortedArr, num));
                break;
            }
        }
        System.out.println("Ok");
    }

}
