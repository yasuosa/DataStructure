package com.rpy;

import com.rpy.list.SeqList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

        public int longestStrChain(String[] words) {
            int ans = 1;
            int n = words.length;
            Arrays.sort(words, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() - o2.length();
                }
            });
            int[] dp = new int[n+1];
            Arrays.fill(dp,1);
            for(int i=1;i<=n;i++){
                String w1= words[i-1];
                for (int j = 1; j < i; j++) {
                    String w2 = words[j-1];
                    if(isPre(w1,w2)){
                        dp[i] = Math.max(dp[i],dp[j]+1);
                        ans = Math.max(ans,dp[i]);
                    }
                }
            }
            return ans;
        }

        private boolean isPre(String w1,String w2){
            int i=0,j=0;
            int n = w1.length();
            int m = w2.length();
            if((n-1) != m) return false;
            while (i < n && j < m){
                if(w1.charAt(i) == w2.charAt(j)){
                    j++;
                }
                i++;
            }
            return j == m;
        }


    public static void main(String[] args) {
        System.out.println(new Main().longestStrChain(new String[]
                {"xbc","pcxbcf","xb","cxbc","pcxbc"}));
    }
}
