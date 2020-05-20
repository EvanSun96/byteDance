
//besides the solution of our previous two pointer
//this hashmap way is much easier to understand, and it seems that we only have one pointer, it's actually two
//and now we implement the queue way, it is a really genius way to solve preious problems...
class Solution {
    public int lengthOfLongestSubstring(String s) {
    int[] map = new int[128];
    int start = 0, end = 0; //still two pinters, end used for exanding and start used for contraction
    int maxLen = 0, counter = 0; //

    while (end < s.length()) {
      final char c1 = s.charAt(end);
      if (map[c1] > 0) counter++; //counter used for keep track of the duplicate number in current substring(because if we already have char the same as c1 in map, then means we now meet a duplicate )
      map[c1]++; //update
      end++; //end move forward

      while (counter > 0) { //if we have a duplcate, then start to shrinking
        final char c2 = s.charAt(start);
        if (map[c2] > 1) counter--;
        map[c2]--; //update map
        start++; //update start
      }

      maxLen = Math.max(maxLen, end - start); //keeps track of maxLen
    }

    return maxLen;
  }
}
