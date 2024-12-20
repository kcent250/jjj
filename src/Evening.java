import java.io.*;
public class Evening {
    public static void main(String args[]) {
        int a[] = {2, 7, 9, 12};
        try {
            System.out.println("Access element five: " + a[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception thrown: " + e);
        } finally {
            System.out.println(a[3]);
        }
    }
}
