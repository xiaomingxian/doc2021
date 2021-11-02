package z10_java内存模型_诺亚;

public class Test1 {

    public static void main(String[] args) {
        Long i=10L;
        int i1 = i.intValue();
        System.out.println(i1);
    }
}
