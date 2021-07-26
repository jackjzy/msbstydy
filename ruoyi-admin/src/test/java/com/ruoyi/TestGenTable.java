package com.ruoyi;

import com.ruoyi.back.domain.FfwyAbout;
import com.ruoyi.back.service.IFfwyAboutService;
import com.ruoyi.generator.config.GenConfig;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.service.IGenTableColumnService;
import com.ruoyi.generator.service.IGenTableService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成类
 * 更改表名 和生成包名就可
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestGenTable {

    @Autowired
    private IGenTableService genTableService;

    @Test
    public void test1() throws IOException {
        ArrayList<String> tables = new ArrayList<>();

        /**
         * 生成表名
         * */
        tables.add("ffwy_pay_refund");
        /**
         * 生成包名
         * */
        GenConfig.setPackageNamePath("com.ruoyi.system.controller.payRefund");

        /**
         *
         * 删除原先复制的表结构信息
         * */
//        for (String tableName : tables) {
//            GenTable genTable = new GenTable();
//            genTable.setTableName(tableName);
//            Object[] TableIds = genTableService.selectGenTableList(genTable).stream().map(tab -> tab.getTableId())
//                    .toArray();
//            Long[] longs = new Long[TableIds.length];
//            for(int i =0;i<TableIds.length;i++){
//                longs[i]=Long.valueOf(TableIds[i].toString());
//            }
//            System.err.println(longs);
//            genTableService.deleteGenTableByIds(longs);
//        }
        //查询表名
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tables.toArray(new String[tables.size()]));
        //引入表名
        genTableService.importGenTable(tableList);
        //生成表名
        tableList.stream().forEach(table -> genTableService.generatorCode(table.getTableName()));
    }


}
