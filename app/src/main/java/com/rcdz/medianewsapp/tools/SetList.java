package com.rcdz.medianewsapp.tools;

import java.util.LinkedList;

/**
 * 不重复的list
 * @author:create by
 * 邮箱 983049539@qq.com
 */
public class SetList<T> extends LinkedList<T> {
    private static final long serialVersionUID = 1434324234L;

    @Override
    public boolean add(T object) {
        if (size() == 0) {
            return super.add(object);
        } else {
            int count = 0;
            for (T t : this) {
                if (t.equals(object)) {
                    count++;
                    break;
                }
            }
            if (count == 0) {
                return super.add(object);
            } else {
                return false;
            }
        }
    }
}
