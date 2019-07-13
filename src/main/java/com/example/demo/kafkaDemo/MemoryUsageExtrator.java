package com.example.demo.kafkaDemo;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * MemoryUsageExtrator类（很简单的工具类，提取当前可用内存字节数）
 */
public class MemoryUsageExtrator {

    private static OperatingSystemMXBean mxBean =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    /**
     * Get current free memory size in bytes
     * @return  free RAM size
     */
    public static long currentFreeMemorySizeInBytes() {
        /*return mxBean.getFreePhysicalMemorySize();*/
        return  1000L;
    }
}
