package com.ascrud.cloud.samples.core.utils;

import com.ascrud.cloud.samples.core.enums.ResultEnum;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import org.apache.commons.lang.ArrayUtils;

import java.util.Collection;

public final class ResultVOUtils {

    public static ResultVO success(String msg, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMsg(msg);
        resultVO.setData(data);
        if (data != null) {
            if (data instanceof Collection) {
                resultVO.setCount(((Collection) data).size());
            } else if (data.getClass().isArray()) {
                resultVO.setCount(ArrayUtils.getLength(data));
            }
        }
        return resultVO;
    }

    public static ResultVO success(Object data) {
        return success("success", data);
    }

    public static ResultVO success() {
        return success("success", null);
    }


    public static ResultVO success(String msg) {
        return success(msg, null);
    }

    public static ResultVO error() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-1);
        resultVO.setMsg("error");
        return resultVO;
    }

    public static ResultVO error(String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-1);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getDesc());
        return resultVO;
    }

    public static ResultVO error(ResultEnum resultEnum, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getDesc());
        resultVO.setData(data);
        return resultVO;
    }
}
