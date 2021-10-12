package com.github.stephenenright.callcenter.domain.comparator;

import com.github.stephenenright.callcenter.domain.entity.PhoneCall;

import java.util.Comparator;

public class PhoneCallComparator implements Comparator<PhoneCall> {

    public static Comparator<PhoneCall> MAX_COMPARATOR = new PhoneCallComparator();

    @Override
    public int compare(PhoneCall first, PhoneCall second) {
        return Integer.compare(first.getPriority(), second.getPriority());
    }
}
