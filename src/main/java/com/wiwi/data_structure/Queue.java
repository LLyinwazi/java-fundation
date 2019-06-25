package com.wiwi.data_structure;

import com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV;

/**
 * Create by Wisya on 2019/6/24 10:37 <p>
 * <p> 队列, 是一种有序列表, 实际应用参考银行取号系统。
 * Description:
 * @since
 */
public class Queue {

    public static void main(String[] args) {
        CircularQueueTest();
    }

    private static void CircularQueueTest() {
        CircularQueue<String> queue = new CircularQueue<String>(3);
        queue.add("A");
        queue.add("B");
        queue.add("C");
        queue.add("D");
        System.out.println(queue);
        System.out.println("get:" + queue.get());
//        System.out.println("get:" + queue.get());
//        System.out.println("get:" + queue.get());
//        System.out.println("get:" + queue.get());
        queue.add("E");
//        queue.add("F");
//        queue.add("G");
//        queue.add("H");
        System.out.println(queue);
        System.out.println("get:" + queue.get());
        System.out.println("get:" + queue.get());
        System.out.println(queue);
        queue.add("H");
        queue.add("I");
        queue.add("J");
        System.out.println(queue);
    }

    private static void ArrayQueueTest() {
        ArrayQueue<String> queue = new ArrayQueue<String>(5);
        queue.add("A");
        queue.add("B");
        queue.add("C");
        queue.add("D");
        queue.add("E");
        queue.add("F");
        System.out.println(queue);
        String e;
        while ((e = queue.get()) != null) {
            System.out.println("get:" + e);
        }
    }

}

/**
 * 使用数组实现简单队列(FIFO):
 * @param <T>
 */
class ArrayQueue<T> {

    private int front; //队列头
    private int rear; // 队列尾
    private int maxSize; // 队列最大长度
    private Object[] arr; // 队列的容器:数组

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        arr = new Object[maxSize];
    }

    /**
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 队列添加元素
     * @param e
     * @return
     */
    public boolean add(T e) {
        if (isFull()) {
            System.out.println("数组已满, 添加失败.");
            return false;
        }
        // 尾部添加
        arr[++rear] = e;
        return true;
    }

    /**
     * 从队列获取元素
     * @param
     * @return
     */
    public T get() {
        if (isEmpty()) {
            return null;
        }
        // 从头部获取
        return (T) arr[++front];
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        // 不需要全部遍历
        for (int i = 0; i <= rear; i++) {
            // 逗号分隔各个元素
            builder.append(arr[i]).append(", ");
        }
        if (rear > 0) {
            // 删除最后一个逗号.
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("]");
        return builder.toString();
    }

    // 迭代器, 略

}

/**
 * 环形队列
 * @param <T>
 */
class CircularQueue<T> {

    private int front;
    private int rear;
    private int maxSize;
    private Object[] arr;

    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
        front = 0; // 队列头  指向头实际位置
        rear = 0;  // 队列尾  指向尾实际位置
        arr = new Object[maxSize];
    }

    /**
     * 会浪费最末尾一个空间.即实际的队列容量是 maxSize-1
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean add(T e) {
        if (isFull()) {
            System.out.println(String.format("队列已满, 添加[%s]失败.", e));
            return false;
        }
        // 尾部添加
        arr[rear] = e;
        // 形成环
        rear = (rear + 1) % (maxSize);
        // 非原子操作,线程不安全
        return true;
    }

    public T get() {
        if (isEmpty()) {
            return null;
        }
        // 取当前结果
        T result = (T) arr[front];
        arr[front] = null;
        // 头 +1
        front = (front + 1) % maxSize;
        return result;
    }

    //打印队列
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < this.maxSize; i++) {
            builder.append(arr[i]).append(", ");
        }
        if (builder.length() > 2) {
            // 去除尾部 ", "
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("]");
        return builder.toString();
    }

    // 迭代器:略

}






