class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];

        // Count frequency of each digit
        for (int d : digits) {
            count[d]++;
        }

        int ans = 0;

        // Only even 3-digit numbers
        for (int num = 100; num <= 998; num += 2) {
            int a = num / 100;
            int b = (num / 10) % 10;
            int c = num % 10;

            int[] used = new int[10];
            used[a]++;
            used[b]++;
            used[c]++;

            boolean valid = true;

            for (int d = 0; d < 10; d++) {
                if (used[d] > count[d]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                ans++;
            }
        }

        return ans;
    }
}