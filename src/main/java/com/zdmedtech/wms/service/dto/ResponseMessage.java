package com.zdmedtech.wms.service.dto;

import lombok.Data;

/**
 * Author: StefanChoo
 * Date: 2018/7/13 17:43
 */
@Data
public class ResponseMessage<T> {
    private Boolean result;
    private String msg;
    private T clazz;

    public final static ResponseMessage SUCCESS_MSG = new ResponseMessage().result(Boolean.TRUE).msg("操作成功");
    public final static ResponseMessage ERROR_MSG = new ResponseMessage().result(Boolean.FALSE).msg("操作失败");

    public ResponseMessage<T> result(Boolean result) {
         this.result = result;
         return this;
    }

    public ResponseMessage<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResponseMessage<T> load(T clazz) {
        this.clazz = clazz;
        return this;
    }
}
