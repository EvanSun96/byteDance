class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        //与LC77相对应 排列组合问题就像是给定枝数和层数 让你输出所有的结果
        //这道题一眼看上去 我也知道是要用递归
        //仔细一想 这个其实跟组合很像，就是把Cn0, Cn1, Cn2, Cn3....Cnn都给输出来即可
        //这代码属实有点low,但是终归是自己写出来了
        for(int i = 0; i <= nums.length; i++){
            combine(nums, nums.length, i);
        }
        return res;
    }

    public void combine(int[] nums, int n, int k) {
        //List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), nums, n, k, 1);
        //return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int n, int k, int start) {
        if (k == 0) {
            res.add(new ArrayList<>(list));//因为规定了list是个List 所以要转换一下？list为什么不可以直接定义ArrayList呢？
            return;
        }
        for (int i = start; i <= n; i++) { //之前的排列 这儿每次都是从1开始到n 然后在内部剪掉已经存在的枝，现在的组合因为是按照顺序来的 因此每一次都要进行不一样的剪枝
            list.add(nums[i - 1]);
            helper(res, list, nums, n, k - 1, i + 1);
            list.remove(list.size() - 1);//返回上一层
        }
    }
}
