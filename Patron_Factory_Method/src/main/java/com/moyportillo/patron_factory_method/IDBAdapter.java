
package com.moyportillo.patron_factory_method;

import java.sql.Connection;

/**
 *
 * @author mbustillo
 */
public interface IDBAdapter {
    public Connection getConnection();
}
