class Solution {
    static class Node {
        int start, end, weight, index;

        Node(int start, int end, int weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        Node[] a = new Node[n];
        for (int i = 0; i < n; i++) {
            List<Integer> in = intervals.get(i);
            a[i] = new Node(in.get(0), in.get(1), in.get(2), i);
        }

        Arrays.sort(a, (x, y) -> {
            if (x.start != y.start)
                return Integer.compare(x.start, y.start);
            return Integer.compare(x.end, y.end);
        });

        // Find next compatible interval.
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = i + 1, hi = n;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (a[mid].start > a[i].end)
                    hi = mid;
                else
                    lo = mid + 1;
            }

            next[i] = lo;
        }

        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {

                State skip = dp[i + 1][k];

                State following = dp[next[i]][k - 1];

                List<Integer> takeIndices =
                        new ArrayList<>(following.indices);

                takeIndices.add(a[i].index);

                Collections.sort(takeIndices);

                State take = new State(
                        a[i].weight + following.weight,
                        takeIndices
                );

                dp[i][k] = better(take, skip);
            }

            dp[i][0] = new State(0, new ArrayList<>());
        }

        List<Integer> ans = dp[0][4].indices;

        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }

    private State better(State a, State b) {
        if (a.weight != b.weight) {
            return a.weight > b.weight ? a : b;
        }

        // Lexicographically smaller index list.
        List<Integer> x = a.indices;
        List<Integer> y = b.indices;

        int len = Math.min(x.size(), y.size());

        for (int i = 0; i < len; i++) {
            if (!x.get(i).equals(y.get(i))) {
                return x.get(i) < y.get(i) ? a : b;
            }
        }

        return x.size() <= y.size() ? a : b;
    }
}