package cn.liyw.domin;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 每个礼包的信息
 *
 * @author liujianqin
 */
public class CMQMessageGiftBody {

    private Integer giftId;
    private String gift;
    private Long packageId;
    private Long itemId;
    private Integer itemType;

    public Map<String, String> extParams = Maps.newHashMap();

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Map<String, String> getExtParams() {
        return extParams;
    }

    public void addExtParam(String key, String value) {
        this.extParams.put(key, value);
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

}
