package com.ruoyi.app.controller.myself;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BizCodeEnum;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.order.FfwyConsigneeAddr;
import com.ruoyi.system.service.IFfwyConsigneeAddrService;
import com.ruoyi.system.service.IFfwyRoleService;
import com.ruoyi.system.service.IFfwyUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "地址管理")
@RestController
@RequestMapping("/app/addr")
public class AddressController extends BaseController {

    @Autowired
    private IFfwyConsigneeAddrService ffwyConsigneeAddrService;
    @Autowired
    private IFfwyUserService iFfwyUserService;


    /**
     * 查询收货地址列表
     */
    @ApiOperation("查询收货地址列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:list')")
    @GetMapping("/list")
    public AjaxResult list(HttpServletRequest request) {

        startPage();
        FfwyConsigneeAddr ffwyConsigneeAddr = new FfwyConsigneeAddr();
        ffwyConsigneeAddr.setUserId(JWTUtils.getId(request.getHeader(JWTUtils.TOKRN)));
        List<FfwyConsigneeAddr> list = ffwyConsigneeAddrService.selectFfwyConsigneeAddrList(ffwyConsigneeAddr);
        if (null == list) {
            return AjaxResult.error(BizCodeEnum.ADDR_NOT_FOUNT.getCode(), BizCodeEnum.ADDR_NOT_FOUNT.getMessage());
        } else {

            return AjaxResult.success(list);
        }

    }
    /**
     * 查询收货地址列表
     */
    @ApiOperation("查询默认收货地址列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:list')")
    @GetMapping("/defaultList")
    public AjaxResult addrList(HttpServletRequest request) {

        startPage();
        FfwyConsigneeAddr ffwyConsigneeAddr = new FfwyConsigneeAddr();
        ffwyConsigneeAddr.setUserId(JWTUtils.getId(request.getHeader(JWTUtils.TOKRN)));
        List<FfwyConsigneeAddr> list = ffwyConsigneeAddrService.selectFfwyConsigneeAddressStatus(ffwyConsigneeAddr);
        if (null == list) {
            return AjaxResult.error(BizCodeEnum.ADDR_NOT_FOUNT.getCode(), BizCodeEnum.ADDR_NOT_FOUNT.getMessage());
        } else {

            return AjaxResult.success(list);
        }

    }

    /**
     * 获取收货地址详细信息
     */
    @ApiOperation("获取收货地址详细信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:query')")
    @GetMapping(value = "/queryone/{addrId}")
    public AjaxResult getInfo(@PathVariable("addrId") Long addrId) {
        return AjaxResult.success(ffwyConsigneeAddrService.selectFfwyConsigneeAddrById(addrId));
    }

    @ApiOperation("设置默认收货地址")
    @PutMapping(value = "/default/{addrId}")
    public AjaxResult defaultAddr(@PathVariable("addrId") Long addrId, HttpServletRequest request) {
        FfwyUser ffwyUser = new FfwyUser();

        ffwyUser.setUserId(JWTUtils.getId(request.getHeader(JWTUtils.TOKRN)));
        ffwyUser.setDefaultAddr(addrId);
        return AjaxResult.success(iFfwyUserService.updateFfwyUserOther(ffwyUser));
    }

    /**
     * 新增收货地址
     */
    @ApiOperation("新增收货地址")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:add')")
    @Log(title = "收货地址", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public AjaxResult add(@RequestBody FfwyConsigneeAddr ffwyConsigneeAddr, HttpServletRequest request) {
        ffwyConsigneeAddr.setUserId(JWTUtils.getId(request.getHeader(JWTUtils.TOKRN)));
        return ffwyConsigneeAddrService.insertFfwyConsigneeAddr(ffwyConsigneeAddr);
    }

    /**
     * 修改收货地址
     */
    @ApiOperation("修改收货地址")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:edit')")
    @Log(title = "收货地址", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody FfwyConsigneeAddr ffwyConsigneeAddr) {
        return toAjax(ffwyConsigneeAddrService.updateFfwyConsigneeAddr(ffwyConsigneeAddr));
    }


    /**
     * 删除收货地址
     */
    @ApiOperation("删除收货地址")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:remove')")
    @Log(title = "收货地址", businessType = BusinessType.DELETE)
    @DeleteMapping("/{addrId}")
    public AjaxResult remove(@PathVariable("addrId") Long addrId) {
        return toAjax(ffwyConsigneeAddrService.deleteFfwyConsigneeAddrById(addrId));
    }
}
