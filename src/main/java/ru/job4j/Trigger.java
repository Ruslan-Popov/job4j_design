package ru.job4j;

public class Trigger {
    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int a = -51;
        System.out.println(binary(a));
        int b = a >>> 2;
//        int c = a & b;
        System.out.println(binary(b));
        System.out.println("a = " + a);
        System.out.println("b = " + b);
//        System.out.println("с = " + c);
    }
}