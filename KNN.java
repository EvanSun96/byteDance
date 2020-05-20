//每次都拿出来离（0，0）最近的
//所以我们可以先将所有的距离计算出来，然后排序。找出第k远的那个距离，然后重新对点进行遍历，
//如果某个点离（0，0）的距离小于等于这个临界值，那么就将其添加到最后结果中。
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int N = points.length;
        int[] dists = new int[N];
        for (int i = 0; i < N; ++i)
            dists[i] = dist(points[i]);

        Arrays.sort(dists);
        int distK = dists[K-1];

        int[][] ans = new int[K][2];
        int t = 0;
        for (int i = 0; i < N; ++i)
            if (dist(points[i]) <= distK)
                ans[t++] = points[i];
        return ans;
    }

    public int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}

//但是起始重点在于找到那个k临界值，也就是说在距离里面选出第k小的那个数即可
//我们可以用k select算法 这算法是基于quick select的，就是说我们用每次拿出一个点，然后判断这个点是不是那个临界点
public int[][] kClosest(int[][] points, int K) {
    int len =  points.length, l = 0, r = len - 1;
    while (l <= r) {
        int mid = helper(points, l, r);
        if (mid == K) break; //如果找到这个位置了，就直接break即可，此时在K前面的都是我们要的
        if (mid < K) { //因为helper函数，此时所有比K大的都在mid后面，属于如果当前Mid小于K,那么就提升下界
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    return Arrays.copyOfRange(points, 0, K); //直接返回前k个即可
}

private int helper(int[][] A, int l, int r) { //helper函数返回的是l指针对应的值应该处的位置 每一次调这个函数都能就会把A[l]放到正确位置，然后返回这个位置
    int[] pivot = A[l];
    while (l < r) {
        while (l < r && compare(A[r], pivot) >= 0) r--;
        A[l] = A[r];
        while (l < r && compare(A[l], pivot) <= 0) l++;
        A[r] = A[l];
    }
    A[l] = pivot;
    return l;
}

private int compare(int[] p1, int[] p2) {
    return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
}

//或者我们可以构建一个pq, pq里面储存了点的坐标以及点的距离，然后我们按照距离排序，每次poll出一个，poll K次
public int[][] kClosest(int[][] points, int K) {
    PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
    for (int[] p : points) {
        pq.offer(p);
        if (pq.size() > K) {
            pq.poll();
        }
    }
    int[][] res = new int[K][2];
    while (K > 0) {
        res[--K] = pq.poll();
    }
    return res;
}
