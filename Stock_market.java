//只能一次交易
//就是针对每一个价格，找出前面所有价格中，比当前价格小并且差距和当前价格最大的
//然后全局维护一个全局的最大差值 就是结果
class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;

        int min = prices[0];
        int profit = 0;
        for(int price: prices){
            min = Math.min(min, price);
            profit = Math.max(profit, price - min);
        }
        return profit;
    }
}
//最多只能交易两次
public class Solution {

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        // dp[i][j] ，表示 [0, i] 区间里，状态为 j 的最大收益
        // j = 0：什么都不操作
        // j = 1：第 1 次买入一支股票
        // j = 2：第 1 次卖出一支股票
        // j = 3：第 2 次买入一支股票
        // j = 4：第 2 次卖出一支股票

        // 初始化
        int[][] dp = new int[len][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 3 状态都还没有发生，因此应该赋值为一个不可能的数
        for (int i = 0; i < len; i++) {
            dp[i][3] = Integer.MIN_VALUE;
        }

        // 状态转移只有 2 种情况：
        // 情况 1：什么都不做
        // 情况 2：由上一个状态转移过来
        for (int i = 1; i < len; i++) {
            // j = 0 的值永远是 0
            dp[i][0] = 0;
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        // 最大值只发生在不持股的时候，因此来源有 3 个：j = 0 ,j = 2, j = 4
        return Math.max(0, Math.max(dp[len - 1][2], dp[len - 1][4]));
    }
}
//贪心算法 在每一个低谷处 找最近的peak
class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;

        while(i < prices.length - 1) { //because we use prices[i+1] later, so i<prices.length-1
            while(i < prices.length - 1 && prices[i] >= prices[i+1]) { //why we use = in here? becasue we want to go as far as we can
                i++;
            }
            valley = prices[i]; //get a valley
            while(i < prices.length - 1 && prices[i] <= prices[i+1]) {
                i++;
            }
            peak = prices[i]; //get its closest peak
            maxprofit += peak - valley; //accumulative maxprofit
        }
        return maxprofit;
    }
}
