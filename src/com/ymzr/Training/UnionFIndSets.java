package com.ymzr.Training;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFIndSets {
    /**
     * --------------------------------集合版并查集--------------------------------------
     */
    /**
     * 用一个空节点包住数据
     * @param <V>
     */
    public static class Element<V> {
        V val;
        public Element (V v) {
            val = v;
        }
    }

    /**
     * 并查集集合
     * @param <V>
     */
    public static class UnionFindSet<V> {
        //元素集合，key是元素，value是空节点
        HashMap<V, Element<V>> elementMap;
        //父元素集合，key是当前元素创建的空节点，value是他的父节点，一开始是自己
        HashMap<Element<V>, Element<V>> fatherMap;
        //个数的集合，key是当前元素创建的空节点，value是以他为父节点的所有元素个数之和，默认值是1
        HashMap<Element<V>, Integer> sizeMap;

        //初始化并查集
        public UnionFindSet(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V v: list
                 ) {
                Element<V> element = new Element<>(v);
                elementMap.put(v, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        //查询头节点（内部使用）
        public Element<V> findHead(Element<V> element) {
            //利用栈，对所有遍历出来的头节点以下的节点，归为头节点以下
            Stack<Element<V>> stack = new Stack<>();
            //当一个节点的头节点是他自己的时候，改节点为头节点
            while (fatherMap.get(element) != element) {
                stack.push(element);
                element = fatherMap.get(element);
            }
            //将所有栈中的元素的头节点设为要返回的头节点
            while (!stack.isEmpty()) {
                Element<V> element1 = stack.pop();
                fatherMap.put(element1, element);
            }
            return element;
        }

        //判断两个值是否在一个集合内
        public boolean isSameSet(V a, V b) {
            //判断是否加入过并查集的初始化
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                //看两个元素的头节点是否是相等
                return findHead(fatherMap.get(new Element<>(a))) == findHead((fatherMap.get(new Element<>(b))));
            }
            return false;
        }

        //将两个值进行合并
        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                //找到两个元素的头节点
                Element<V> head1 = findHead(new Element<>(a));
                Element<V> head2 = findHead(new Element<>(b));
                //如果两个元素不相等，则合并，将size小的头节点指向大的头节点
                if (head1 != head2) {
                    //取得大的头节点
                    Element<V> big = sizeMap.get(head1) > sizeMap.get(head2) ? head1 : head2;
                    //取得小的头节点
                    Element<V> small = head1 == big ? head2 : head1;
                    //父节点map中修改小的指向大的
                    fatherMap.put(small, big);
                    //size集合将小的size加到大的size更新大的头节点的size
                    sizeMap.put(big, sizeMap.get(big) + sizeMap.get(small));
                    //删除小头节点的size的
                    sizeMap.remove(small);
                }
            }
        }
    }




    /**
     * --------------------------------简易并查集[数组版]--------------------------------------
     */
    //设置集合大小
    public static final int N = 10009;
    //新建集合
    public static int[] p = new int[N];

    public static int find(int a) {
        if (p[a] != a) {
            p[a] = find(p[a]);
        }
        return p[a];
    }

    public static void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }

    public static boolean query(int a, int b) {
        return p[find(a)] == p[find(b)];
    }

}
