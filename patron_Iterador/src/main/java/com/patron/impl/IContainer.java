
package com.patron.impl;

/**
 *
 * @author mbustillo
 */
public interface IContainer<T> {
    public IIterator<T> createIterator();
}
