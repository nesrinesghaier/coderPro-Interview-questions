import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author nesrinesghaier
 */

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TwoNumbersLinkedLists solver = new TwoNumbersLinkedLists();
        solver.solve(1, in, out);
        out.close();
    }

    static class TwoNumbersLinkedLists {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            ListNode ans = null;
            ListNode list1 = null, list2 = null;
            // read the two linked-lists values from standard input
            int l1 = in.nextInt();
            if (l1 != 0) {
                ListNode head1 = new ListNode(in.nextInt());
                list1 = head1;
                for (int i = 0; i < l1 - 1; i++) {
                    int n = in.nextInt();
                    head1.next = new ListNode(n);
                    head1 = head1.next;
                }
            }

            int l2 = in.nextInt();
            if (l2 != 0) {
                ListNode head2 = new ListNode(in.nextInt());
                list2 = head2;
                for (int i = 0; i < l2 - 1; i++) {
                    head2.next = new ListNode(in.nextInt());
                    head2 = head2.next;
                }
            }
            // check if one of them is empty to avoid exception
            if (l1 == 0) {
                ans = list2;
            } else if (l2 == 0) {
                ans = list1;
            } else {
                // else sum their digits into another linked list
                ans = addTwoNumbers(list1, list2, out);
            }
            while (ans != null) {
                out.print(ans);
                ans = ans.next;
            }
        }

        public ListNode addTwoNumbers(ListNode list1, ListNode list2, PrintWriter out) {
            int l1 = 0;
            int l2 = 0;
            // preserve head pointers for later use
            ListNode head1 = list1;
            ListNode head2 = list2;

            // calculate both lists length to know the longest one
            while (head1 != null) {
                l1++;
                head1 = head1.next;
            }

            while (head2 != null) {
                l2++;
                head2 = head2.next;
            }
            ListNode result = null;
            ListNode head = null;
            head1 = list1;
            head2 = list2;
            // never forget the reminder of each summation
            int r = 0;
            // run through both of them till reaching one of the ends
            while (head1 != null && head2 != null) {

                if (head == null) {
                    r = (head1.value + head2.value) / 10;
                    head = new ListNode((head1.value + head2.value) % 10);
                    result = head;
                }
                head1 = head1.next;
                head2 = head2.next;
                if (head1 != null && head2 != null) {
                    head.next = new ListNode((head1.value + head2.value + r) % 10);
                    r = (head1.value + head2.value + r) / 10;
                    head = head.next;
                }
            }
            // add the rest of the longest one of them to the list result
            if (l1 < l2) {
                head1 = head2;
            }
            while (head1 != null) {
                head.next = new ListNode((head1.value + r) % 10);
                r = (head1.value + r) / 10;
                head1 = head1.next;
                head = head.next;
            }
            if (r != 0) {
                head.next = new ListNode(r);
            }
            return result;
        }

        class ListNode {
            int value;
            ListNode next;

            public ListNode(int value) {
                this.value = value;
            }

            public String toString() {
                if (next == null)
                    return value + "";
                return value + " -> ";
            }

        }

    }
}


