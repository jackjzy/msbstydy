package com.ruoyi.system.domain.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyVideoTag {
    private Integer ffwyvideotagId;

    private Integer videoId;

    private Integer tagId;
}
