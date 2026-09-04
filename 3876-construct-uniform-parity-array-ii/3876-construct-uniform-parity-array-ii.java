class Solution {
    public boolean uniformArray(int[] nums1) {
        boolean hasOdd = false, hasEven = false;
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;

        for (int x : nums1) {
            if ((x & 1) == 1) {
                hasOdd = true;
                minOdd = Math.min(minOdd, x);
            } else {
                hasEven = true;
                minEven = Math.min(minEven, x);
            }
        }

        // Already uniform.
        if (!hasOdd || !hasEven) {
            return true;
        }

        // Make everything odd:
        // Every even number must subtract a smaller odd number.
        // The smallest odd number works for all evens iff minOdd < minEven.
        return minOdd < minEven;
    }
}