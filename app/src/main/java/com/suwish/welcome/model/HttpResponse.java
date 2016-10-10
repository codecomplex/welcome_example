package com.suwish.welcome.model;

/**
 *  http请求返回数据封装对象,包括一个状态码<code>code</code>、
 *  <code>message</code>返回消息,一般是对code的解释,<code>T</code>
 *  泛型类型,一般为http返回数据转化过的实体
 *
 * @author by min.su on 2016/10/5.
 */
public class HttpResponse<T> {

    public static final long CODE_SUCCESS = 0;

    private long code;
    private String message;

    private T data;

    public long getCode() {
        return code;
    }
    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
