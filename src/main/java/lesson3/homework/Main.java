package lesson3.homework;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Формат записи в файл был немного изменен, тк. не был указан формат файла
 */
public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final String selfDir = "src/main/java/lesson3/homework/";

    public static void main(String[] args) {
        User user = User.create();
        System.out.printf("Данные %sсохранены --> %s", user.save() ? "" : "не ", user);
    }

    private static class User {
        private final String lastName, firstName, middleName;
        private final String birthDate; // dd.mm.yyyy
        private final Long phoneNumber; // 900XXXYYZZ
        private final Character sex; // f/m

        public User(
                String lastName,
                String firstName,
                String middleName,
                String birthDate,
                Long phoneNumber,
                Character sex
        ) throws ExUser {
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleName = middleName;
            this.birthDate = birthDate;
            this.phoneNumber = phoneNumber;
            this.sex = sex;
            if (this.isEmptyData()) throw new ExUser("Данные заполнены некорректно");
        }

        public static User create() {
            User user = null;
            String[] sexValues = {"m", "f", "м", "ж"};
            String[] data;

            String lastName, firstName, middleName, birthDate;
            Long phoneNumber;
            Character sex;

            while (user == null) {
                try {
                    lastName = null;
                    firstName = null;
                    middleName = null;
                    birthDate = null;
                    phoneNumber = null;
                    sex = null;
                    data = User.getData(User.class.getDeclaredFields().length);
                    for (String item : data) {
                        if (User.isEmpty(item)) {
                            throw new ExUser("Введите все данные");
                        }
                        // birthDate
                        if (item.indexOf('.') != -1) {
                            if (
                                    item.length() != 10 ||
                                    (item.length() - item.replace(".", "").length()) != 2 // count "."
                            ) {
                                throw new ExUser("Дата рождения указана не корректно");
                            } else {
                                birthDate = item;
                                continue;
                            }
                        }
                        // sex
                        if (sex == null && item.length() == 1) {
                            if (!Arrays.asList(sexValues).contains(item)) {
                                throw new ExUser("Пол указан некорректно");
                            } else {
                                sex = item.toCharArray()[0];
                                continue;
                            }
                        }
                        // phoneNumber
                        if (phoneNumber == null) {
                            try {
                                if (String.valueOf(phoneNumber = Long.parseLong(item)).length() != 10) {
                                    phoneNumber = null;
                                    throw new ExUser("Номер телефона заполнен некорректно");
                                }
                                continue;
                            } catch (ExUser e) {
                                throw new ExUser(e.getMessage());
                            } catch (Throwable ignored) {}
                        }
                        // fullName
                        if (lastName == null) lastName = item;
                        else if (firstName == null) firstName = item;
                        else if (middleName == null) middleName = item;
                    }
                    user = new User(lastName, firstName, middleName, birthDate, phoneNumber, sex);
                } catch (Throwable e) {
                    System.out.printf("Error: %s\n", e.getMessage());
                }
            }
            return user;
        }
        private static boolean isEmpty(Object data) {
            return data == null || data == "" || data.equals("");
        }
        private static String[] getData(int countData) throws ExUser {
            System.out.print("Введите \"<фамилия> <имя> <отчество> <дата_рождения(ex: dd.mm.yyyy)> " +
                    "<номер_телефона(ex: 900XXXYYZZ)> <пол(ex: m/f)>\": ");
            String[] data = in.nextLine().split(" ");
            if (data.length != countData) {
                throw new ExUser(String.format("Введено %s данных", data.length < countData ? "меньше" : "больше"));
            }
            return data;
        }

        private boolean isEmptyData() {
            boolean isFull = true;
            for (Field field : User.class.getDeclaredFields()) {
                try {
                    if (field.get(this) == null) throw new RuntimeException();
                } catch (Exception e) {
                    isFull = false;
                    break;
                }
            }
            return !isFull;
        }
        public boolean save() {
            try (FileWriter f = new FileWriter(selfDir + this.lastName + ".txt", true)) {
                f.write(String.format("%s\n", this));
                f.flush();
                return true;
            } catch (IOException e) {
                System.out.printf("Error %s\n", e.getMessage());
                for (StackTraceElement trace : e.getStackTrace()) {
                    System.out.printf("\t%s\n", trace);
                }
            }
            return false;
        }
        public String toString() {
            return String.format(
                    "ФИО: %s %s %s, Дата рождения: %10s, Номер телефона: %10d, Пол: %1s;",
                    this.lastName,
                    this.firstName,
                    this.middleName,
                    this.birthDate,
                    this.phoneNumber,
                    this.sex
            );
        }

        private static class ExUser extends Exception {
            public ExUser(String message) {
                super(message);
            }
        }
    }
}