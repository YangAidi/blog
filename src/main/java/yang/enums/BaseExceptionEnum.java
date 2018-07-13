package yang.enums;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 17:25
 **/
public enum BaseExceptionEnum {
    BASE_EXCEPTION_ENUM("500", "测试错误");

    String code;
    String msg;
    BaseExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getDescStrByCode(String code) {
        BaseExceptionEnum[] payTypes = values();
        for (BaseExceptionEnum payType : payTypes) {
            if (payType.getCode().equals(code)) {
                return payType.getMsg();
            }
        }
        return "";
    }

    public static BaseExceptionEnum getByCode(String code) {
        BaseExceptionEnum[] payTypes = values();
        for (BaseExceptionEnum payType : payTypes) {
            if (payType.getCode().equals(code)) {
                return payType;
            }
        }
        return null;
    }
}
