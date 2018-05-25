package com.hpmont.constants;

/**
 * Created by Administrator on 2016/7/20.
 */
public class Setting{
    /**
     * 验证码类型
     */
    public enum CaptchaType {

        /** 会员登录 */
        memberLogin,

        /** 会员注册 */
        memberRegister,

        /** 后台登录 */
        adminLogin,

        /** 找回密码 */
        findPassword,

        /** 重置密码 */
        resetPassword,

        /** 其它 */
        other
    }

    public enum GiroPayStatus{
        /** 初始 */
        firststatu,
        /** 支付中 */
        paying,
        /** 支付成功 */
        paysuccess,
        /** 支付失败 */
        payerr,
        /**支付取消*/
        paycancle
    }

    /**
     * 赠送类型
     */
    public enum PromotePayType{
        /** 优惠券 */
        coupon,
        /** 现金 */
        money

    }

    /**
     * 优惠券发放类型
     * 对应的整型需要+1
     */
    public enum CouponSendType{
        /** 按用户发放 */
        byuser,
        /** 注册时发放 */
        regist,
        /** 推荐发放 */
        promote
    }

    /**
     * 优惠券发放对象
     * 对应的整型需要+1
     */
    public enum CouponSendObject{
        /** 老师 */
        teacher,
        /** 学生 */
        student,
        /** 所有 */
        all
    }

    /**
     * HomePage FitType
     */
    public enum HomePageFitType{
        /** 都适合 */
        ALL,
        /** 老师 */
        T,
        /** 学生 */
        S
    }

    //上课地址类型
    public enum ClassAddType{
        /** 协商 */
        bothagree,
        /** 老师上门 */
        t2door,
        /** 学生上门 */
        s2door
    }
    /**
     * 课程类型
     * 1：1v1
     * 2:班课1vn
     */
    public enum PublishType{
        one2one(1), one2many(2);
        private int value = 0;
        private PublishType(int value) {
            this.value = value;
        }
        public static PublishType valueOf(int value) {
            switch (value) {
                case 1:
                    return one2one;
                case 2:
                    return one2many;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }
    }
    /**
     * 订单状态:-10--退单;10--下单;20--已付款;25--老师已接受;30--退款请求;35.接受并正在退款中;40--退款结束; 50--交易完成;60--评价完成
     */
    public enum OrderStatu{
        BACK(-10),INITIAL(10), PAIED(20),TEAACCEPT(25),APPLYREFUND(30),REFUNDING(35),REFUNDED(40),ORDEROVER(50),JUDGEOVER(60);
        private int value = 0;
        private OrderStatu(int value) {
            this.value = value;
        }
        public static OrderStatu valueOf(int value) {
            switch (value) {
                case -10:
                    return BACK;
                case 10:
                    return INITIAL;
                case 20:
                    return PAIED;
                case 25:
                    return TEAACCEPT;
                case 30:
                    return APPLYREFUND;
                case 35:
                    return REFUNDING;
                case 40:
                    return REFUNDED;
                case 50:
                    return ORDEROVER;
                case 60:
                    return JUDGEOVER;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }
    }
    /**
     * 信息来源
     * 0.android
     1.ios
     2.微信
     3.qq
     4.微博
     有新的来源增加即可
     */
    public enum SourceFrom{
        android,
        ios,
        wechat,
        qq,
        microblog,
        other
    }
    /**
     * 订单流水状态记录
     0 待支付：waitPay
     1 待老师确认：waitconfirm
     2 待老师排课：waitclassorder
     3 等待上课：waitclass
     4 课程开始：beginclass
     5 学生确认上课：classconfirm
     6 等待下一课时:nextclass
     7 已完成：over
     8 退款申请：refunding
     9  已退款：refunded
     10 订单取消: cancel
     11 学生评价：stuevaluate
     12 老师回复：teareply
     13 老师评价：teaevaluate
     14 结转课程金额：carryover

     4,5,6根据课时数循环显示
     */
    public enum FlowOrderStatus{
        waitpay,
        waitconfirm,
        waitclassorder,
        waitclass,
        beginclass,
        classconfirm,
        nextclass,
        over,
        refunding,
        refunded,
        cancel,
        stuevaluate,
        teareply,
        teaevaluate,
        carryover
    }

    /**
     * 开始认证Merge状态
     * hasmerged：Merge操作完成
     * unmerged: Merge操作未完成
     */
    public enum MergeStatus{
        unmerged,
        hasmerged

    }

    /*
     * 教师资料认证类型
     */
    public enum TeacherConfirmType{
        ACADEMIC((byte)1),TITLE((byte)2), QUALIFICATIONS((byte)3),VIDEO((byte)4),HONORS((byte)5),ACHIEVEMENTS((byte)6),DEGREE((byte)8),OTHER((byte)9),IDENTITY((byte)10);
        private byte value =1;
        private TeacherConfirmType(byte value) {
            this.value = value;
        }
        public static TeacherConfirmType valueOf(byte value) {
            switch (value) {
                case 1:
                    return ACADEMIC;
                case 2:
                    return TITLE;
                case 3:
                    return QUALIFICATIONS;
                case 4:
                    return VIDEO;
                case 5:
                    return HONORS;
                case 6:
                    return ACHIEVEMENTS;
                case 8:
                    return DEGREE;
                case 9:
                    return OTHER;
                case 10:
                    return IDENTITY;
                default:
                    return null;
            }
        }
        public byte value() {
            return this.value;
        }
    }

    /**
     * 认证状态
     * 认证中 confirming
     * 认证通过 pass
     * 认证不通过 unpass
     */
    public enum ConfirmStatus{
        confirming,
        pass,
        unpass
    }

    /**
     * 用户钱包流水状态
     * 支付为1,结转为2
     * 其他状态好像没用（从数据库说明看）
     */
    public enum walletLogIsComplateType{
        /** 尚在请求中 */
        init,
        /** 支付 */
        pay,
        /** 结转 */
        carryover
    }

    /**
     * 用户钱包是否提现
     * 0：其他,1：提现
     */
    public enum walletLogIsTakeOutType{
        /** 其他 */
        other,
        /** 提现 */
        takeout
    }

    /**
     * messag消息类型
     * pay：支付类型
     * evaluate：评价
     * preorder:预约
     * system：系统
     */
    public enum JPushContentType{
        pay,
        evaluate,
        preorder,
        system
    }

    /**
     * messag消息界面跳转类型
     * teacher:老师类型
     * order：订单类型
     * evaluate:评价类型
     * pay：钱包类型（钱包类型现在无id）
     */
    public enum JPushKeyType{
        teacher,
        order,
        evaluate,
        pay,
        coupon
    }
    /**
     * 佣金类型，
     * 0:默认
     * 1：不收佣金
     * 2:自定义佣金比
     */
    public enum CommisionType{
        def,
        uncommision,
        self
    }


    /**
     * 是否拼课
     */
    public enum spellCode{
        FALSE,//0
        TRUE//1
    }

    /**
     * 订单表支付状态
     * 0:初始 1:支付中 2:已支付 3:已退款,4:支付失败
     */
    public enum OrderPayStatus{
        init,
        paying,
        paied,
        backpay,
        payerr
    }
}
