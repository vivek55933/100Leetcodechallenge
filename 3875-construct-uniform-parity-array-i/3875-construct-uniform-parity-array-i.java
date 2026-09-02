class Solution {
    public boolean uniformArray(int[] nums1) {
        int odd = 0;
        int even = 0;

        for (int num : nums1) {
            if (num % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        // If all numbers already have the same parity
        if (odd == 0 || even == 0) {
            return true;
        }

        // Both parities exist, so we can make all elements have
        // the same parity using subtraction.
        return true;
    }
}