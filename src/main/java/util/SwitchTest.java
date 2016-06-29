package util;

/**
 * Created by seven on 29/06/16.
 */
public class SwitchTest {
    public static void test(int i) {
        switch (i) {
            case 0:
                System.out.println("0");
            case 1:
                System.out.println("1");
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
        }
    }

    public static void main(String[] args) {
        for (int i : new int[]{
                -1, 0, 1, 2, 3, 4, 5
        }) {
            System.out.println("##################################");
            test(i);
        }

    }
}
