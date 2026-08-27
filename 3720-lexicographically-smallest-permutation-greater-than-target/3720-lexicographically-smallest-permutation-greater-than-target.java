import java.util.*;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Try to match target from left to right.
        for (int i = 0; i < n; i++) {
            int cur = target.charAt(i) - 'a';

            if (freq[cur] > 0) {
                // We can keep target[i] equal.
                freq[cur]--;
            } else {
                // target[i] cannot be matched.
                // Try making the first difference at i.
                int bigger = findBigger(freq, cur);

                if (bigger != -1) {
                    StringBuilder ans = new StringBuilder();
                    ans.append(target, 0, i);
                    ans.append((char) ('a' + bigger));
                    freq[bigger]--;

                    appendSorted(ans, freq);
                    return ans.toString();
                }

                // Can't make target greater at i.
                // Backtrack to an earlier position.
                for (int j = i - 1; j >= 0; j--) {
                    // Restore target[j] because we're changing it.
                    int restored = target.charAt(j) - 'a';
                    freq[restored]++;

                    int biggerAtJ = findBigger(freq, restored);

                    if (biggerAtJ != -1) {
                        StringBuilder ans = new StringBuilder();

                        // Prefix before j stays equal to target.
                        ans.append(target, 0, j);

                        // Make the smallest possible increase.
                        ans.append((char) ('a' + biggerAtJ));
                        freq[biggerAtJ]--;

                        // Smallest possible suffix.
                        appendSorted(ans, freq);

                        return ans.toString();
                    }
                }

                return "";
            }
        }

        // We matched target completely, so target itself is a permutation.
        // We need the next greater permutation.
        for (int i = n - 1; i >= 0; i--) {
            int restored = target.charAt(i) - 'a';
            freq[restored]++;

            int bigger = findBigger(freq, restored);

            if (bigger != -1) {
                StringBuilder ans = new StringBuilder();

                ans.append(target, 0, i);
                ans.append((char) ('a' + bigger));
                freq[bigger]--;

                appendSorted(ans, freq);
                return ans.toString();
            }
        }

        return "";
    }

    private int findBigger(int[] freq, int c) {
        for (int x = c + 1; x < 26; x++) {
            if (freq[x] > 0) {
                return x;
            }
        }
        return -1;
    }

    private void appendSorted(StringBuilder sb, int[] freq) {
        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                sb.append((char) ('a' + c));
                freq[c]--;
            }
        }
    }
}