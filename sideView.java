//left view
class Node {
	int data;
	Node left, right;

	public Node(int item)
	{
		data = item;
		left = right = null;
	}
}

/* Class to print the left view */
class BinaryTree {
	Node root;
	static int max_level = 0;

	// recursive function to print left view
	void leftViewUtil(Node node, int level)
	{
		// Base Case
		if (node == null)
			return;

		// If this is the first node of its level
		if (max_level < level) {
			System.out.print(" " + node.data);
			max_level = level;
		}

		// Recur for left and right subtrees
		leftViewUtil(node.left, level + 1);
		leftViewUtil(node.right, level + 1);
	}

	// A wrapper over leftViewUtil()
	void leftView()
	{
		leftViewUtil(root, 1);
	}

} 

//right view
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if(root == null) return res;

        dfs(root, res, 0);
        return res;
    }

    public void dfs(TreeNode root, List<Integer> res, int level) {
        if(root == null) return;
        if(res.size() == level) res.add(root.val);
        dfs(root.right, res, level + 1);
        dfs(root.left, res, level + 1);
    }

}
