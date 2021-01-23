package service;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;


public class DataExportCPU {

    private final OperatingSystemMXBean mxBean;

    public DataExportCPU() {
        this.mxBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();


    }
    public double loadAndGetCpuData() {
        return this.mxBean.getSystemCpuLoad() * 100;
    }


}
