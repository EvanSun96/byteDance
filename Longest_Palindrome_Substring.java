class Solution {
    String res = "";
    public String longestPalindrome(String s) {
        if(s==null||s.length()==0) {
            return s;
        }

        for(int i = 0; i < s.length(); i++) {
            helper(s, i, i);
            helper(s, i, i + 1);
        }
        return res;

    }

    public String helper(String s, int left, int right) {
        while(left>=0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        String cur = s.substring(left + 1, right);
        if(cur.length() > res.length()) {
            res = cur;
        }
        return res;
    }
}
//dp
class Solution {
    public String longestPalindrome(String s) {
        if(s==null||s.length() == 0||s.length() == 1) return s;
        String res = "";
        boolean[][] dp = new boolean[s.length()][s.length()]; //dp[i][j]代表substring(i,j)是回文的
        int max = 0; //维护一个全局最长的数值
        for(int j = 0; j < s.length(); j++){
            for(int i = 0; i <= j; i++){
                dp[i][j] = s.charAt(i)==s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if(dp[i][j]) {
                    if(j - i + 1 > max) {
                        max = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }
}
