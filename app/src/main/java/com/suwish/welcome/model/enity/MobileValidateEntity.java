package com.suwish.welcome.model.enity;

/**对象
 *
 * 手机号验证返回信息实体
 *
 * @author min.su on 2016/9/29.
 */
public class MobileValidateEntity extends ResponseEntity {

    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
