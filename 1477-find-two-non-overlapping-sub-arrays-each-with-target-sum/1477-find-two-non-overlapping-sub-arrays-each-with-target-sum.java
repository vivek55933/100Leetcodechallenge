class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = 1_000_000_000;
        
        int[] best = new int[n];

        
        Arrays.fill(best,INF);

        int left = 0;
        int sum = 0;
        int answer = INF;

        for(int right = 0; right<n; right++){
            sum += arr[right];

            while(sum>target) {
                sum -= arr[left];
                left++;
            }
            if(right>0){
                best[right] = best[right-1];
            }
            if(sum == target){
                int len= right-left+1;
                if(left>0 && best[left-1]!=INF){
                    answer = Math.min(answer,len +best[left-1]);
                }
                 best[right]= Math.min(best[right],len);
            }
        }
    
    return answer == INF ? -1: answer;
}
}
    

