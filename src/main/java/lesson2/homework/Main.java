package lesson2.homework;

import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws ExNull {
        // System.out.println(getFloat());
        // test();
        // test2();
        System.out.println(test3());
    }

    /**
     * 1. Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float),
     * и возвращает введенное значение. Ввод текста вместо числа не должно приводить к падению
     * приложения, вместо этого, необходимо повторно запросить у пользователя ввод данных.
     */
    public static float getFloat() {
        Float number = null;
        while (number == null) {
            System.out.print("Введите дробное число: ");
            try {
                number = Float.parseFloat(in.next());
            } catch (Exception e) {
                System.out.printf("Error: %s\nПопробуйте снова\n", e.getMessage());
            }
        }
        return number;
    }

    /**
     * 2. Если необходимо, исправьте данный код
     * - не был задан массив intArray
     * - исключение ArithmeticException заменено на Throwable
     */
    public static void test() {
        try {
            int[] intArray = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int d = 0;
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (Throwable e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * 2.1. Дан следующий код, исправьте его там, где требуется
     * - обработка Throwable перемещена в конец
     * - из ф-ции printSum удалено исключение FileNotFoundException
     */
    public static void test2() {
        try {
            int a = 90;
            int b = 3;
            System.out.println(a / b);
            printSum(23, 234);
            int[] abc = { 1, 2 };
            abc[3] = 9;
        } catch (NullPointerException ex) {
            System.out.println("Указатель не может указывать на null!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Массив выходит за пределы своего размера!");
        } catch (Throwable ex) {
            System.out.println("Что-то пошло не так...");
        }
    }
    public static void printSum(Integer a, Integer b) {
        System.out.println(a + b);
    }

    /**
     * 2.2. Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку.
     * Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
     */
    public static String test3() throws ExNull {
        System.out.print("Введите что-нибудь: ");
        String read = in.nextLine();
        if (read.equals("")) throw new ExNull();
        return read;
    }
    private static class ExNull extends Exception {
        public ExNull() {
            super("Нельзя вводить пустую строку");
        }
    }
}
