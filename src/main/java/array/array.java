package array;

@SuppressWarnings("all")
public class array {
    /**
     * 由于数组是一块连续的空间所以数组的大小不可变
     * @param args
     */
    public static void main(String[] args) {
        //创建数组的几种方式
        //1
        int[] arr = new int[]{1,2,3,4,5};
        //2
        int[] arrs = new int[2];
        arrs[0] = 1;
        arrs[1] = 1;
        //3
        int[] a = {1,2,3,5};
        //字符串数组
        String[] str = {"alone","liuguoqian","xi"};
        //以上arr和str均为指针,指向的实际是1这一块连续空间
        //这种情况实际上是arr重新指向了一个新的内存空间并不是数组变了
        arr = new int[]{1,2,3,4,0,12,42,14,10};
        Bubblesort(arr);
        //quickSort(arr,0,arr.length-1);
        //selectSort(arr);
        //insertSort(arr);
        for (int i = 0; i < arr.length;++i)
            System.out.println(arr[i]);
        reverse(str);
        for (int i = 0; i < str.length; ++i)
            System.out.println(str[i]);
        String[] newStr = copy(str);
        for (int i = 0; i < newStr.length; ++i)
            System.out.println(newStr[i]);
    }
    public static void Bubblesort(int[] arr){
        int flag,temp;
        for (int i = 1; i < arr.length; ++i){
            flag = 0;
            for (int j = arr.length - 1; j >= i; --j){
                if (arr[j - 1] > arr[j])
                {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    flag = 1;
                }
            }
            if (flag == 0)
                break;
        }
    }
    public static void reverse(String[] str){
        int i,j;
        String temp;
        i = 0;j = str.length - 1;
        while (i < j){
            temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            ++i;
            --j;
        }
    }
    public static String[] copy(String[] str)
    {
        String[] newStr = new String[str.length];
        for (int i = 0;i < str.length; ++i)
            newStr[i] = str[i];
        return newStr;
    }
    //快排
    public static void quickSort(int[] arr,int low,int high)
    {
        int temp,i,j;
        i = low;j = high;
        if (low < high){
            temp = arr[low];
            while (i < j){
                while (i < j && arr[j] >= temp)
                    --j;
                if (i < j){
                    arr[i] = arr[j];
                    ++i;
                }
                while (i < j && arr[i] < temp)
                    ++i;
                if (i < j){
                    arr[j] = arr[i];
                    --j;
                }
            }
            arr[i] = temp;
            quickSort(arr,low,i-1);
            quickSort(arr,i+1,high);
        }
    }
    //选排
    public static void selectSort(int[] arr){
        int temp,i,j,k;
        for (i = 0; i < arr.length;++i){
            k = i;
            for (j = i + 1; j < arr.length;++j)
                if (arr[j] < arr[k])
                    k = j;
            temp = arr[k];
            arr[k] = arr[i];
            arr[i] = temp;
        }
    }
    public static void insertSort(int[] arr)
    {
        int i,j,temp;
        for (i = 1; i < arr.length; ++i)
        {
            temp = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > temp){
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = temp;
        }
    }
    /**
     * 数组常见的异常
     * ArrayIndexOutOfBoundsException:数字下标越界异常
     * NullPointerException:空指针异常
     */
}
