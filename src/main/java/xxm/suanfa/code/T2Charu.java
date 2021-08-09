package xxm.suanfa.code;

public class T2Charu {
    public static void main(String[] args) {
        int[] arr={3,1,4,2,7,9,6,8,13,12,11,200,100};

        sort(arr);

        for (int i : arr) {
            System.out.print(i+",");
        }

        System.out.println();



    }

    /**
     * 分为有序数组 和 无序数组
     *
     * @param arr
     */
    private static void sort(int[] arr) {

        //数组中的第一个元素作为有序数组中的元素
        //从第二个元素开始认为是 无序数组中的元素 倒叙便利有序数组进行比对 替换
        if (arr.length<=1)return;
        for (int i = 1; i < arr.length; i++) {//要进行比较的数字(无序数组)//从第二个开始
            int val=arr[i];
            //有序数组初始值(的位置)为第一个元素
            int j=i-1;
            //倒叙遍历进行比较
            for (;j>=0;--j){
                //如果要比较的数字 小于 此时遍历的数字 就记录此位置
                if(val<arr[j]){//小于时
                    arr[j+1]=arr[j];
                }else {
                    break;
                }
            }
            //记录j的位置 让比较的值 替换
            arr[j+1]=val;

        }




    }


}
