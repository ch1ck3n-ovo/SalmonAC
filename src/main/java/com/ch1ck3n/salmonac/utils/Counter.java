package com.ch1ck3n.salmonac.utils;

import java.util.LinkedList;
import java.util.Queue;

public class Counter {
    long d = 1000;
    public Counter(){}
    public Counter(long d){
        this.d = d;
    }
    private Queue<Long> ticks = new LinkedList<>();

    public int onTick() {
        this.ticks.add(Long.valueOf(System.currentTimeMillis() + d));
        return getTick();
    }

    public int getTick() {
        long time = System.currentTimeMillis();
        while (!this.ticks.isEmpty() && ((Long)this.ticks.peek()).longValue() < time)
            this.ticks.poll();
        return this.ticks.size();
    }

    public int reduceTick() {
        this.ticks.poll();
        return getTick();
    }
}
