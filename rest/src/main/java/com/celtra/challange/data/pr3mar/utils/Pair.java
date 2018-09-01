package com.celtra.challange.data.pr3mar.utils;

public class Pair<L,R> {

    private final L key;
    private final R value;

    public Pair(L left, R value) {
        this.key = left;
        this.value = value;
    }

    public L getLeft() { return key; }
    public R getRight() { return value; }

    @Override
    public int hashCode() { return key.hashCode() ^ value.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair pairo = (Pair) o;
        return this.key.equals(pairo.getLeft()) &&
                this.value.equals(pairo.getRight());
    }

}
