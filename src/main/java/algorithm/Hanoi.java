package algorithm;

/**
 * @author: 李昭
 * @Date: 3/24/2020 8:59 PM
 */
public class Hanoi {

    public static void main(String[] args) {
        int nDisks = 3;
        hanoi(nDisks, 'A', 'B', 'C');
    }

    public static void hanoi(int top, char from, char inter, char to) {
        if (top == 1) {
            System.out.println("Disk 1 from " + from + " to " + to);
        } else {
            hanoi(top - 1, from, to, inter);
            System.out.println("Disk " + top + " from " + from + " to " + to);
            hanoi(top - 1, inter, from, to);
        }
    }

}
