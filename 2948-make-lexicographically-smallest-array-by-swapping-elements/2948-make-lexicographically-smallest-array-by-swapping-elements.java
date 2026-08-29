import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Store {value, originalIndex}
        int[][] pairs = new int[n][2];

        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        // Sort by value
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = nums.clone();

        int start = 0;

        while (start < n) {
            int end = start;

            // Find one connected component.
            while (end + 1 < n &&
                   pairs[end + 1][0] - pairs[end][0] <= limit) {
                end++;
            }

            // Collect original indices of this component.
            List<Integer> indices = new ArrayList<>();

            for (int i = start; i <= end; i++) {
                indices.add(pairs[i][1]);
            }

            // Values are already sorted because pairs is sorted by value.
            // Sort indices so smallest values go to smallest positions.
            Collections.sort(indices);

            for (int i = 0; i < indices.size(); i++) {
                ans[indices.get(i)] = pairs[start + i][0];
            }

            start = end + 1;
        }

        return ans;
    }
}