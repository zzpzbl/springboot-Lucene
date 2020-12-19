package com.example.demo.utils;

import com.example.demo.entity.ResultBean;
import com.example.demo.entity.ResultEnum;

public class ResultUtil<T> {

    public static <T> ResultBean<T> success(T t) {
        ResultEnum successEnum = ResultEnum.SUCCESS;
        if(t == null) {
            System.out.println(777);
        }
        System.out.println(666);
        return new ResultBean<T>(successEnum.getCode(),successEnum.getMsg(),t);
    }

    public static <T> ResultBean<T> success(){
        return success(null);
    }

    public static <T> ResultBean<T> error(ResultEnum Enum){
        ResultBean<T> result = new ResultBean<T>();
        result.setCode(Enum.getCode());
        result.setMsg(Enum.getMsg());
        result.setData(null);
        return result;
    }
}
