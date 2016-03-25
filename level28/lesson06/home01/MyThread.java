package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Влад on 12.02.2016.
 */
public class MyThread extends Thread
{
    private static AtomicInteger priority = new AtomicInteger(0);

    public MyThread()
    {
        meSetPriority();
    }

    public MyThread(Runnable target)
    {
        super(target);
        meSetPriority();
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        meSetPriority();
    }

    public MyThread(String name)
    {
        super(name);
        meSetPriority();
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        meSetPriority();
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        meSetPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        meSetPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        meSetPriority();
    }

    private void meSetPriority() {
        priority.incrementAndGet();
        priority.compareAndSet(11, 1);
        int newPriority = priority.get();
        if (getThreadGroup() != null) {
            if (getThreadGroup().getMaxPriority() < newPriority) {
                newPriority = getThreadGroup().getMaxPriority();
            }
        }
        setPriority(newPriority);
    }
}
