public class BinarySearchTreeImpl {

    private TreeNode root;

    private int size;

    public BinarySearchTreeImpl() {
        this.root = null;
        size = 0;
    }

    public void add(int val) {
        root = add(root, val);
    }

    private TreeNode add(TreeNode node, int val) {
        if (node == null) {
            size++;
            return new TreeNode(val);
        }
        if (node.val == val) {
            throw new IllegalArgumentException("节点已经存在");
        }
        if (node.val > val) {
            node.left = add(node.left, val);
        } else {
            node.right = add(node.right, val);
        }
        return node;
    }

    public void remove(int val) {
        root = remove(root, val);

    }

    private TreeNode remove(TreeNode node, int val) {//对于删除操作 跟查找和添加一样 都是先找出值应该插入的位置 然后再想办法把位置空出来把要插的值插入
        if (node == null) return null;
        if (node.val > val) { //如果根节点的值大于要删除的值 说明要删除的位置存在于左子树中
            node.left = remove(node.left, val);//递归就好
        } else if (node.val < val) {//同理
            node.right = remove(node.right, val);
        } else {//如果要删除的正好是根节点 根据约定俗成 我们要取右子树的最小值作为根节点
            if (node.left == null) {//如果这个树根本就没有左子树 那么直接返回右子树即可
                size--;
                return node.right;
            } else if (node.right == null) {//同理
                size--;
                return node.left;
            } //如果抗过了以上if语句的洗礼
            TreeNode minNode = findMin(node.right);//findMin方法见下面 传入的参数为右子树根部
            node.val = minNode.val;//先对根节点重新赋值
            node.right = remove(node.right, node.val);//然后在递归的方式去掉那个曾经的右子树的最左节点
            size--;
        }
        return node;
    }

    private TreeNode findMin(TreeNode node) {//就是为了找 某树的最左节点的
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    public boolean contains(int val) {
        return contains(root, val);
    }

    private boolean contains(TreeNode node, int val) {
        if (node == null) return false;
        if (node.val == val) {
            return true;
        } else if (node.val > val) {
            return contains(node.left, val);
        } else {
            return contains(node.right, val);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void print() {
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println("第" + (level + 1) + "层");
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                System.out.print(cur.val);
                if (cur.left != null) {
                    System.out.println(" 的左子节点：" + cur.left.val);
                    queue.offer(cur.left);
                }
                if (cur.left != null) {
                    System.out.println(" 的右子节点：" + cur.right.val);
                    queue.offer(cur.right);
                }
                System.out.println();
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        BinarySearchTreeImpl binarySearchTree = new BinarySearchTreeImpl();
        binarySearchTree.add(8);
        binarySearchTree.add(5);
        binarySearchTree.add(6);
        binarySearchTree.add(3);
        binarySearchTree.add(10);
        binarySearchTree.add(9);
        binarySearchTree.add(11);
        binarySearchTree.print();


    }
}
