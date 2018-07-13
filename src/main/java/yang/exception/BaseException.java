package yang.exception;

import yang.enums.BaseExceptionEnum;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 17:33
 **/
public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 8002283590289218255L;

    public String msg;
    public String code;

    public BaseException(){}

    protected BaseException(String code, String arg0, Throwable arg1) {
        super(arg0, arg1);
        this.code = code;
        this.msg = arg0;
    }

    public BaseException(String code) {
        this.code = code;
        this.msg = BaseExceptionEnum.getDescStrByCode(code);
    }

    public BaseException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    public String getCode() {
        return this.code;
    }

}
