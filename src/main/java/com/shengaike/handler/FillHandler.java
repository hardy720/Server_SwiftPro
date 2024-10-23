package com.shengaike.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("friendAddtime", new Date(), metaObject);
//        this.setFieldValByName("addTime", new Date(), metaObject);
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("friendAddtime", new Date(), metaObject);
//        this.setFieldValByName("updTime", new Date(), metaObject);
    }
}


