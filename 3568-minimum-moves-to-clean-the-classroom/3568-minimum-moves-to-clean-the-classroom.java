import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = -1, sc = -1;

        List<int[]> litter = new ArrayList<>();

        // Find starting position and litter positions
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    sr = r;
                    sc = c;
                } else if (ch == 'L') {
                    litter.add(new int[]{r, c});
                }
            }
        }

        int k = litter.size();
        int fullMask = (1 << k) - 1;

        /*
         * best[r][c][mask] =
         * maximum remaining energy with which we've
         * reached (r, c) having collected 'mask'.
         */
        int[][][] best = new int[m][n][1 << k];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(best[r][c], -1);
            }
        }

        // State: row, col, mask, remaining energy, distance
        Queue<int[]> queue = new ArrayDeque<>();

        best[sr][sc][0] = energy;
        queue.offer(new int[]{sr, sc, 0, energy, 0});

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int mask = cur[2];
            int e = cur[3];
            int dist = cur[4];

            // All litter collected
            if (mask == fullMask) {
                return dist;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // Out of bounds
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                // Cannot make a move with zero energy
                if (e == 0) {
                    continue;
                }

                // Moving costs 1 energy
                int ne = e - 1;
                int nmask = mask;

                // Collect litter
                for (int i = 0; i < k; i++) {
                    if (litter.get(i)[0] == nr &&
                        litter.get(i)[1] == nc) {

                        nmask |= (1 << i);
                        break;
                    }
                }

                // Reset energy on R
                if (classroom[nr].charAt(nc) == 'R') {
                    ne = energy;
                }

                /*
                 * If we've already reached this exact
                 * (position, litterMask) with >= energy,
                 * this state is dominated and can be ignored.
                 */
                if (best[nr][nc][nmask] >= ne) {
                    continue;
                }

                best[nr][nc][nmask] = ne;
                queue.offer(new int[]{
                    nr, nc, nmask, ne, dist + 1
                });
            }
        }

        return -1;
    }
}