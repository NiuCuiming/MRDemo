package com.anu.logmr.utils;

import org.apache.hadoop.io.RawComparator;

public class GroupComparator implements RawComparator<LogBean> {

    @Override
    public int compare(byte[] bytes, int i, int i1, byte[] bytes1, int i2, int i3) {
        return 0;
    }

    @Override
    public int compare(LogBean o1, LogBean o2) {
        return o1.getUser().compareTo(o2.getUser());
    }
}
