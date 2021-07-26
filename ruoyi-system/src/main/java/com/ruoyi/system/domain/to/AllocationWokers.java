package com.ruoyi.system.domain.to;

import com.ruoyi.system.domain.admin.FfwyUser;

import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomeal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllocationWokers {
    private List<FfwyUser> wokers;
    private List<FfwyOrderCombomeal> orderCombomeals;
}
