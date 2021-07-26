package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.config.EqbConfig;
import com.ruoyi.common.enums.ContractCartType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.cos.CosCofig;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.system.domain.FfwyContract;
import com.ruoyi.system.domain.FfwyWorkplan;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.combomealorders.*;
import com.ruoyi.system.domain.vo.FfwyOrderCombomealVo;
import com.ruoyi.system.mapper.FfwyContractMapper;
import com.ruoyi.system.mapper.FfwyWorkplanMapper;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.combomealorders.FfwyMaterialMapper;
import com.ruoyi.system.mapper.combomealorders.FfwyOrderCombomealMapper;
import com.ruoyi.system.mapper.combomealorders.FfwyOrderCombomealPlanMapper;
import com.ruoyi.system.mapper.combomealorders.FfwyPhaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.service.IFfwyOrderCombomealService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
@Service
public class FfwyOrderCombomealServiceImpl implements IFfwyOrderCombomealService 
{
    @Autowired
    private FfwyOrderCombomealMapper ffwyOrderCombomealMapper;

    @Autowired
    private FfwyOrderCombomealPlanMapper ffwyOrderCombomealPlanMapper;
    @Autowired
    private FfwyMaterialMapper ffwyMaterialMapper;
    @Autowired
    private FfwyPhaseMapper ffwyPhaseMapper;
    @Autowired
    private FfwyUserMapper ffwyUserMapper;
    @Autowired
    private FfwyContractMapper ffwyContractMapper;


    @Autowired
    private FfwyWorkplanMapper workplanMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param orderId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyOrderCombomeal selectFfwyOrderCombomealById(Long orderId)
    {
        FfwyOrderCombomeal ffwyOrderCombomeal = ffwyOrderCombomealMapper.selectFfwyOrderCombomealById(orderId);

        ffwyOrderCombomeal.setDesignerPhoto(CosCofig.getPrefixUrl()+ffwyOrderCombomeal.getDesignerPhoto());
        ffwyOrderCombomeal.setWorkerPhoto(CosCofig.getPrefixUrl()+ffwyOrderCombomeal.getWorkerPhoto());
        ffwyOrderCombomeal.setCombomealCover(CosCofig.getPrefixUrl()+ffwyOrderCombomeal.getCombomealCover());
        return ffwyOrderCombomeal;
    }

    @Override
    public FfwyOrderCombomeal selectFfwyOrderCombomealByOrderNumber(String orderNumber) {
        return ffwyOrderCombomealMapper.selectFfwyOrderCombomealByOrderNumber(orderNumber);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyOrderCombomeal 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyOrderCombomeal> selectFfwyOrderCombomealList(FfwyOrderCombomeal ffwyOrderCombomeal)
    {
        List<FfwyOrderCombomeal> orderCombomeals = ffwyOrderCombomealMapper.selectFfwyOrderCombomealList(ffwyOrderCombomeal);
        orderCombomeals.forEach(ffwyOrderCombomeal1 -> {
            ffwyOrderCombomeal1.setCombomealCover(CosCofig.getPrefixUrl()+ffwyOrderCombomeal1.getCombomealCover());
            ffwyOrderCombomeal1.setDesignerPhoto(CosCofig.getPrefixUrl()+ffwyOrderCombomeal1.getDesignerPhoto());
        });
        return orderCombomeals;
    }

    /**
     * 查询【所有订单】列表PC
     *
     * @param ffwyOrderCombomealVo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyOrderCombomeal> selectFfwyOrderCombomealListPC(FfwyOrderCombomealVo ffwyOrderCombomealVo)
    {
        List<FfwyOrderCombomeal> ffwyOrderCombomeals = null;
        if(!StringUtils.isEmpty(ffwyOrderCombomealVo)) {
            ffwyOrderCombomeals = ffwyOrderCombomealMapper.selectFfwyOrderCombomealListPC(ffwyOrderCombomealVo);
        }
        if(StringUtils.isEmpty(ffwyOrderCombomealVo)){
            ffwyOrderCombomeals = ffwyOrderCombomealMapper.selectFfwyOrderCombomealListPC(ffwyOrderCombomealVo);
        }
        return ffwyOrderCombomeals;
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyOrderCombomeal 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyOrderCombomeal(FfwyOrderCombomeal ffwyOrderCombomeal)
    {
        ffwyOrderCombomeal.setCreateTime(DateUtils.getNowDate());
        return ffwyOrderCombomealMapper.insertFfwyOrderCombomeal(ffwyOrderCombomeal);
    }

    @Override
    public int insertFfwyOrder( Materials materials)
    {
        //生成套餐订单
        ffwyOrderCombomealMapper.updateFfwyOrderCombomeal(materials.getFfwyOrderCombomeal());
        //获取生成套餐订单id 生成套餐订单材料
        List<FfwyMaterial> materials1 = materials.getMaterials();
        materials1.forEach(ffwyMaterial -> {
            ffwyMaterial.setOrderCombomealId(materials.getFfwyOrderCombomeal().getOrderId());
             ffwyMaterialMapper.insertFfwyMaterial(ffwyMaterial);

        });
        //获取套餐订单id 生成套餐订单节点以及子节点
        List<FfwyPhase> phases1 = materials.getPhases();
        //新建父节点
        phases1.forEach(ffwyPhase -> {
            //添加订单id
            ffwyPhase.setOrderId(materials.getFfwyOrderCombomeal().getOrderId());
            //如果等级为1 则设置用户端状态为 支付 否则设置用户状态为待进行
            if (ffwyPhase.getLevel().equals(1)){
                ffwyPhase.setPhaseStatusUser("0");
            }else {
                ffwyPhase.setPhaseStatusUser("2");
            }
            //设置工长端为待进行
            ffwyPhase.setPhaseStatus("2");
            ffwyPhaseMapper.insertFfwyPhase(ffwyPhase);


            //新建子节点
            List<FfwyPhase> ffwyPhaseList = ffwyPhase.getFfwyPhaseList();
            if (null!=ffwyPhaseList){
                ffwyPhaseList.forEach(ffwyPhase1 -> {
                    ffwyPhase1.setPhaseStatusUser("2");
                    ffwyPhase1.setPhaseStatus("2");
                    ffwyPhase1.setOrderId(materials.getFfwyOrderCombomeal().getOrderId());
                    ffwyPhase1.setParentId(ffwyPhase.getPhaseId());
                    ffwyPhaseMapper.insertFfwyPhase(ffwyPhase1);
                });
            }

        });
        return 1;
    }

    @Override
    public int createOrder(Long orderId, String designerName, MultipartFile photo, List<MultipartFile> phtotos,String phaseStr,MultipartFile doc) {
        String path=null;
        int i =0;
        if (!photo.isEmpty()){
            path = CosUtil.CosUpload(photo);
        }
        FfwyContract ffwyContract=null;
        FfwyOrderCombomeal ffwyOrderCombomeal1 = ffwyOrderCombomealMapper.selectFfwyOrderCombomealById(orderId);
        //上传合同
        if (!doc.isEmpty()){
            String s = CosUtil.CosUploadtest(doc);
            EqbConfig eqbConfig = new EqbConfig();
            ffwyContract = new FfwyContract();
            ffwyContract.setFilePhas(CosCofig.getPrefixUrl()+s);
            //创建个人账号id
            ffwyContract.setThirdPartyUserIdPsn(ffwyOrderCombomeal1.getUserId().toString());
            //合同所属的用户
            ffwyContract.setUserId(ffwyOrderCombomeal1.getUserId());
            //签署合同的用户姓名（真实姓名）
            ffwyContract.setNamePsn(ffwyOrderCombomeal1.getOrderUserName());
            //用户证件类型（默认身份证）
            ffwyContract.setIdTypePsn(ContractCartType.IDTYPEPSN1.getType());
            //用户证件号
            ffwyContract.setIdNumberPsn(ffwyOrderCombomeal1.getIdCard());
            //用户手机号
            ffwyContract.setMobilePsn(ffwyOrderCombomeal1.getOrderPhone());
            //创建平台账号id
            ffwyContract.setThirdPartyUserIdOrg(eqbConfig.getIdNumberOrg());
            //平台名称
            ffwyContract.setNameOrg("北京嗅美科技有限公司");
            //平台证件类型
            ffwyContract.setIdTypeOrg(ContractCartType.IDTYPEORG1.getType());

            ffwyContract.setIdNumberOrg(eqbConfig.getIdNumberOrg());

            ffwyContractMapper.insertFfwyContract(ffwyContract);

        }
        //升级量房订单
        FfwyOrderCombomeal ffwyOrderCombomeal = new FfwyOrderCombomeal();
        ffwyOrderCombomeal.setOrderId(orderId);
        ffwyOrderCombomeal.setDesignerName(designerName);
        ffwyOrderCombomeal.setDesignerPhoto(path);
        ffwyOrderCombomeal.setOrderType(ffwyOrderCombomeal1.getCombomealType());
        //判断是否为毛培房套餐、是毛培房套餐则添加合同
        if (ffwyOrderCombomeal1.getCombomealType().equals("1")&& !ObjectUtil.isEmpty(ffwyContract)){
            ffwyOrderCombomeal.setContractId(ffwyContract.getContractId());
        }
        i+=ffwyOrderCombomealMapper.updateFfwyOrderCombomeal(ffwyOrderCombomeal);
        FfwyOrderCombomealPlan ffwyOrderCombomealPlan = new FfwyOrderCombomealPlan();
        //删除原来的设计图片
        FfwyOrderCombomealPlan ffwyOrderCombomealPlan1 = new FfwyOrderCombomealPlan();
        ffwyOrderCombomealPlan1.setOrderId(orderId);
        List<FfwyOrderCombomealPlan> combomealPlans = ffwyOrderCombomealPlanMapper.selectFfwyOrderCombomealPlanList(ffwyOrderCombomealPlan1);
        if (null!=combomealPlans&&combomealPlans.size()>0){
            combomealPlans.forEach(ffwyOrderCombomealPlan2 -> {
                CosUtil.deletePhoto(ffwyOrderCombomealPlan2.getPhotoPhath());
                ffwyOrderCombomealPlanMapper.deleteFfwyOrderCombomealPlanById(ffwyOrderCombomealPlan2.getPhotoId());
            });
        }


        //添加新的设计图片
        if (null!=phtotos&&phtotos.size()>0){
            for (MultipartFile phtoto : phtotos) {
                String s = CosUtil.CosUpload(phtoto);

                ffwyOrderCombomealPlan.setOrderId(orderId);
                ffwyOrderCombomealPlan.setPhotoPhath(s);
                i+=ffwyOrderCombomealPlanMapper.insertFfwyOrderCombomealPlan(ffwyOrderCombomealPlan);

            }
        }

        FfwyPhase ffwyPhase = new FfwyPhase();

        //为每个节点设置比例
        String[] split = phaseStr.split("/");
        for (String s : split) {
            String[] phases = s.split(":");
            ffwyPhase.setPhaseId(Long.valueOf(phases[0]));
            ffwyPhase.setPayMoney(new BigDecimal(phases[1]));
            ffwyPhaseMapper.updateFfwyPhase(ffwyPhase);
        }


        return i;
    }

    @Override
    public int addDesignerPhotos(Long orderId,List<MultipartFile> files) {
        int i=0;
        FfwyOrderCombomealPlan ffwyOrderCombomealPlan = new FfwyOrderCombomealPlan();

        if (null!=files&&files.size()>0){
            for (MultipartFile phtoto : files) {
                if (!phtoto.isEmpty()){
                    String s = CosUtil.CosUpload(phtoto);
                    ffwyOrderCombomealPlan.setOrderId(orderId);
                    ffwyOrderCombomealPlan.setPhotoPhath(s);
                    ffwyOrderCombomealPlan.setCreateTime(DateUtils.getNowDate());
                    i+=ffwyOrderCombomealPlanMapper.insertFfwyOrderCombomealPlan(ffwyOrderCombomealPlan);
                }


            }
        }
        return i;
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyOrderCombomeal 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyOrderCombomeal(FfwyOrderCombomeal ffwyOrderCombomeal)
    {
        return ffwyOrderCombomealMapper.updateFfwyOrderCombomeal(ffwyOrderCombomeal);
    }

    /**
     * 修改【分配工长】
     *
     * @param ffwyOrderCombomeal 【请填写功能名称】
     * @return 结果
     */
    public Map<String, Object> updateFfwyOrderCombomealAllocation(FfwyOrderCombomeal ffwyOrderCombomeal) {

        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("code", 0);

        FfwyOrderCombomeal ffwyOrderCombomealTemp = ffwyOrderCombomealMapper.selectFfwyOrderCombomealById(ffwyOrderCombomeal.getOrderId());
        if (null == ffwyOrderCombomealTemp) {
            dataMap.put("resmsg", "订单不存在！");
            return dataMap;
        } else {
            if (null != ffwyOrderCombomealTemp.getWorkerId() && !"".equals(ffwyOrderCombomealTemp.getWorkerId())) {
                dataMap.put("resmsg", "已分配工长！");
                return dataMap;
            }
        }

        FfwyUser ffwyUser = ffwyUserMapper.selectFfwyUserById(ffwyOrderCombomeal.getWorkerId());
        if (null == ffwyUser) {
            dataMap.put("resmsg", "工长不存在！");
            return dataMap;
        }

        if (null == ffwyOrderCombomeal.getBeginTime()) {
            dataMap.put("resmsg", "项目开始时间不能为空！");
            return dataMap;
        }
        if (null == ffwyOrderCombomeal.getEndTime()) {
            dataMap.put("resmsg", "项目结束时间不能为空！");
            return dataMap;
        }

        ffwyOrderCombomealTemp.setWorkerId(ffwyUser.getUserId());
        ffwyOrderCombomealTemp.setWorkerName(ffwyUser.getUserName());
        ffwyOrderCombomealTemp.setBeginTime(ffwyOrderCombomeal.getBeginTime());
        ffwyOrderCombomealTemp.setEndTime(ffwyOrderCombomeal.getEndTime());

        int insertCode = ffwyOrderCombomealMapper.updateFfwyOrderCombomeal(ffwyOrderCombomealTemp);

        if (1 == insertCode) {
            dataMap.put("code", "1");
//            dataMap.put("data", ffwyAudit);
//            dataMap.put("resmsg", "操作成功！");
            return dataMap;
        }

        return dataMap;
    }

    @Override
    public int allocationWorker(FfwyOrderCombomeal ffwyOrderCombomeal) {


        if (null!=ffwyOrderCombomeal.getAllotStatus()&&ffwyOrderCombomeal.getAllotStatus().equals("0")){
            ffwyOrderCombomeal.setAllotStatus("1");
        }
        int i1 = ffwyOrderCombomealMapper.updateFfwyOrderCombomeal(ffwyOrderCombomeal);

        return i1;
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param orderIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyOrderCombomealByIds(Long[] orderIds)
    {
        return ffwyOrderCombomealMapper.deleteFfwyOrderCombomealByIds(orderIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param orderId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyOrderCombomealById(Long orderId)
    {

        return ffwyOrderCombomealMapper.deleteFfwyOrderCombomealById(orderId);
    }

    @Override
    public List<FfwyOrderCombomeal> selectFfwyOrderCombomealByorderType() {
        return ffwyOrderCombomealMapper.selectFfwyOrderCombomealByorderType();
    }


}
