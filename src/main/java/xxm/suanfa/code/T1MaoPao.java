package xxm.suanfa.code;

public class T1MaoPao {

    public static void main(String[] args) {

        int[] arr={3,1,4,2,7,9,6,8};

        //与相邻的比较
        for (int i = 0; i < arr.length; i++) {

            //提前退出冒泡循环的标识
            boolean flag=false;

            //for (int j = 0; j < arr.length; j++) { // 1 不思考 判断所有
            for (int j = 0; j < arr.length-i-1; j++) { // 2 一轮出来就选出右边的最大值下次就不参与比较  参与比较的元素 长度-1-轮数
                if (arr[j]>arr[j+1]){
                    //换位置
                    int tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                    flag=true;//表示有数据交换
                }

            }

            if (!flag)break;//表示没有数据交换 提前退出


        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");

        }
        System.out.println();


    }


}
