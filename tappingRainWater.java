class Solution {
    public int trap(int[] height) {

        int left = 0; //left指针和right指针的作用是标识 如果所有的坑都盛满，当前位置处的水的体积
        int right = height.length - 1;
        int res = 0;
        int leftMax = 0;//不是位置 只是初始化为value=0
        int rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) { //谁小就以谁那边的Max作为基准 即如果左边的小 那么左指针处盛的最大水量就只与leftMax有关 与left右边的任何一个墙的高度无关，即墙如果低，left指针上最终也会是这个高度，墙如果高 left指针最终也会是这样一个高度
               leftMax = Math.max(height[left], leftMax); //当leftMax有必要更新的时候再更新
                res += leftMax - height[left]; //隐形乘以了1
                left++; //左指针右移一位
            } else {
                rightMax = Math.max(height[right], rightMax);
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
//leetcode011 container with most water
class Solution {
    public int maxArea(int[] height) {
        int res = 0;
        int cap = 0;
        int l = 0;
        int r = height.length - 1;
        while(l<r) {
            cap = Math.min(height[l], height[r])* (r - l);
            res = Math.max(res, cap);
            if(height[l] < height[r]) {
                l++;
            } else{
                r--;
            }
        }
        return res;
    }
}
//leetcode084 Largest Rectangle in Histogram
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();//use stack
        int res = 0;//
        for (int i = 0; i <= heights.length; i++) {//iterate heights array
            int h = i == heights.length ? 0 : heights[i];//如果i是height.length 就设h为0 否则设为height[i]
            while (!stack.isEmpty() && h < heights[stack.peek()]) {//当stack非空 并且  h < height[stack.peek()]
                int height = heights[stack.pop()];//如果遇到小的值 就将之Pop出来
                int start = stack.isEmpty() ? -1 : stack.peek(); //计算的起始index
                int area = height * (i - start - 1);//计算面积 高*宽
                res = Math.max(res, area);//维护最大值
            }
            stack.push(i);//stack里面存的是Index
        }
        return res;
    }
}
