package xxm.bingfa.code;

import lombok.Data;

import java.util.*;

public class TestLru {
    public static void main(String[] args) {
        new String();
        new StringBuffer();
        new StringBuilder("  ");
        HashMap<String, Object> map = new HashMap<String, Object>();

//        namespace




        //4.	设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
        //1.	set(key, value)：将记录(key, value)插入该结构
        //2.	get(key)：返回key对应的value值
        //        要求
        //1.	set和get方法的时间复杂度为O(1)
        //2.	某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
        //3.	当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。

        //public class LRUCache {
        //
        //}

        System.out.println(22000*5/100);
        System.out.println(2>2);


            System.out.println("M1".compareTo("M2-1"));

        // 插入 记录索引  k-v
        //TestLru test = new TestLru();
        //for (int i = 0; i <11 ; i++) {
        //    test.lruSet(i+"",i+"");
        //
        //}

        //台阶数 走法
        //1     1    基础
        //2     2    基础
        //3     2(2) 1(1)
        //4     3(3) 2(2)
        //5     4(5) 3(3)

    }
    @Data
    class Node{
        String k;
        String v;
    }

    int k = 10;
    List<Node> queue = new ArrayList<Node>();
    Map<String, Node> map = new HashMap<String, Node>();

    public  void lruSet(String k,String v) {
        if (queue.size() < 10) {
            //可存
            Node node = new Node();
            node.setK(k);
            node.setV(v);
            queue.add(node);
            map.put(k,node);

        } else {
            //淘汰头部
            ListIterator<Node> nodeListIterator = queue.listIterator();
            while (nodeListIterator.hasNext()){
                Node next = nodeListIterator.next();
                System.out.println(":淘汰："+next.getV());
                nodeListIterator.remove();
                break;
            }
            //淘汰map


            lruSet(k,v);
        }


    }

    public String lruGet(String k) {
        //wiehu
        return map.get(k)==null?null:map.get(k).getV();
    }
}
