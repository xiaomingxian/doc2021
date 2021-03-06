package xxm.bingfa.code;

import xxm.springlearn.pojo.Person;

import javax.security.auth.login.Configuration;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class TestYw {

    public static void main(String[] args) {
//        getN(4,System.out::println);

        mapTest();

    }

    private static void mapTest() {

        HashMap<String, Object> map = new HashMap<>();


        ArrayList<Map<String, Object>> msgs = new ArrayList<>();
        map.put("msg", msgs);

        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> data = new HashMap<>();

            data.put("workId", Long.valueOf(i));
            data.put("taskId", i + "");
            data.put("name", "name:" + i);

            msgs.add(data);
        }


        //解析
        Object msg = map.get("msg");
        if (msg instanceof List) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) msg;

            for (Map<String, Object> dataMap : list) {
                String nameData = (String) dataMap.get("name");

                Long wid = (Long) dataMap.get("workId");

                System.out.println(nameData+"-"+wid);

            }


        }


    }


    public static void getN(Integer n, Consumer<Integer> kont) {
        if (n <= 1) {
            kont.accept(n);

        } else {

            getN(n - 1, ret -> kont.accept(ret));
        }

    }


//
//    public static void main(String[] args) {
//
//        String pattern = ".*runoob.*";
//        boolean isMatch = Pattern.matches(pattern, "aaaa");
//        System.out.println(isMatch);
//
//
//
//
//
////        new TestYw().test();
////武汉数据模拟
////        wuhanDeptAndUser();
//
//
//    }

    private static void wuhanDeptAndUser() {
        String curRank = "P4";
        boolean p4 = new TestYw().comp("P4", "M2-3", true);
        System.out.println(p4);

        List<String> leadRank = new ArrayList<>();
//        M2,M2-1,M2-2,M2-3
        List<String> ranks = Arrays.asList("M2", "M2-1", "M2-2", "M2-3");
        boolean areEqualRank = false;
        for (String rank : ranks) {
            //当前员工职级小于范围里的职级
            if (compareJobRank(rank, curRank, false)) {
                leadRank.add(rank);
            }
            if (curRank.equals(rank)) {
                areEqualRank = true;
            }
        }

        System.out.println(areEqualRank);
        System.out.println(leadRank);

        ArrayList list = new ArrayList();
        Person p = new Person("Tom", 18);
        while (p != null) {
            list.add(p);
            p = getPerson();
        }
        System.out.println(list);
    }

    static int count = 0;

    private static Person getPerson() {
        if (count == 3) return null;
        Person person = new Person("tom" + count, count);
        count++;
        return person;
    }


    public void test() {
        boolean p4 = new TestYw().comp("M2-2", "P6", true);
        System.out.println(p4);

    }


    private boolean comp(String rank1, String rank2, boolean includeEqual) {
        String r1 = rank1.toUpperCase();
        String r2 = rank2.toUpperCase();
        if ((r1.startsWith("P")) && r2.startsWith("M")) {
            return false;
        }
        if (r1.startsWith("M") && r2.startsWith("P")) {
            return true;
        } else {
            return includeEqual ? r2.compareTo(r1) <= 0 : r1.compareTo(r2) > 0;
        }
    }

    public static boolean compareJobRank(String rank1, String rank2, boolean includeEqual) {
        String r1 = rank1.toUpperCase();
        String r2 = rank2.toUpperCase();
        if ((r1.startsWith("P")) && r2.startsWith("M")) {
            return false;
        }
        if (r1.startsWith("M") && r2.startsWith("P")) {
            return true;
        } else {
            return includeEqual ? r2.compareTo(r1) <= 0 : r1.compareTo(r2) > 0;
        }
    }


}
