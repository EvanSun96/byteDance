package tree.trie;

/**
 * 字典树 又叫前缀树 因为他也能匹配前缀
 */
public class Trie {

  public class TrieNode {

      TrieNode[] children;
      boolean isWord;
      public TrieNode() {
          children = new TrieNode[26];
          isWord = false;
      }
  }

    TrieNode root;

    private void add(String word) {
        TrieNode cur = root;
        for (char c: word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {//这条路径虽然有 但是是null
                cur.children[c - 'a'] = new TrieNode();//所以我们就将其进行 新建一个trieNode
            }
            cur = cur.children[c - 'a'];//然后将cur指针移到新建的那个上面
        }
        cur.isWord = true;
    }

    private boolean contains(String word) {
        TrieNode cur = root;
        for (char c: word.toCharArray()) { //针对建立好的字典树
            if (cur.children[c - 'a'] == null) {//一旦检索不到 立刻返回false
                return false;//
            }
            cur = cur.children[c - 'a'];//如果检索到 就继续往下走
        }
        return cur.isWord;//如果能一直走到最后 最后cur指针对应的叶子节点的isWord属性已经被设置成true了
    }

    private boolean startsWith(String prefix) {//前缀树的精华所在
        TrieNode cur = root;
        for (char c: prefix.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return true;//与contains函数相比 只要遍历完prefix没有返回false 那么都一律返回true
    }

    private boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(TrieNode cur, String word, int index) {
        if (index == word.length()) {
            return cur.isWord;
        }
        if (word.charAt(index) == '.') { //
            for (TrieNode temp: cur.children) {
                if (temp != null && search(temp, word, index)) {
                    return true;
                }
            }
            return false;
        } else { //
            char c = word.charAt(index);
            TrieNode temp = cur.children[c - 'a'];
            return temp != null && search(temp, word, index + 1);
        }
    }
}
