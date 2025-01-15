package bs.bsOnAnswers;

public class NthRootOfM {

    public static void main(String[] args) {
        int N= 9, M = 1953125;
        System.out.println(nthRootOfMBrute(N,M));
        System.out.println(nthRootOfMOptimized(N,M));
    }

    /**
     * as answer will lie in the range [1,M] {when N=1, ans will be M, i.e }
     * TC:O(M*logN)
     */
    private static int nthRootOfMBrute(int N, int M){
        for(int i=1; i<=M;i++){
            int pow = (int)findPow(i,N);
            if(pow == M){
                return i;
            }else if(pow>M) break;
        }
        return -1;
    }
    // TC:O(logN)
    private static long findPow(int base, int exp){
        long ans = 1;
        while(exp>0){
            if(base % 2 == 1){
                exp--;
                ans = ans*base;
            }else{
                exp = exp/2;
                base = base*base;
            }
        }
        return ans;
    }

    /**
     * use of Binary search on answer, as answer will lie in the range [1,M]
     */
    private static int nthRootOfMOptimized(int N, int M){
        int low = 1, high = M;
        while(low<=high){
            int mid = (low+high)/2;
            int midN = func(mid,N,M);
            if(midN == 0) return mid;
            else if(midN == 2) high = mid -1;
            else{
                low = mid+1;
            }
        }
        return -1;
    }
    private static int func(int mid, int N, int M){
        long ans = 1;
        for(int i=1; i<=N; i++){
            ans = ans*mid;
            if(ans > M){
                return 2;
            }
        }
        if(ans == M) return 0;
        return 1; // ans < M
    }
}
