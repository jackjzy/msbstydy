package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.config.MyRabbitConfig;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomeal;
import com.ruoyi.system.domain.combomealorders.FfwyPhase;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.combomealorders.FfwyOrderCombomealMapper;
import com.ruoyi.system.mapper.combomealorders.FfwyPhaseMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.service.IFfwyPhaseService;

/**
 * 节点订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-15
 */
@Service
@Log4j
public class FfwyPhaseServiceImpl implements IFfwyPhaseService
{
    @Autowired
    private FfwyPhaseMapper ffwyPhaseMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 查询节点订单
     * 
     * @param phaseId 节点订单ID
     * @return 节点订单
     */
    @Override
    public FfwyPhase selectFfwyPhaseById(Long phaseId)
    {
        return ffwyPhaseMapper.selectFfwyPhaseById(phaseId);
    }

    /**
     * 查询节点订单列表
     * 
     * @param ffwyPhase 节点订单
     * @return 节点订单
     */
    @Override
    public List<FfwyPhase> selectFfwyPhaseList(FfwyPhase ffwyPhase)
    {
        return ffwyPhaseMapper.selectFfwyPhaseList(ffwyPhase);
    }

    @Override
    public FfwyPhase selectFfwyPhaseByOrder(String orderId) {
        return ffwyPhaseMapper.selectFfwyPhaseByOrder(orderId);
    }

    @Override
    public List<FfwyPhase> selectFfwParent(FfwyPhase ffwyPhase) {
        List<FfwyPhase> ffwyPhases = ffwyPhaseMapper.selectFfwyPhaseList(ffwyPhase);
        ffwyPhases.forEach(ffwyPhase1 -> {

            FfwyPhase Phase2 = new FfwyPhase();
            Phase2.setOrderId(ffwyPhase.getOrderId());
            Phase2.setParentId(ffwyPhase1.getPhaseId());

            List<FfwyPhase> ffwyPhases1 = ffwyPhaseMapper.selectFfwyPhaseList(Phase2);
            ffwyPhase1.setFfwyPhaseList(ffwyPhases1);


        });
        return ffwyPhases;
    }

    /**
     * 新增节点订单
     * 
     * @param ffwyPhase 节点订单
     * @return 结果
     */
    @Override
    public int insertFfwyPhase(FfwyPhase ffwyPhase)
    {
        return ffwyPhaseMapper.insertFfwyPhase(ffwyPhase);
    }

    @Override
    public int insertFfwyPhases(List<FfwyPhase> ffwyPhases) {

        return 0;
    }

    /**
     * 修改节点订单
     * 
     * @param ffwyPhase 节点订单
     * @return 结果
     */
    @Override
    public int updateFfwyPhase(FfwyPhase ffwyPhase)
    {
        return ffwyPhaseMapper.updateFfwyPhase(ffwyPhase);
    }

    @Override
    public int updateFfwyPhases(FfwyPhase ffwyPhase,Long userId,String userType) {
        //查询当前用户的的类型
        if (ObjectUtil.isEmpty(ffwyPhase)){
            return 0;
        }
//        FfwyUser user = ffwyUserMapper.selectFfwyUserById(userId);
//        String userType = user.getUserType();
        //为工长类型
        if ("1".equals(userType)){
            //修改当前（工长端）子节点为已确认
            ffwyPhase.setPhaseStatus("1");
            //修改当前（用户端）子节点为确认
            ffwyPhase.setPhaseStatusUser("0");
            ffwyPhaseMapper.updateFfwyPhase(ffwyPhase);
        }else{//非工长类型（用户和商家）
            //判断当前为父节点还是子节点
            if (null==ffwyPhase.getParentId()){//父节点
                //修改当前（用户端）父节点为已支付
                ffwyPhase.setPhaseStatusUser("1");
                //修改当前（工长端）父节点为装修中
                ffwyPhase.setPhaseStatus("0");
                //修改当前节点用户段的状态和工长端的状态
                ffwyPhaseMapper.updateFfwyPhase(ffwyPhase);
                //查询下一个子节点，若为空则表示最后一个节点
                FfwyPhase ChildPhase = ffwyPhaseMapper.selectNextChildPhase(ffwyPhase.getPhaseId(), ffwyPhase.getOrderId());
                    if (null!=ChildPhase){
                        //修改工长段的子节点为确认状态
                        ChildPhase.setPhaseStatus("0");
                        ffwyPhaseMapper.updateFfwyPhase(ChildPhase);
                    }else {
                        //最后一个节点设置定时任务，达到时间是修改状态

                        long time = ffwyPhase.getEndTime().getTime();
                        updeateLastPhase(ffwyPhase.getPhaseId(),time);

                        return 2;
                    }

            }else {//子节点
                //修改当前子节点为已确认
                ffwyPhase.setPhaseStatusUser("1");
                ffwyPhaseMapper.updateFfwyPhase(ffwyPhase);
                //查询下一个节点为父节点还是子节点

                FfwyPhase childPhase = ffwyPhaseMapper.selectNextChildPhase(ffwyPhase.getPhaseId(), ffwyPhase.getOrderId());
                FfwyPhase parentPhase = ffwyPhaseMapper.selectNextParentPhase(ffwyPhase.getPhaseId(), ffwyPhase.getOrderId());
                //下个节点为子节点

                if (!ObjectUtil.isEmpty(childPhase)&&
                        childPhase.getLevel()-ffwyPhase.getLevel()==1){
                    //修改工长段子节点状态为确认
                    childPhase.setPhaseStatus("0");
                    ffwyPhaseMapper.updateFfwyPhase(childPhase);
                }else {//下个节点为父节点
                    FfwyPhase nowParentPhase = ffwyPhaseMapper.selectNowParentPhase(ffwyPhase.getPhaseId(), ffwyPhase.getOrderId());
                    nowParentPhase.setPhaseStatus("2");//修改当前父节点工长端为已完成
                    ffwyPhaseMapper.updateFfwyPhase(nowParentPhase);
                    //修改下个父节点状态
                    if (null!=parentPhase){
                        parentPhase.setPhaseStatusUser("0");//修改下个用户端父节点为支付
                        ffwyPhaseMapper.updateFfwyPhase(parentPhase);
                    }else {
                        //此节点为最后一个节点
                        return 2;
                    }


                }

            }
        }

        return 1;
    }

    /**
     * 批量删除节点订单
     * 
     * @param phaseIds 需要删除的节点订单ID
     * @return 结果
     */
    @Override
    public int deleteFfwyPhaseByIds(Long[] phaseIds)
    {
        return ffwyPhaseMapper.deleteFfwyPhaseByIds(phaseIds);
    }

    /**
     * 删除节点订单信息
     * 
     * @param phaseId 节点订单ID
     * @return 结果
     */
    @Override
    public int deleteFfwyPhaseById(Long phaseId)
    {
        return ffwyPhaseMapper.deleteFfwyPhaseById(phaseId);
    }


    public void  updeateLastPhase(Long ffwyPhaseId,Long time) {
        int i = time.intValue();
        System.err.println("当前id:" + ffwyPhaseId);
        rabbitTemplate.convertAndSend(MyRabbitConfig.DELAYED_EXCHANGE_NAME, MyRabbitConfig.DELAYED_ROUTING_KEY, "23", message -> {
            log.info("发送消息{}");
            System.err.println("-------------");
            message.getMessageProperties().setDelay(10000);
            log.info("发送mq******8");
            return message;
        });
    }
}
