class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rows = new HashMap<>();

        // Mark reserved seats for each affected row.
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Use bit (col - 1) for this seat.
            rows.put(row, rows.getOrDefault(row, 0) | (1 << (col - 1)));
        }

        int ans = (n - rows.size()) * 2;

        // Masks for:
        // left  = seats 2,3,4,5
        // middle = seats 4,5,6,7
        // right = seats 6,7,8,9
        int left = (1 << 1) | (1 << 2) | (1 << 3) | (1 << 4);
        int middle = (1 << 3) | (1 << 4) | (1 << 5) | (1 << 6);
        int right = (1 << 5) | (1 << 6) | (1 << 7) | (1 << 8);

        for (int mask : rows.values()) {
            boolean canLeft = (mask & left) == 0;
            boolean canMiddle = (mask & middle) == 0;
            boolean canRight = (mask & right) == 0;

            if (canLeft && canRight) {
                // We can place two groups:
                // one in [2,3,4,5] and one in [6,7,8,9].
                ans += 2;
            } else if (canLeft || canMiddle || canRight) {
                // At least one block is available.
                ans += 1;
            }
        }

        return ans;
    }
}