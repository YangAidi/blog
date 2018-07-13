package yang.exception;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 17:45
 **/
public class DeleteBuilding extends RuntimeException{
    private Integer code;

    public DeleteBuilding(String msg) {
        super(msg);
        this.code = -9;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
