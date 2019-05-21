package com.wanlin.util;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源动态切换 TODO 也可用aspectj + 注解方式 切换数据源，减少耦合
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 数据库主库
     */
    public static final String DATASOURCE_MASTER = "dataSource1";
    /**
     * 数据库从库
     */
    public static final String DATASOURCE_SALVE = "dataSource2";

    //本地线程，获取当前正在执行的currentThread
    public static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {

        contextHolder.set(customerType);

    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return this.getCustomerType();
    }
}
