package liwei.com.algorithm;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

/**
 * 算法Activity
 * 参考网址：https://www.jianshu.com/p/ae97c3ceea8d
 */
public class AlgorithmActivity extends Activity {

    @BindView(R.id.input_sort_number)
    public EditText inputSortNumber;
    @BindView(R.id.sort_result)
    public TextView sortResult;
    @BindView(R.id.reset_btn)
    public Button resetBtn;
    @BindView(R.id.bubble_sort_btn)
    public Button bubbleSortBtn;
    @BindView(R.id.selction_sort_btn)
    public Button selctionSortBtn;
    @BindView(R.id.insert_sort_btn)
    public Button insertSortBtn;
    @BindView(R.id.shell_sort_btn)
    public Button shellSortBtn;
    @BindView(R.id.quick_sort_btn)
    public Button quickSortBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.reset_btn, R.id.bubble_sort_btn, R.id.selction_sort_btn, R.id.insert_sort_btn, R.id.shell_sort_btn, R.id.quick_sort_btn})
    public void MyClick(View v) {
        String input = inputSortNumber.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            return;
        }
        String[] strArr = input.split(" ");
        int[] arr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }
        switch (v.getId()) {
            case R.id.reset_btn:
                sortResult.setText("");
                break;
            case R.id.bubble_sort_btn:
                sortResult.setText(Arrays.toString(bubbleSort(arr)));
                break;
            case R.id.selction_sort_btn:
                sortResult.setText(Arrays.toString(selctionSort(arr)));
                break;
            case R.id.insert_sort_btn:
                sortResult.setText(Arrays.toString(insertSort(arr)));
                break;
            case R.id.shell_sort_btn:
                sortResult.setText(Arrays.toString(shellSort(arr)));
                break;
            case R.id.quick_sort_btn:
                quickSort(arr, 0, arr.length - 1);
                break;
        }
    }

    /**
     * 冒泡排序-时间复杂度O(n^2)
     * 基本思想：
     * 1、比较相邻的两个数据，如果第二个数小，就交换位置。
     * 2、从后向前两两比较，一直到比较最前两个数据。最终最小数被交换到起始的位置，这样第一个最小数的位置就排好了。
     * 3、继续重复上述过程，依次将第2.3...n-1个最小数排好位置。
     *
     * @param arr 待排序的数组
     */
    private int[] bubbleSort(int[] arr) {
        int temp;
        int[] tempArr = Arrays.copyOf(arr, arr.length);
        Log.e("XXX", "冒泡排序前：" + Arrays.toString(arr));

        //正宗冒泡排序-从最后面的开始比较，小的数逐步往上冒
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (tempArr[j] < tempArr[j - 1]) {
                    temp = tempArr[j];
                    tempArr[j] = tempArr[j - 1];
                    tempArr[j - 1] = temp;
                }
            }
        }

        //经过优化的冒泡排序
        //数据的顺序排好之后，冒泡算法仍然会继续进行下一轮的比较，直到arr.length-1次，后面的比较没有意义的
        //如果发生了交换flag设置为true；如果没有交换就设置为false。这样当一轮比较结束后如果flag仍为false，即：这一轮没有发生交换，说明数据的顺序已经排好，没有必要继续进行下去
//        boolean flag;
//        for (int i = 0; i < arr.length - 1; i++) {
//            flag = false;
//            for (int j = arr.length - 1; j > i; j--) {
//                if (tempArr[j] < tempArr[j - 1]) {
//                    temp = tempArr[j];
//                    tempArr[j] = tempArr[j - 1];
//                    tempArr[j - 1] = temp;
//                    flag = true;
//                }
//            }
//            if (!flag) {
//                break;
//            }
//        }

        //下沉排序-从起始位置开始比较，大的数逐步下沉
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                if (tempArr[j] > tempArr[j + 1]) {
//                    temp = tempArr[j];
//                    tempArr[j] = tempArr[j + 1];
//                    tempArr[j + 1] = temp;
//                }
//            }
//        }

        Log.e("XXX", "冒泡排序后：" + Arrays.toString(tempArr));
        return tempArr;
    }

    /**
     * 选择排序-时间复杂度O(n^2)
     * 基本思想：
     * 在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换；第二次遍历n-2个数，找到最小的数值与第二个元素交换；
     * ......
     * 第n-1次遍历，找到最小的数值与第n-1个元素交换，排序完成。
     *
     * @param arr 待排序的数组
     */
    private int[] selctionSort(int[] arr) {
        int temp;
        int[] tempArr = Arrays.copyOf(arr, arr.length);
        Log.e("XXX", "选择排序前：" + Arrays.toString(arr));

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (tempArr[j] < tempArr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                temp = tempArr[i];
                tempArr[i] = tempArr[minIndex];
                tempArr[minIndex] = temp;
            }
        }

        Log.e("XXX", "选择排序后：" + Arrays.toString(tempArr));
        return tempArr;
    }

    /**
     * 插入排序-时间复杂度O(n^2)
     * 基本思想：
     * 在要排序的一组数中，假定前n-1个数已经排好序，现在将第n个数插到前面的有序数列中，使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
     *
     * @param arr 待排序的数组
     */
    private int[] insertSort(int[] arr) {
        int temp;
        int[] tempArr = Arrays.copyOf(arr, arr.length);
        Log.e("XXX", "插入排序前：" + Arrays.toString(arr));

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (tempArr[j] < tempArr[j - 1]) {
                    temp = tempArr[j - 1];
                    tempArr[j - 1] = tempArr[j];
                    tempArr[j] = temp;
                } else { //不需要交换
                    break;
                }
            }
        }

        Log.e("XXX", "插入排序后：" + Arrays.toString(tempArr));
        return tempArr;
    }

    /**
     * 希尔排序-时间复杂度O(n^3/2)
     * 基本思想：
     * 在要排序的一组数中，根据某一增量分为若干子序列，并对子序列分别进行插入排序。然后逐渐将增量减小,并重复上述过程。直至增量为1,此时数据序列基本有序,最后进行插入排序。
     *
     * @param arr 待排序的数组
     */
    private int[] shellSort(int[] arr) {
        int temp;
        int[] tempArr = Arrays.copyOf(arr, arr.length);
        Log.e("XXX", "希尔排序前：" + Arrays.toString(arr));

        //增量
        int incre = arr.length;

        while (incre != 1) {
            //增量每次减半
            incre /= 2;
            for (int k = 0; k < incre; k++) { //根据增量分组
                for (int i = k + incre; i < arr.length; i += incre) { //对每小组的循环(即获得每小组的第1，第2个元素)
                    for (int j = i; j > k; j -= incre) { //把每个小组拉通，进行循环然后进行比较
                        if (tempArr[j] < tempArr[j - incre]) {
                            temp = tempArr[j - incre];
                            tempArr[j - incre] = tempArr[j];
                            tempArr[j] = temp;
                        }
                    }
                }
            }
        }

        Log.e("XXX", "希尔排序后：" + Arrays.toString(tempArr));
        return tempArr;
    }

    /**
     * 快速排序-时间复杂度O(N*logN)
     * 基本思想：
     * 先从数列中取出一个数作为key值；将比这个数小的数全部放在它的左边，大于或等于它的数全部放在它的右边；对左右两个小数列重复第二步，直至各区间只有1个数。
     * 参考：挖坑填坑：https://blog.csdn.net/morewindows/article/details/6684558
     *
     * @param arr   待排序的数组
     * @param left  从左边开始的基数(下标从0开始)
     * @param right 从右边开始的基数(下标从0开始)
     */
    private void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            Log.e("XXX", "传入基数错误");
            return;
        }
        Log.e("XXX", "快速排序前：" + Arrays.toString(arr));

        int i = left;
        int j = right;
        int key = arr[left]; //选择第一个数为key，即第一个坑
        while (i < j) {
            while (i < j && arr[j] >= key) { //从右向左找第一个小于key的值
                j--;
            }
            if (i < j) {
                arr[i] = arr[j]; //将tempArr[j]填到tempArr[i]中，tempArr[j]就形成了一个新的坑
                i++;
            }

            while (i < j && arr[i] <= key) { //从左往右找第一个大于key的值
                i++;
            }
            if (i < j) {
                arr[j] = arr[i]; //将tempArr[i]填到tempArr[j]中，tempArr[i]就形成了一个新的坑
                j--;
            }
        }

        //退出时，i等于j。将key填到这个坑中。
        arr[i] = key;
        quickSort(arr, left, i - 1); //递归调用
        quickSort(arr, i + 1, right); //递归调用

        Log.e("XXX", "快速排序后：" + Arrays.toString(arr));
        sortResult.setText(Arrays.toString(arr));
    }
}