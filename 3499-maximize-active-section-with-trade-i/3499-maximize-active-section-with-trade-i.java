class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int baseOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') baseOnes++;
        }

        String t = "1" + s + "1";
        int m = t.length();

        java.util.ArrayList<Character> chars = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> lens = new java.util.ArrayList<>();

        int i = 0;
        while (i < m) {
            char c = t.charAt(i);
            int j = i;
            while (j < m && t.charAt(j) == c) j++;
            chars.add(c);
            lens.add(j - i);
            i = j;
        }

        int maxGain = 0;

        for (int k = 1; k < chars.size() - 1; k++) {
            if (chars.get(k) == '1'
                    && chars.get(k - 1) == '0'
                    && chars.get(k + 1) == '0') {

                int gain = lens.get(k - 1) + lens.get(k + 1);
                maxGain = Math.max(maxGain, gain);
            }
        }

        return baseOnes + maxGain;
    }
}