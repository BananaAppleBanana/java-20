package com.example.java20.week3.designPattern;

import java.util.ArrayDeque;
import java.util.Deque;

public class CompositionPattern {
    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
    //print tree level by level
    public void print(TreeNode root) {
        if(root == null) {
            //..
            return;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int len = q.size();
            for(int i = 0; i < len; i++) {
                TreeNode node = q.poll();
                System.out.print(node.val);
                if(node.left != null) {
                    q.offer(node.left);
                }
                if(node.right != null) {
                    q.offer(node.right);
                }
            }
            System.out.println();
        }
    }
}

/**
 * Has-A relation  : composition
 *
 * class A {
 *     B b;
 * }
 *
 * Is-A relation : aggregation
 *
 *  class Pizza {}
 *
 *  class HawaiiPizza extends Pizza {}
 */