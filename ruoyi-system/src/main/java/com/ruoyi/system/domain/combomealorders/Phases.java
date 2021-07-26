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
public class Phases {
    /**装修节点*/
    private List<FfwyPhase> phases;

}
