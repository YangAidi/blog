package yang.util;

import com.alibaba.fastjson.JSONObject;
import yang.enums.BackendEnum;
import yang.exception.BackendException;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-09 17:23
 **/
public class Reply {
    private String code;
    private String msg;
    private JSONObject data;

    public Reply() {
        this.code = Constant.SUCCESSCODE;
        this.msg = Constant.SUCCESSMSG;
    }

    public Reply(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Reply buildFalse() {
        Reply reply = new Reply();
        reply.setCode(Constant.FAILCODE);
        reply.setMsg(Constant.FAILMSG);
        return reply;
    }

    public static Reply buildTrue() {
        Reply reply = new Reply();
        reply.setCode(Constant.SUCCESSCODE);
        reply.setMsg(Constant.SUCCESSMSG);
        return reply;
    }

    public void setTrue() {
        this.code = Constant.SUCCESSCODE;
        this.msg = Constant.SUCCESSMSG;
    }

    public void setFalse() {
        this.code = Constant.FAILCODE;
        this.msg = Constant.FAILMSG;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public void setErrMsg(String code, String msg) {
        this.data = null;
        this.code = code;
        this.msg = msg;
    }

    public void setErrMsg(BackendEnum e) {
        this.data = null;
        this.code = e.getCode();
        this.msg = e.getMsg();
    }

    public void setErrMsg(BackendException e) {
        this.data = null;
        this.code = e.getCode();
        this.msg = e.getMessage();
    }

    public void put(String key, Object value) {
        if (this.data == null) {
            this.data = new JSONObject();
        }
        this.data.put(key, value);
    }

//    public Boolean isSuccess(){
//        return this.code.equals(Constant.SUCCESSCODE);
//    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("data", data);
        return jsonObject.toString();
    }
}

