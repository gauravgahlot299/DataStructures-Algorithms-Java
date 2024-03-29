import java.util.Stack;

public class LongestIncreasingSubsequence {
    static class Result {
        int[] dummy;
        int maxSoFar;

        public Result(int[] dummy, int maxSoFar) {
            this.dummy = dummy;
            this.maxSoFar = maxSoFar;
        }
    }

    public static void main(String args[]) {
        int a[] = {10, 22, 9, 33, 21, 50, 41, 60, 80};
//        int a[] = {1, 11, 2, 10, 4, 5, 2, 1};
        int b[] = printLIS(a);
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }
    }

    static int[] printLIS(int a[]) {
        Result res = getLIS(a);
        int dummy[] = res.dummy;
        int maxSoFar = res.maxSoFar;
        return getLISHelper(a, dummy, maxSoFar);
    }

    static Result getLIS(int a[]) {
        int dummy[] = new int[a.length];
        int maxSoFar = 1;
        for (int i = 0; i < dummy.length; i++) {
            dummy[i] = 1;
        }
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > a[i]) {
                    if (dummy[j] < dummy[i] + 1) {
                        dummy[j] = dummy[j] + 1;
                        maxSoFar = dummy[j] > maxSoFar ? dummy[j] : maxSoFar;
                    }
                }
            }
        }
        return new Result(dummy, maxSoFar);
    }

    static int[] getLISHelper(int a[], int dummy[], int maxSoFar) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = dummy.length - 1;
        int toPush = maxSoFar;
        while (i > 0) {
            while (i > 0 && dummy[i] != toPush) {
                i--;
            }
            if (i > 0) {
                stack.push(a[i]);
                toPush = toPush - 1;
                i--;
            } else break;
        }
        if (dummy[0] == toPush) stack.push(a[0]);
        int toReturn[] = new int[stack.size()];
        i = 0;
        while (!stack.empty()) {
            toReturn[i++] = stack.pop();
        }
        return toReturn;
    }
}
