package com.ruoyi.system.domain.vo;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.Model;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.product.FfwyProductSku;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/5/11
 **/
public class SpecificationSort {

    public static final String SEPARATOR = ";";
    public static final String SELECTVALUE = "selectValue";
    public static final String SKU = "sku";

    /**
     *
     * @param map   {颜色=[红色, 绿色, 黑色], 尺码=[大码, 小码], 类型=[上衣, 裤子]}
     *
     * @return
     * [{"尺码":"大码","类型":"上衣","颜色":"红色"},
     * {"尺码":"大码","类型":"上衣","颜色":"绿色"},
     * {"尺码":"大码","类型":"上衣","颜色":"黑色"},
     * {"尺码":"大码","类型":"裤子","颜色":"红色"},
     * {"尺码":"大码","类型":"裤子","颜色":"绿色"},
     * {"尺码":"大码","类型":"裤子","颜色":"黑色"},
     * {"尺码":"小码","类型":"上衣","颜色":"红色"},
     * {"尺码":"小码","类型":"上衣","颜色":"绿色"},
     * {"尺码":"小码","类型":"上衣","颜色":"黑色"},
     * {"尺码":"小码","类型":"裤子","颜色":"红色"},
     * {"尺码":"小码","类型":"裤子","颜色":"绿色"},
     * {"尺码":"小码","类型":"裤子","颜色":"黑色"}]
     *
     */
    public static List<JSONObject> specificationMapAscendJSON(Map<String,List<String>> map){
        List<JSONObject> jsonList = new ArrayList<>();
        List<List<Model>> lists = specificationMapAscend(map);
        for (List<Model> list : lists) {
            String selectValue="";
            JSONObject json = new JSONObject(new LinkedHashMap<>());
            for (Model model : list) {
                json.put(model.getName(),model.getAuthor());
                if (StringUtils.isEmpty(selectValue)){
                    selectValue = model.getAuthor();
                }else {
                    selectValue = selectValue+";"+model.getAuthor();
                }
            }
            json.put(SELECTVALUE,selectValue);
            jsonList.add(json);
        }

        return jsonList;
    }


    /**
     *  商品所有规格属性排序
     * @param map
     * @return
     */
    private static List<List<Model>> specificationMapAscend(Map<String,List<String>> map){
        Map<String, Integer> mapInt = new HashMap<>();
        List<Model> models = new ArrayList<>();
        map.forEach((k,v) -> {
            mapInt.put(k,v.size());
        });

        int countType = 1;
        Map<String, Integer> stringIntegerMap = sortAscend(mapInt);
        for (Map.Entry<String, Integer> stringIntegerEntry : stringIntegerMap.entrySet()) {
            String k = stringIntegerEntry.getKey();
            List<String> list = map.get(k);
            for (String s : list) {
                models.add(new Model(k,s,countType));
            }
            countType ++;
        }

        return getCP(models);
    }

    /**
     * Map的value值降序排序
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    private static <K, V extends Comparable<? super V>> Map<K, V> sortDescend(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return -compare;
            }
        });

        Map<K, V> returnMap = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            returnMap.put(entry.getKey(), entry.getValue());
        }
        return returnMap;
    }

    /**
     * Map的value值升序排序
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    private static <K, V extends Comparable<? super V>> Map<K, V> sortAscend(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>(map.entrySet());
        list.sort(new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> returnMap = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            returnMap.put(entry.getKey(), entry.getValue());
        }
        return returnMap;
    }



    /**
     * Discription: 笛卡尔乘积算法
     * 把一个List{[1,2],[A,B],[a,b]} 转化成
     * List{[1,A,a],[1,A,b],[1,B,a],[1,B,b],[2,A,a],[2,A,b],[2,B,a],[2,B,b]} 数组输出
     *
     * @param dimensionValue
     *              原List
     * @param result
     *              通过乘积转化后的数组
     * @param layer
     *              中间参数
     * @param currentList
     *               中间参数
     */
    private static<T> void descartes(List<List<T>> dimensionValue, List<List<T>> result,
                                    int layer, List<T> currentList) {
        if (layer < dimensionValue.size() - 1) {
            if (dimensionValue.get(layer).size() == 0) {
                descartes(dimensionValue, result, layer + 1, currentList);
            } else {
                for (int i = 0; i < dimensionValue.get(layer).size(); i++) {
                    List<T> list = new ArrayList<T>(currentList);
                    list.add(dimensionValue.get(layer).get(i));
                    descartes(dimensionValue, result, layer + 1, list);
                }
            }
        } else if (layer == dimensionValue.size() - 1) {
            if (dimensionValue.get(layer).size() == 0) {
                result.add(currentList);
            } else {
                for (int i = 0; i < dimensionValue.get(layer).size(); i++) {
                    List<T> list = new ArrayList<T>(currentList);
                    list.add(dimensionValue.get(layer).get(i));
                    result.add(list);
                }
            }
        }
    }


    private static List<List<Model>> getCP(List<Model> tList){
        // 按指定字段（type）分组
        Map<Integer, List<Model>> modelMap = tList.stream().collect(Collectors.groupingBy(Model::getType));
        Collection<List<Model>> mapValues = modelMap.values();
        List<List<Model>> dimensionValue = new ArrayList<>(mapValues);	// 原List

        List<List<Model>> result = new ArrayList<>(); // 返回集合
        SpecificationSort.descartes(dimensionValue, result, 0, new ArrayList<Model>());
        return result;
    }

    /**
     * 商品规格组合排序
     * @param map
     * @param productId
     * @return
     */
    public static List<SpeVO> specificationMapAscendTree(Map<String,List<String>> map,Long productId){
        Map<String, Integer> mapInt = new HashMap<>();
        Map<String,List<String>> newMap = new LinkedHashMap<>();
        //将数组合并成一个数组
        List<List> totalArr = new ArrayList<>();

        map.forEach((k,v) -> {
            mapInt.put(k,v.size());
        });

        Map<String, Integer> stringIntegerMap = sortAscend(mapInt);
        for (Map.Entry<String, Integer> stringIntegerEntry : stringIntegerMap.entrySet()) {
            String k = stringIntegerEntry.getKey();
            List<String> list = map.get(k);
            List<SpeVO> speVOS = new ArrayList<>();
            for (String s : list) {
                speVOS.add(new SpeVO(productId,k,s));
            }
            totalArr.add(speVOS);
        }

        return descartesTree(totalArr);
    }

    public static void selectSpeVO(SpeVO speVOS,String valueSelect,List<FfwyProductSku> skuList){
        if (speVOS.getChildSpe() == null){
            speVOS.setIsEnd(true);
            speVOS.setProductSku(contrastValue(speVOS.getValueSelect(),skuList,null));
        }else {
            List<SpeVO> childSpe = speVOS.getChildSpe();
            for (SpeVO speVO : childSpe) {
                speVO.setValueSelect(valueSelect + SEPARATOR +speVO.getSpeValue());
                selectSpeVO(speVO,speVO.getValueSelect(),skuList);
            }
        }
    }


    /**
     * 返回规格所对应的商品sku
     * @param valueSelect
     * @param skuList
     * @return
     */
    public static FfwyProductSku contrastValue(String valueSelect,List<FfwyProductSku> skuList,Long pId){
        List<String> asList = Arrays.asList(valueSelect.split(SEPARATOR));
        for (FfwyProductSku ffwyProductSku : skuList) {
            List<String> list = Arrays.asList(ffwyProductSku.getSkuSpec().split(SEPARATOR));
            if (asList.size() == list.size()){
                if (asList.containsAll(list)){
                    return ffwyProductSku;
                }
            }
        }
        return new FfwyProductSku(null,pId,"","","",new BigDecimal("0.0"),0L,0L,"");
    }

    private static SpeVO recursionNewSpeVo(SpeVO speVOS){
        SpeVO newSpe = new SpeVO();
        newSpe.setSpeName(speVOS.getSpeName());
        newSpe.setSpeValue(speVOS.getSpeValue());
        newSpe.setProductId(speVOS.getProductId());

        if (speVOS.getChildSpe() == null || speVOS.getChildSpe().size() == 0){
            return newSpe;
        }else {
            List<SpeVO> childSpe = speVOS.getChildSpe();
            List<SpeVO> newChildSpe = new ArrayList<>();
            for (SpeVO speVO : childSpe) {
                SpeVO speVO1 = recursionNewSpeVo(speVO);
                newChildSpe.add(speVO1);
            }
            newSpe.setChildSpe(newChildSpe);

            return newSpe;
        }
    }

    private static List<SpeVO> descartesTree(List<List> totalArr){
        //转换后的结果集
        List<SpeVO> mergeArr = new ArrayList<>();

        //倒序循环（目前是为了简单，将第一次循环的结果集放入下一次循环的结果集中，也就是所谓的子数据放入父节点中）
        for(int i=totalArr.size() - 1;i>=0;i--){
            if(i-1>=0){
                //有父节点，需要将本次循环所得数组放入下一次循环中
                List<SpeVO> nextArr = totalArr.get(i-1);//获取下一次循环的数组集合（就是arr2）
                List<SpeVO> tmpArr = new ArrayList<>();
                for(int j=0;j<nextArr.size();j++){//循环遍历arr2的每一项
                    SpeVO obj = new SpeVO();
                    obj.setSpeName(nextArr.get(j).getSpeName());
                    obj.setProductId(nextArr.get(j).getProductId());
                    obj.setSpeValue(nextArr.get(j).getSpeValue());
                    if(mergeArr!=null && mergeArr.size()>0){
                        //如果mergeArr不为空，表示之前已经遍历过一次，直接赋值到父节点下的每一项中即可
                        //给父节点下的每一项创建不同引用对象，防止之后因为用同一引用对象而无法区分修改
                        List<SpeVO> newArr = new ArrayList<>();
                        for (SpeVO speVO : mergeArr) {
                            SpeVO newSpe = recursionNewSpeVo(speVO);
                            if (newSpe != null){
                                newArr.add(newSpe);
                            }
                        }
                        obj.setChildSpe(newArr);
//                        obj.setChildSpe(mergeArr);
                    }else{
                        //如果是第一次遍历，需要对最外层的arr3进行拆除，拆分成多个数组的形式。
                        List<SpeVO> firstArr = new ArrayList<>();
                        for(int m=0;m<totalArr.get(i).size();m++){
                            SpeVO tmpObj = new SpeVO();
                            SpeVO speVO=(SpeVO)totalArr.get(i).get(m);
                            tmpObj.setSpeName(speVO.getSpeName());
                            tmpObj.setSpeValue(speVO.getSpeValue());
                            tmpObj.setProductId(speVO.getProductId());
                            firstArr.add(tmpObj);
                        }
                        obj.setChildSpe(firstArr);
                    }
                    tmpArr.add(obj);
                }
                mergeArr = tmpArr;
            }else{
                //无父节点的情况下，需要判断是否是第一次循环。
                //如果是第一次循环，证明只有一组数据，无父子节点
                if(totalArr.size()-1==i){
                    for(int j=0;j<totalArr.get(i).size();j++){
                        SpeVO obj = new SpeVO();
                        SpeVO speVO=(SpeVO)totalArr.get(i).get(j);
                        obj.setSpeName(speVO.getSpeName());
                        obj.setSpeValue(speVO.getSpeValue());
                        obj.setProductId(speVO.getProductId());
                        mergeArr.add(obj);
                    }
                }else{
                    //非第一次循环，笛卡尔积计算完毕
                    break;
                }
            }
        }

        return mergeArr;
    }

    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("红色");
        list.add("绿色");
        list.add("黑色");
        map.put("颜色", list);
        list = new ArrayList<>();
        list.add("大码");list.add("小码");
//        list.add("X");list.add("S");list.add("M");list.add("L");
        map.put("尺码", list);
        list = new ArrayList<>();
        list.add("上衣");
        list.add("裤子");
        map.put("类型", list);

        System.out.println("----------------------------------specificationMapAscend-------------------------------------");
        Map<String ,String > stringMap = null;
        List<Map<String ,String >> mapList = new ArrayList<>();
        List<JSONObject> jsonList = new ArrayList<>();
        List<List<Model>> lists = specificationMapAscend(map);
        for (List<Model> modelList : lists) {
            stringMap = new TreeMap<>();
            JSONObject jsonObject = new JSONObject(new LinkedHashMap<>());
            for (Model model : modelList) {
//                resList.add(model);
//                System.out.println(model);
                stringMap.put(model.getName(),model.getAuthor());
                jsonObject.put(model.getName(),model.getAuthor());
            }
            jsonObject.put("sku",new FfwyProductSku(1l,2l,"手机","这是一部手机","一部很牛的手机"));
            jsonList.add(jsonObject);
            mapList.add(stringMap);
//            System.out.println(modelList.toString());
        }

        System.out.println("----------------------------------specificationMapAscendTree-------------------------------------");
        for (Map<String, String> stringStringMap : mapList) {
            System.out.println(stringStringMap+","+new FfwyProductSku(1l,2l,"手机","这是一部手机","一部很牛的手机"));
        }

        System.out.println("----------------------------------specificationMapAscendTree-------------------------------------");

        for (JSONObject json : jsonList) {
            System.out.println(json);
        }
        System.err.println(jsonList);

//        List<SpeVO> speVOS = specificationMapAscendTree(map, 1L);
//        for (SpeVO speVO : speVOS) {
//            selectSpeVO(speVO,speVO.getSpeValue(),null);
//        }

    }
}


