package Day9账户合并;

import java.util.*;

/**
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 *
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 *
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 *
 * 示例 1：
 *
 * 输入：
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 输出：
 * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * 解释：
 * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
 * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 *
 * -----------------------------------------
 * ***************所有归结到是否是一个人/种类的这种"分类"问题就使用并查集******************
 * 并查集实现基于高度实现路径压缩代码：
 *
 * public class UnionFind {
 *     private int[] parent;
 *     private int[] height;
 *     int size;
 * //初始化时将所有点的父节点设为自己，高度设为1
 *     public UnionFind(int size) {
 *         this.size = size;
 *         this.parent = new int[size];
 *         this.height = new int[size];
 *         for (int i = 0; i < size; i++) {
 *             parent[i] = i;
 *             height[i] = 1;
 *         }
 *     }
 * //递归查找自己的根节点，因为实现了路径压缩，所以一般不会递归很多次
 *     public int find(int element) {
 *         while (element != parent[element]) {
 *         //将自己的的父节点指向父节点的父节点
 *             parent[element] = parent[parent[element]];
 *             element = parent[element];
 *         }
 *         return element;
 *     }
 * //判断两个元素是否在同一个分类
 *     public boolean isConnected(int firstElement, int secondElement) {
 *         return find(firstElement) == find(secondElement);
 *     }
 *
 * //如果要合并的两个集合高度一样，那么随意选一个作为根
 * //这里选的是让secondRoot作为新集合的根。
 * //然后secondRoot高度高了一层，所以+1
 *     public void unionElements(int firstElement, int secondElement) {
 *         int firstRoot = find(firstElement);
 *         int secondRoot = find(secondElement);
 *
 *         if (height[firstRoot] < height[secondRoot]) {
 *             parent[firstRoot] = secondRoot;
 *         } else if (height[firstRoot] > height[secondRoot]) {
 *             parent[secondRoot] = firstRoot;
 *         } else {
 *             parent[firstRoot] = secondRoot;
 *             height[secondRoot] += 1;
 *         }
 *     }
 *
 */

public class Solution {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToIndex = new HashMap<String, Integer>();
        Map<String, String> emailToName = new HashMap<String, String>();
        int emailsCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, emailsCount++);
                    emailToName.put(email, name);
                }
            }
        }
        UnionFind uf = new UnionFind(emailsCount);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                uf.union(firstIndex, nextIndex);
            }
        }
        Map<Integer, List<String>> indexToEmails = new HashMap<Integer, List<String>>();
        for (String email : emailToIndex.keySet()) {
            int index = uf.find(emailToIndex.get(email));
            List<String> account = indexToEmails.getOrDefault(index, new ArrayList<String>());
            account.add(email);
            indexToEmails.put(index, account);
        }
        List<List<String>> merged = new ArrayList<List<String>>();
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<String>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }
        return merged;
    }
}

class UnionFind {
    int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int index1, int index2) {
        parent[find(index2)] = find(index1);
    }

    public int find(int index) {
        if (parent[index] != index) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }

}
