//lca of binary search tree
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        } else if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }
}

//lca of binary tree
//recursion
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null){
            return root;
        }

        return left == null ? right: left;
    }
}
//iterative
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {//this reciusrion aims to find the first common ancester of p and q
        if(root == null || root == p || root == q) return root;

        Deque<TreeNode> stack = new ArrayDeque<>();//use in dfs tranverse

        Map<TreeNode, TreeNode> parent = new HashMap<>();//use to store the path so can trace all the way back to parent node

        parent.put(root, null); //initialize parent
        stack.push(root);//initialize stack

        //the ending condition for this while will be the time we found that the parent map contains the key p and key q
        while (!(parent.containsKey(p) && parent.containsKey(q))) {

            TreeNode cur = stack.pop();

            //now we have to put cur.left-cur and cur.right-cur in parent, and push cur.left and cur.right in stack
            if (cur.left != null) {
                parent.put(cur.left, cur);//son-parent pair, why? becasue we need to find parent throught son as key
                stack.push(cur.left);
            }
            if (cur.right != null) {
                parent.put(cur.right, cur);
                stack.push(cur.right);
            }
        }

        //now, we have to construct the path from root to p, store every node in ancester set
        HashSet<TreeNode> ancesters = new HashSet<>();
        while (p != null) {
            ancesters.add(p);
            p = parent.get(p); //p will move to it parent
        }
        //now we only needs to check the path starting from q, if cur node is been contained in ancester, then tha's the common ancester of p and q
        while (!ancesters.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }  
}
