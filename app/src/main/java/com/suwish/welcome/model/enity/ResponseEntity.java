package com.suwish.welcome.model.enity;

/**
 * 请求实体返回的模板,设计目的是基于业务逻辑协议。<p/>
 *
 * 与<code>HttpResponse</code>的区别是其为HTTP协议,此类
 * 为其泛型模板。
 *
 * @author min.su on 2016/9/27.
 */
public abstract class ResponseEntity {

    protected long code;
    protected String message;

    public final String getMessage() {
        return message;
    }

    public final void setMessage(String message) {
        this.message = message;
    }

    public final long getCode() {
        return code;
    }

    public final void setCode(long code) {
        this.code = code;
    }

}
