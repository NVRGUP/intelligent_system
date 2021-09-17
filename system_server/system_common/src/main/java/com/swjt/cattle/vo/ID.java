/**
 * Create By DaiRui
 * 2021/9/16
 * Version 1.0
 **/

package com.swjt.cattle.vo;

/**
 * 主键泛型
 */
public class ID<T> {
    public ID(T t) {

        this.id = t;

    }

    private T id;

    public T getID() {
        return id;
    }

    public void setID(T id) {
        this.id = id;
    }
}
