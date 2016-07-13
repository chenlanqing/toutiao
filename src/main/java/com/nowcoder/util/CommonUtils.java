package com.nowcoder.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Map;

/**
 * 通用工具类
 */
public class CommonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 获取json字符串
     * @param code  状态码
     * @return
     */
    public static String getJSONString(int code){
        JSONObject json = new JSONObject();
        json.put("code", code);
        return json.toJSONString();
    }

    /**
     * 获取json字符串
     * @param code  状态码
     * @param msg   返回消息
     * @return
     */
    public static String getJSONString(int code, String msg){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json.toJSONString();
    }

    /**
     * 获取json字符串
     * @param code  状态码
     * @param data  数据
     * @return
     */
    public static String getJSONString(int code, Map<String, Object> data){
        JSONObject json = new JSONObject();
        json.put("code", code);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            json.put(entry.getKey(), entry.getValue());
        }
        return json.toJSONString();
    }

    /**
     * 将传入的字符串进行md5加密
     * @param key   要加密的字符串
     * @return
     */
    public static String md5(String key){
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            LOGGER.error("生成MD5失败", e);
            return null;
        }
    }


    public static boolean isAllowedUploadImage(String fileExt, String... fileExts){
        if(StringUtils.isBlank(fileExt)){
            return false;
        }
        for(String ext : fileExts){
            if (fileExt.equals(ext)){
                return true;
            }
        }
        return false;
    }

}
