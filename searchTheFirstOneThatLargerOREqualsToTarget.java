//leetcode34 Find First and Last Position of Element in Sorted Array
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[]{-1,-1};

        //int[] res = new int[];

        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                int i = mid;
                int j = mid;
                while(i >= 0 && nums[i] == target) {
                    i--;
                }
                while(j <= nums.length -1 && nums[j] == target) {
                    j++;
                }
                return new int[]{i+1, j-1};
            }
            if(nums[mid] > target) {
                right = mid - 1;
            }
            if(nums[mid] < target) {
                left = mid + 1;
            }
        }
        return new int[]{-1,-1};
    }
}

//leetCode35
class Solution {
  public int searchInsert(int[] nums, int target) {
    int pivot, left = 0, right = nums.length - 1;
    while (left <= right) {
      pivot = left + (right - left) / 2;
      if (nums[pivot] == target) return pivot;
      if (target < nums[pivot]) right = pivot - 1;
      else left = pivot + 1;
    }
    return left;
  }
}
