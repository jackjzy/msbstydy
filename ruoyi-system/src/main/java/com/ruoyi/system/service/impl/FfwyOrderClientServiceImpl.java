package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.order.FfwyOrderClient;
import com.ruoyi.system.domain.order.FfwyOrderPlane;
import com.ruoyi.system.domain.vo.OrderClientVo;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.order.FfwyOrderClientMapper;
import com.ruoyi.system.mapper.order.FfwyOrderPlaneMapper;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyOrderClientService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
@Service
public class FfwyOrderClientServiceImpl implements IFfwyOrderClientService {
    @Autowired
    private FfwyOrderClientMapper ffwyOrderClientMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;

    @Autowired
    private FfwyOrderPlaneMapper ffwyOrderPlaneMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param orderClientId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyOrderClient selectFfwyOrderClientById(Long orderClientId) {
        return ffwyOrderClientMapper.selectFfwyOrderClientById(orderClientId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param ffwyOrderClient 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<OrderClientVo> selectFfwyOrderClientList(FfwyOrderClient ffwyOrderClient) {
        List<OrderClientVo> orderClientVos = null;
        Long orderClientId = ffwyOrderClient.getOrderClientId();
        String orderClientAddr = ffwyOrderClient.getOrderClientAddr();
        Long orderClientPhone = ffwyOrderClient.getOrderClientPhone();
        Integer orderClientStatus = ffwyOrderClient.getOrderClientStatus();
        String orderClientType = ffwyOrderClient.getOrderClientType();
        if (orderClientId != null || orderClientAddr != null || orderClientPhone != null || orderClientStatus != null || orderClientType != null) {
        orderClientVos = ffwyOrderClientMapper.selectFfwyOrderClientList(ffwyOrderClient);
        }
      if (orderClientId == null && orderClientAddr == null && orderClientPhone == null && orderClientStatus == null && orderClientType == null) {
            orderClientVos = ffwyOrderClientMapper.selectOrderClientList();

        }
        return orderClientVos;
    }

        /**
         * 新增【请填写功能名称】
         *
         * @param ffwyOrderClient 【请填写功能名称】
         * @return 结果
         */
        @Override
        public int insertFfwyOrderClient (FfwyOrderClient ffwyOrderClient, FfwyOrderPlane ffwyOrderPlane)
        {
            Long orderClientId = ffwyOrderClient.getOrderClientId();
            ffwyOrderPlane.setOrderClientId(orderClientId);
            ffwyOrderPlane.setPlaneStatus(1);
            int i = ffwyOrderPlaneMapper.insertFfwyOrderPlane(ffwyOrderPlane);
            int i1 = ffwyOrderClientMapper.insertFfwyOrderClient(ffwyOrderClient);
            return i + i1;
        }

        /**
         * 修改【请填写功能名称】
         *
         * @param ffwyOrderClient 【请填写功能名称】
         * @return 结果
         */
        @Override
        public int updateFfwyOrderClient (FfwyOrderClient ffwyOrderClient)
        {
            return ffwyOrderClientMapper.updateFfwyOrderClient(ffwyOrderClient);
        }

        /**
         * 批量删除【请填写功能名称】
         *
         * @param orderClientIds 需要删除的【请填写功能名称】ID
         * @return 结果
         */
        @Override
        public int deleteFfwyOrderClientByIds (Long[]orderClientIds)
        {
            return ffwyOrderClientMapper.deleteFfwyOrderClientByIds(orderClientIds);
        }

        /**
         * 删除【请填写功能名称】信息
         *
         * @param orderClientId 【请填写功能名称】ID
         * @return 结果
         */
        @Override
        public int deleteFfwyOrderClientById (Long orderClientId)
        {
            return ffwyOrderClientMapper.deleteFfwyOrderClientById(orderClientId);
        }
    }
