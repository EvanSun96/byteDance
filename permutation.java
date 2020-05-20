//input has no duplicates
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        List<Integer> list = new ArrayList<Integer>();
        helper(res, list, nums); //unlike other combination and permutation problems, we only needs three parameters, we don't need level or start, so this is pure.
        return res;
    }
    public void helper(List<List<Integer>> res, List<Integer> list, int[] nums){
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(list.contains(nums[i])) continue; // another way to pruning some branch, in order to avoid duplicates in this list
            //attention: if this input is not a set of distinct integrs, things might change
            list.add(nums[i]);
            helper(res, list, nums);//看似没变 实际上是变了
            list.remove(list.size() - 1);
        }
    }
}
//input has duplicates
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums);

        helper(res, new ArrayList<>(), nums,new boolean[nums.length]);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
        if(list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return; //为什么加不加return 都可以？
        }

        for(int i = 0; i< nums.length; i++) {
            if(used[i] || i>0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            used[i] = true;
            list.add(nums[i]);
            helper(res, list, nums, used);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
//next permutation:
class Solution {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length == 0) return;

        //from end to start, find last two elements who are increment.
        for(int end = nums.length - 1;end>0; end--){
            if(nums[end - 1] >= nums[end] ){//if large or equal, just keep going
                continue;
            } else{
                for(int i = nums.length - 1; i >= end; i--){//iterate from last to end pointer, find the first one who is larger than nums[end - 1]
                    if(nums[i] > nums[end - 1]){
                        swap(nums, end - 1, i);//if find, then swap it
                        break;
                    }
                }

                Arrays.sort(nums, end, nums.length);
                return;
            }
        }
        Arrays.sort(nums);
        return;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

        return;
    }
}
