//dp 因为当前的切割会对后面的进一步切割产生影响
//定义dp[i]为只切割前i个字符是否能切割成功
//所以dp[i] = dp[j] && wordDict.contains(s.substring(j,i)) where j is from [0,i-1]
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;

        for(int i = 1; i<= s.length(); i++){
            for(int j = 0; j<i; j++){
                if(dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}
//follow up:输出所有可能的断开方式
//就是把字典中的每个元素想象成合法的节点，从头开始找这些合法的节点，找到了就继续往下找合法的节点
public class Solution {
    HashMap<String,List<String>> map = new HashMap<String,List<String>>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<String>();
        if(s == null || s.length() == 0) {
            return res;
        }
        if(map.containsKey(s)) {
            return map.get(s);
        }
        if(wordDict.contains(s)) {
            res.add(s);
        }
        for(int i = 1 ; i < s.length() ; i++) {
            String t = s.substring(i); //取index=i到最后的substring
            if(wordDict.contains(t)) { //如果包含
                List<String> temp = wordBreak(s.substring(0 , i) , wordDict);
                if(temp.size() != 0) { //如果前面能够返回结果 说明我们就得出结果了
                    for(int j = 0 ; j < temp.size() ; j++) { //一个个添加进最终结果即可
                        res.add(temp.get(j) + " " + t);
                    }
                }
            }
        }
        map.put(s , res);
        return res;
    }
}
