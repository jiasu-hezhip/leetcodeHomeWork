package Day5交换字符串元素;

import java.util.*;

/**
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 *
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 *
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 *
 * 示例 1:
 *
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 *
 * 索引如果连通，则直接在连通的索引位置上排序并且返回，其他位置不变
 *
 */

public class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        DisjointSetUnion dsu = new DisjointSetUnion(s.length());
        for (List<Integer> pair : pairs) {
            dsu.unionSet(pair.get(0), pair.get(1));
        }
        Map<Integer, List<Character>> map = new HashMap<Integer, List<Character>>();
        for (int i = 0; i < s.length(); i++) {
            int parent = dsu.find(i);
            if (!map.containsKey(parent)) {
                map.put(parent, new ArrayList<Character>());
            }
            map.get(parent).add(s.charAt(i));
        }
        for (Map.Entry<Integer, List<Character>> entry : map.entrySet()) {
            Collections.sort(entry.getValue(), new Comparator<Character>() {
                public int compare(Character c1, Character c2) {
                    return c2 - c1;
                }
            });
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            int x = dsu.find(i);
            List<Character> list = map.get(x);
            sb.append(list.remove(list.size() - 1));
        }
        return sb.toString();
    }
}

class DisjointSetUnion {
    int[] f;
    int[] rank;
    int n;

    public DisjointSetUnion(int n) {
        this.n = n;
        rank = new int[n];
        Arrays.fill(rank, 1);
        f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
        }
    }

    public int find(int x) {
        return f[x] == x ? x : (f[x] = find(f[x]));
    }

    public void unionSet(int x, int y) {
        int fx = find(x), fy = find(y);
        if (fx == fy) {
            return;
        }
        if (rank[fx] < rank[fy]) {
            int temp = fx;
            fx = fy;
            fy = temp;
        }
        rank[fx] += rank[fy];
        f[fy] = fx;
    }
}
