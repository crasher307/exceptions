package lesson1.homework;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // test_1_1();
        // test_1_2();
        // test_1_3();

        int[] test1 = {10, 20, 30};
        int[] test2 = {0, 30, 40};
        System.out.println(Arrays.toString(test_4(test1, test2)));
    }

    /**
     * 1. Реализуйте 3 метода, чтобы в каждом из них получить разные исключения.
     */
    public static void test_1_1() {
        int[] arr = new int[4];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
    }
    public static void test_1_2() {
        int response = Integer.parseInt("Test" + 10);
    }
    public static void test_1_3() {
        int response = 10 / 0;
    }

    /**
     * 2. Посмотрите на код, и подумайте сколько разных типов исключений вы тут сможете получить?
     * Возможных исключений 3:
     *  - java.lang.ArrayIndexOutOfBoundsException
     *  - java.lang.NumberFormatException
     *  - java.lang.NullPointerException
     */
    public static int test_2(String[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int k = 0; k < 5; k++) {
                int val = Integer.parseInt(arr[i][k]);
                sum += val;
            }
        }
        return sum;
    }

    /**
     * 3. Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый
     * массив, каждый элемент которого равен разности элементов двух входящих массивов в той же ячейке.
     * Если длины массивов не равны, необходимо как-то оповестить пользователя.
     */
    public static int[] test_3(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) throw new RuntimeException("Длины массивов не равны");
        int[] response = new int[arr1.length];
        for (int i = 0; i < response.length; i++) response[i] = arr1[i] - arr2[i];
        return response;
    }

    /**
     * 4.* Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий
     * новый массив, каждый элемент которого равен частному элементов двух входящих массивов в той же
     * ячейке. Если длины массивов не равны, необходимо как-то оповестить пользователя. Важно: При
     * выполнении метода единственное исключение, которое пользователь может увидеть - RuntimeException,
     * т.е. ваше.
     */
    public static int[] test_4(int[] arr1, int[] arr2) {
        int[] response;
        try {
            if (arr1.length != arr2.length) throw new RuntimeException("Длины массивов не равны");
            response = new int[arr1.length];
            for (int i = 0; i < response.length; i++) response[i] = arr1[i] / arr2[i];
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }
}
