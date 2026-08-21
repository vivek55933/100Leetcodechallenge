class Solution {
public:
    long long findKthSmallest(vector<int>& coins, int k) {
        int n = coins.size();

        auto count = [&](long long x) -> long long {
            long long ans = 0;

            // Inclusion-exclusion
            for (int mask = 1; mask < (1 << n); ++mask) {
                long long lcm = 1;
                int bits = 0;
                bool overflow = false;

                for (int i = 0; i < n; ++i) {
                    if (mask & (1 << i)) {
                        ++bits;

                        long long g = std::gcd(lcm, (long long)coins[i]);

                        lcm /= g;

                        if (lcm > x / coins[i]) {
                            overflow = true;
                            break;
                        }

                        lcm *= coins[i];
                    }
                }

                if (overflow || lcm > x)
                    continue;

                long long multiples = x / lcm;

                if (bits & 1)
                    ans += multiples;
                else
                    ans -= multiples;
            }

            return ans;
        };

        long long lo = 1;
        long long hi = 1LL * (*min_element(coins.begin(), coins.end())) * k;

        while (lo < hi) {
            long long mid = lo + (hi - lo) / 2;

            if (count(mid) >= k)
                hi = mid;
            else
                lo = mid + 1;
        }

        return lo;
    }
};