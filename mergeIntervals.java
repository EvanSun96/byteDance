class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        int[][] res = new int[intervals.length][2];
        int count = 0; //count是构建结果二维数组中所用到的指针
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        res[count++] = intervals[0]; //add the first interval in res
        for (int i = 1; i < intervals.length; i++) {
            if (res[count - 1][1] < intervals[i][0]) { //如果前一个区间的结束与下一个区间的起始不相交
                res[count++] = intervals[i]; //那么就把当前区间添加进去
            } else { //如果前后两个区间有交集，那么这两个区间就应该合并
                int left = Math.min(res[count - 1][0], intervals[i][0]); //左边界取两者左边界最小
                int right = Math.max(res[count - 1][1], intervals[i][1]);//右边界取右边界最小
                res[count - 1][0] = left;
                res[count - 1][1] = right; //然后对之前的区间进行重写 换成当前的新的区间
            }
        }

        return Arrays.copyOf(res, count); //取res的前count个
    }
}
