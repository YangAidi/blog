package yang.exception;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 17:56
 **/
public class UnauthorizedException extends RuntimeException {
    //异常对应的返回码
    private String retCd ;
    //异常对应的描述信息
    private String msgDes;

    public UnauthorizedException(String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    public String getRetCd() {
        return retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }

    @Override
    public String toString() {
        return "UnauthorizedException{" +
                "retCd='" + retCd + '\'' +
                ", msgDes='" + msgDes + '\'' +
                '}';
    }
}
