package yang.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import yang.enums.BackendEnum;
import yang.exception.BackendException;

import java.util.List;

/**
 * @program: blog
 * @author: YangAidi
 * @create: 2018-07-10 16:13
 **/
public class ReplyArray {
    private String code;
    private String msg;
    private JSONArray data;

    public ReplyArray() {
        this.code = Constant.SUCCESSCODE;
        this.msg = Constant.SUCCESSMSG;
    }

    public ReplyArray(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ReplyArray buildFalse() {
        ReplyArray reply = new ReplyArray();
        reply.setCode(Constant.FAILCODE);
        reply.setMsg(Constant.FAILMSG);
        return reply;
    }

    public static ReplyArray buildTrue() {
        ReplyArray reply = new ReplyArray();
        reply.setCode(Constant.SUCCESSCODE);
        reply.setMsg(Constant.SUCCESSMSG);
        return reply;
    }

    public void setTrue() {
        this.code = Constant.SUCCESSCODE;
        this.msg = Constant.SUCCESSMSG;
    }

    public void setFalse() {
        this.code = Constant.SUCCESSCODE;
        this.msg = Constant.SUCCESSMSG;
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

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }

    public void setData(List data) {
        this.data = (JSONArray) JSONArray.toJSON(data);
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

    public void add(Object data) {
        if (this.data == null) {
            this.data = new JSONArray();
        }
        this.data.add(data);
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

