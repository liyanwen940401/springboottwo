package cn.liyw.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created on 2019/7/24 14:37
 * 风控规则对象
 *
 * @author zhangalong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Read2 {

    private String dayTime;

    /**
     * 规则名称
     */
    private String seconds;

}
