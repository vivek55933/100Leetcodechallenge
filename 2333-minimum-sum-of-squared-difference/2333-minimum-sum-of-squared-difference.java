
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int n = nums1.length;
        int[] diff = new int[n];

        int maxDiff = 0;
        long total = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            total += diff[i];
        }

        // All differences can be reduced to zero.
        if (k >= total) {
            return 0;
        }

        int[] freq = new int[maxDiff + 1];

        for (int d : diff) {
            freq[d]++;
        }

        // Reduce the largest differences first.
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            int operations = (int) Math.min(k, (long) freq[d]);

            freq[d] -= operations;
            freq[d - 1] += operations;
            k -= operations;
        }

        // Calculate the sum of squared differences.
        long result = 0;

        for (int d = 0; d < freq.length; d++) {
            result += (long) d * d * freq[d];
        }

        return result;
    }
}
