package yang.enums;


/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 17:23
 **/
public enum BackendEnum {
    QUERY_EXCEPTION("101", "查询异常"),
    UPDATE_EXCEPTION("102", "更新异常"),
    DELETE_EXCEPTION("103", "删除异常"),
    ADD_EXCEPTION("104", "添加异常"),
    LOGOUT_EXCEPTION("105", "注销异常"),

    UPDATE_FAIL("111", "更新失败"),
    DELETE_FAIL("112", "删除失败"),
    ADD_FAIL("113", "添加失败"),
    REFUSE_DELETE("114", "拒绝删除"),

    ID_NOT_EXIT_EXCEPTION("121", "ID不存在"),
    OBJECT_IS_EMPTY_EXCEPTION("122", "空对象"),
    OBJECT_HAVE_EXIT_EXCEPTION("123", "对象重复"),
    UNSUPPORT_ENCODING_EXCEPTION("124", "不支持的编码异常"),

    UNCOMPLETED_INFO("-1", "用户信息不完整"),
    HAVE_BINDING_SCHOOL("201", "该用户已经绑定学校"),
    ID_IS_NOT_EXIST("202", "ID不存在"),
    ID_HAVE_EXIST("203", "ID已经存在"),
    SESSION_IS_NULL("204", "session不存在"),
    SESSION_EXCEPTION("205", "session异常"),

    USER_NEW_EXCEPTION("211", "用户注册失败"),
    UNASSIGN_COMPUTER("212", "教室未分配设备"),


    USER_REPEAT_REGISTER_EXCEPTION("221", "用户重复注册"),
    USER_NAME_IS_ILLEGAL("223", "注册信息非法"),


    USER_LOGIN_EXCEPTION("241", "登陆失败"),
    USER_REPEAT_LOGIN_EXCEPTION("242", "您已在别处登陆"),
    PASSWORD_IS_INCORRECT_OR_USER_NOT_EXIST("243", "用户名或密码错误"),
    PASSWORD_CHANGE_ERROR("244", "密码修改异常"),

    PASSWORD_AUTHENTICATION_ERROR("245", "密码校验失败"),


    USER_AUTHENTICATED_EXCEPTION("203", "您还没有登录"),
    USER_AUTHORIZED_EXCEPTION("2031", "您没有足够的权限访问"),

    LOGINNAME_IS_NOT_EXIST("204", "用户名不存在"),
    PRINCIPAL_IS_NOTNULL("205", "principal为空"),
    USERID_IS_NOT_EXIST("206", "用户ID不存在"),
    USERNAME_IS_NOT_EXIST("207", "用户名为空"),
    USERNAME_IS_RECORD("208", "用户名已经存在"),

    REDIS_EXCEPTION("601","Redis异常"),

    PARAM_ERROR("401", "参数错误"),
    SYSTEM_ERROR("400", "系统错误"),
    IO_EXCEPTION("402", "IO异常");

    private String code;
    private String msg;


    BackendEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getDescStrByCode(String code) {
        BackendEnum[] payTypes = values();
        for (BackendEnum payType : payTypes) {
            if (payType.getCode().equals(code)) {
                return payType.getMsg();
            }
        }
        return "";
    }

    public static BackendEnum getByCode(String code) {
        BackendEnum[] payTypes = values();
        for (BackendEnum payType : payTypes) {
            if (payType.getCode().equals(code)) {
                return payType;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
