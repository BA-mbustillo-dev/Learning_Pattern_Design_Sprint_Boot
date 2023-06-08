package com.moyportillo.patron_factory_method;

import com.moyportillo.impl.SQLServerDBAdapter;
import com.moyportillo.impl.SQLServerDBAdapterB;
import com.moyportillo.util.PropertiesUtil;
import java.util.Properties;

/**
 *
 * @author mbustillo
 */
public class DBFactory {
    private static final String DB_FACTORY_PROPERTY_URL = "META_INF/DBFactory.properties";
    private static final String DEFAULT_DB_CLASS_PROP = "defaultDBClass";
    
    public static IDBAdapter getDBAdapter(DBType dbType){
        switch (dbType) {
            case SQLServerA:
                return new SQLServerDBAdapter();
            case SQLServerB:
                return new SQLServerDBAdapterB();
            default:
                throw new IllegalArgumentException("No soportado");
        }
    }
    
    public static IDBAdapter getDefaultDBAdapter(){
        try {
            Properties prop = PropertiesUtil.loadProperty(DB_FACTORY_PROPERTY_URL);
            String defaultDBClass = prop.getProperty(DEFAULT_DB_CLASS_PROP);
            System.out.println("DefaultDBClass ==> " + defaultDBClass);
            return (IDBAdapter) Class.forName(defaultDBClass).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
}
