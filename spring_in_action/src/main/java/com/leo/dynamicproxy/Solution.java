package com.leo.dynamicproxy;

import jdk.nashorn.internal.ir.CallNode;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        Map<String,Integer> map = new HashMap();
        for (int i=0;i<accounts.size();i++) {
            List<String> account =  accounts.get(i);
            boolean flag = true;
            int index = 0;
            for (int j=1;j<account.size();j++) {
                if (map.containsKey(account.get(j))){
                    index = map.get(account.get(j));
                    flag = false;
                    break;
                }
            }
            if (flag){
                int temp = result.size()>=1?result.size():0;
                result.add(account);
                accounts.get(i).stream().skip(1).forEach(a->map.put(a,temp));
            }else{
                int finalIndex = index;
                account.stream().forEach(a->map.put(a, finalIndex));
                result.get(finalIndex).addAll(account);
                result.set(finalIndex,result.get(finalIndex).stream().distinct().collect(Collectors.toList()));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        ArrayList one = new ArrayList();
        one.add("John");
        one.add("johnnybravo@mail.com");
        accounts.add(one);
        one = new ArrayList();
        one.add("John");
        one.add("johnnybravo@mail.com");
        accounts.add(one);
        one = new ArrayList();
        one.add("John");
        one.add("john00@mail.com");
        one.add("john_newyork@mail.com");
        one.add("johnsmith@mail.com");
        accounts.add(one);
        System.out.println(accountsMerge(accounts));
    }
}
