package com.ruoyi.system.domain.combomealorders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Materials {
    /** 装修材料集合*/
    private List<FfwyMaterial> materials;

    private List<FfwyPhase> phases;

    private FfwyOrderCombomeal ffwyOrderCombomeal;

}
