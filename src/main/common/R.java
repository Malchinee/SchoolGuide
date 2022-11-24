package main.common;

public class R<T> {
    private Integer code;//编码：1、成功 0、错误

    private String msg;//错误信息

    public static <T> R<T> success(){
        R<T> r = new R<T>();
        r.code = 1;
        return r;
    }
    public static <T> R<T> error(String msg){
        R r =new R();
        r.code = 0;
        r.msg = msg;
        return r;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
