package qzdtest;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


/**
 * user_coupon
 * @author
 */
@Data
public class UserCoupon {

    @ExcelProperty(index = 1)
    private String id;

    /**
     * 场馆所发行的优惠券id
     */
    private Integer couponId;

    /**
     * arena_id
     */
    private String arenaId;
    /**
     * 领取人id
     */
    private String userId;

    /**
     * 领取人name
     */
    @ExcelProperty(index = 3)
    private String userName;

    /**
     * 领取人手机号
     */
    @ExcelProperty(index = 4)
    private String userPhone;

    /**
     * 领取时间
     */
    @ExcelProperty(index = 5)
    private String receiveTime;

    /**
     * 开始生效时间
     */
    private String starttime;

    /**
     * 过期时间
     */
    private String overtime;

    /**
     * 1.代金券 2. 折扣券 3.兑换券
     */
    @ExcelProperty(index = 2)
    private String couponType;

    /**
     * 优惠券标题
     */
    @ExcelProperty(index = 0)
    private String couponTitle;

    /**
     * 优惠券描述
     */
    private String couponDesc;

    /**
     * 代金券门槛金额
     */
    private Integer miniMoney;

    /**
     * 代金券满减金额
     */
    private Integer subtractMoney;

    /**
     * 折扣券折扣系数
     */
    private String discountNum;

    /**
     * 兑换券可兑换物品或场地
     */
    private String exchangeGood;

    /**
     * 优惠券可使用场馆id
     */
    private String canUseArenaids;

    /**
     * 优惠券可使用场馆name
     */
    @ExcelProperty(index = 6)
    private String canUseArenanames;

    /**
     * 优惠券说明
     */
    private String couponInformation;

    /**
     * 优惠券须知
     */
    private String couponNotice;

    /**
     * 1.暂不可用2.未使用 3.已使用 4.已过期
     */
    @ExcelProperty(index = 10)
    private String couponStatu;

    /**
     * 使用时间
     */
    @ExcelProperty(index = 8)
    private String useTime;

    /**
     * 使用场馆id
     */
    private String useArenaId;

    /**
     * 使用场馆名称
     */
    @ExcelProperty(index = 7)
    private String useArenaName;

    @ExcelProperty(index = 9)
    private String modifer;

    public UserCoupon(String userPhone, String useArenaId, String couponStatu, String canUseArenaids) {
        //this.arenaId = arenaId;
        this.userPhone = userPhone;
        this.useArenaId = useArenaId;
        this.couponStatu = couponStatu;
        this.canUseArenaids = canUseArenaids;
    }


    public UserCoupon(){

    }
}