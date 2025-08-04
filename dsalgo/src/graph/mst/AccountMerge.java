package graph.mst;

import java.util.*;

public class AccountMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n - 1);
        // "johnsmith@mail.com" -> 0
        // "john_newyork@mail.com" -> 0
        // "johnsmith@mail.com" -> 1 ==> 1
        // mail -> account
        HashMap<String, Integer> mapMailAccount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if (!mapMailAccount.containsKey(mail)) {
                    mapMailAccount.put(mail, i);
                } else {
                    ds.unionBySize(i, mapMailAccount.get(mail));
                }
            }
        }
        ArrayList<String>[] mergedMail = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            mergedMail[i] = new ArrayList<>();
        }
        for (Map.Entry<String, Integer> it : mapMailAccount.entrySet()) {
            String mail = it.getKey();
            int parNode = ds.findUPar(it.getValue());
            mergedMail[parNode].add(mail);
        }

        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mergedMail[i].isEmpty())
                continue;
            Collections.sort(mergedMail[i]);
            List<String> tmp = new ArrayList<>();
            tmp.add(accounts.get(i).getFirst());
            tmp.addAll(mergedMail[i]);
            ans.add(tmp);
        }
        return ans;
    }

    public static void main(String[] args) {
        String[][] accounts = {{"John", "johnsmith@mail.com", "john_newyork@mail.com"},
                {"John", "johnsmith@mail.com", "john00@mail.com"},
                {"Mary", "mary@mail.com"},
                {"John", "johnnybravo@mail.com"}};
        List<List<String>> list = Arrays.stream(accounts).map(Arrays::asList).toList();

        AccountMerge obj = new AccountMerge();
        List<List<String>> mergedAccount = obj.accountsMerge(list);
        mergedAccount.forEach(System.out::println);
    }
}
