class TopK {
    //创建堆
    int[] createHeap(int a[], int k) {
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = a[i];
        }
        //完全二叉树的数组表示中，下标小于等于result.length / 2 - 1才有子节点
        for (int i = result.length / 2 - 1;i >= 0;i--){
            heapify(i,result);
        }
        return result;
    }

    void heapify(int i,int[] result){
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int smallest = i;
        if (left < result.length && result[left] < result[i]){
            smallest = left;
        }
        if (right < result.length && result[right] < result[smallest]){
            smallest = right;
        }
        if (smallest == i){
            return;
        }
        else {
            int temp = result[i];
            result[i] = result[smallest];
            result[smallest] = temp;
        }
        heapify(smallest,result);
    }

    //调整堆
    void filterDown(int a[], int value) {
        a[0] = value;
        int parent = 0;

        while(parent < a.length){
            int left = 2*parent+1;
            int right = 2*parent+2;
            int smallest = parent;
            if(left < a.length && a[parent] > a[left]){
                smallest = left;
            }
            if(right < a.length && a[smallest] > a[right]){
                smallest = right;
            }
            if(smallest == parent){
                break;
            }else{
                int temp = a[parent];
                a[parent] = a[smallest];
                a[smallest] = temp;
                parent = smallest;
            }
        }
    }

    //遍历数组，并且调整堆
    int[] findTopKByHeap(int input[], int k) {
        int heap[] = this.createHeap(input, k);
        for(int i=k;i<input.length;i++){
            if(input[i]>heap[0]){
                this.filterDown(heap, input[i]);
            }

        }
        return heap;

    }

    public static void main(String[] args) {
        int a[] = { 100,101,5,4,88,89,845,45,8,4,5,8,452,1,5,8,4,5,8,4,588,44444,88888,777777,100000};
        int result[] = new TopK().findTopKByHeap(a, 5);
        for (int temp : result) {
            System.out.println(temp);
        }
    }
}