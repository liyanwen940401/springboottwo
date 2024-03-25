package cn.liyw.domin;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * created on 2019/7/24 14:37
 * 风控规则对象
 *
 * @author zhangalong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Read {

    private long dayTime;

    /**
     * 规则名称
     */
    private int seconds;

}
