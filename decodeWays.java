class Solution {
    public int numDecodings(String s) {
        //值得警醒的是 是让你求共有多少种方法 而不是输出所有的解
        //所有的一位数都是合法的 所有的二位数小于等于26都是合法的
        //i know that we should use DP due to subproblem has impact on cur problem
        if(s == null || s.length() == 0) return 0;

        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for(int i = 2; i <= n; i++){
            int first = Integer.valueOf(s.substring(i-1,i));
            int second = Integer.valueOf(s.substring(i-2,i));
            if(first >= 1 && first <= 9){
                dp[i] += dp[i-1];
            }
            if(second >= 10 && second <= 26){
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}

//decode ways2
//现在我们加入一个“*”表示wildcard
public int numDecodings(String s) {
    /* initial conditions */
    long[] dp = new long[s.length()+1];
    dp[0] = 1;
    if(s.charAt(0) == '0'){
        return 0;
    }
    dp[1] = (s.charAt(0) == '*') ? 9 : 1;

    /* bottom up method */
    for(int i = 2; i <= s.length(); i++){
        char first = s.charAt(i-2);
        char second = s.charAt(i-1);

        // For dp[i-1]
        if(second == '*'){
            dp[i] += 9*dp[i-1];
        }else if(second > '0'){
            dp[i] += dp[i-1];
        }

        // For dp[i-2]
        if(first == '*'){
            if(second == '*'){
                dp[i] += 15*dp[i-2];
            }else if(second <= '6'){
                dp[i] += 2*dp[i-2];
            }else{
                dp[i] += dp[i-2];
            }
        }else if(first == '1' || first == '2'){
            if(second == '*'){
                if(first == '1'){
                   dp[i] += 9*dp[i-2];
                }else{ // first == '2'
                   dp[i] += 6*dp[i-2];
                }
            }else if( ((first-'0')*10 + (second-'0')) <= 26 ){
                dp[i] += dp[i-2];
            }
        }

        dp[i] %= 1000000007;
    }
    /* Return */
    return (int)dp[s.length()];
}
