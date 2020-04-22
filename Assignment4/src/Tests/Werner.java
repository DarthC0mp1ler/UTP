package Tests;

public class Werner {

    public static void main(String[] args) {

        int n = 7;
        for (int i = n-1; i >=0; i-=2) {
            for (int k = (n-i)/2; k >0; k--) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

}
