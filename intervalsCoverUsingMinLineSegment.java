//https://www.cnblogs.com/Draymonder/p/7215230.html
//java
class Solution {
    public int minCover(int[][] intervals, int total) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> (a[0] == b[0]？ a[1] - b[1]: a[0] - b[0]));

        int count = 0;
        int cover = 0;
        int last = 0;
        int start = intervals[0][0];
        for (int i = 0; i<intervals.length; i++) {
            if (i< intervals.length - 1 && intervals[i+1][0] <= last) { //最远的<= last的，就是我们要添加进去的那个区间
                continue;
            }
            last = intervals[i][1]; //update last
            count++;
            cover = last - start;
            if (cover >= total) {
                return count;
            }

        }
        return -1;
    }
}
//
