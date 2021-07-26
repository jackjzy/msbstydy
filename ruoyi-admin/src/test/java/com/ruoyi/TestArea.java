package com.ruoyi;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.system.service.IFfwyAreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestArea {


    @Autowired
    IFfwyAreaService areaService;

//    @Test
//    public void test2(){
//        List<FfwyArea> ffwyAreas = areaService.selectFfwyAreaList(null);
//
//        List<FfwyArea> FfwyAreaOne = ffwyAreas.stream().filter(ffwyArea -> ffwyArea.getPerentCode().equals("0")).collect(Collectors.toList());
//
//        FfwyAreaOne.forEach(ffwyAreaOne -> ffwyAreaOne.setFfwyAreaList(getChildren( ffwyAreas, ffwyAreaOne.getCode())));
//
//        System.err.println(JSONObject.toJSONString(FfwyAreaOne));
//    }
//
//    public List<FfwyArea> getChildren(List<FfwyArea> ffwyAreaList,String code){
//
//        List<FfwyArea>  collect = ffwyAreaList.stream().filter(ffwyArea -> ffwyArea.getPerentCode().equals(code)).collect(Collectors.toList());
//                        collect.stream().forEach(ffwyAreaThree -> ffwyAreaThree.setFfwyAreaList(ffwyAreaList.stream().filter
//                                (ffwyArea -> ffwyArea.getPerentCode().equals(ffwyAreaThree.getCode())).collect(Collectors.toList())));
//        return collect;
//    }
}
