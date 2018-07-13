package yang.exception;

import yang.enums.BackendEnum;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 17:27
 **/
public class BackendException extends BaseException{
    public BackendException(String code, String msg) {
        if (msg == null) {
            msg = BackendEnum.getDescStrByCode(code);
        }
        super.code = code;
        super.msg = msg;
    }

    public BackendException(BackendEnum backendEnum) {
        super(backendEnum.getCode(), backendEnum.getMsg());
    }

    public BackendException(String code) {
        super.code = code;
        BackendEnum backendEnum = BackendEnum.getByCode(code);
        if (backendEnum != null) {
            super.msg = backendEnum.getMsg();
        } else {
            super.msg = BackendEnum.getDescStrByCode(code);
        }
    }

    public BackendException(BackendEnum backendEnum, Throwable cause) {
        super(backendEnum.getCode(), backendEnum.getMsg(), cause);
    }
}
