class Solution {
    public int reverseDegree(String s) {
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            // 'a' -> 26, 'b' -> 25, ..., 'z' -> 1
            int reversedValue = 'z' - s.charAt(i) + 1;

            // Position in string is i + 1
            sum += reversedValue * (i + 1);
        }

        return sum;
    }
}
