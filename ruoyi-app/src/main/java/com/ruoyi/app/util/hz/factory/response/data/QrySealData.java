package com.ruoyi.app.util.hz.factory.response.data;

import com.ruoyi.app.util.hz.factory.response.other.Seals;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 18:20
 * @version 
 */
public class QrySealData {
    private Seals seals;
    private int total;

    public Seals getSeals() {
        return seals;
    }

    public void setSeals(Seals seals) {
        this.seals = seals;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
