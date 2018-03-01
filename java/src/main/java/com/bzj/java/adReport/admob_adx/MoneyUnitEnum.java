package com.bzj.java.adReport.admob_adx;

/**
 * 钱单位枚举,注意此枚举为货币单位，存储还是按照分存储
 * <p/>
 * User: kismetkong@tcl.com
 * Date: 2016-11-22
 * Time: 9:57
 */
public enum MoneyUnitEnum {
    //人名币
    CNY(1, "CNY"),
    //美元
    USD(2, "USD"),
    //港币
    HKD(3, "HKD"),
    //卢布
    RUB(4, "RUB");


    /**
     * 索引
     */
    private final int index;

    private final String name;

    MoneyUnitEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }


    public String getName() {
        return name;
    }

}
