class Solution {
    public boolean sumGame(String num) {
     int n = num.length();
     int half = n / 2;

     int diff = 0;
     int qDiff = 0;

     for(int i =0; i<n; i++){
        char c = num.charAt(i);

        if(c == '?'){
            qDiff += (i < half ? 1: -1);
        } else{
            diff  += (i < half ? 1: -1)*(c-'0');
        }
     }
      return qDiff % 2 != 0 || diff * 2 + qDiff * 9 != 0;
}
}