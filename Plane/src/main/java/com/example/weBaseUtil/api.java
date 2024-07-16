package com.example.weBaseUtil;

import cn.hutool.core.lang.Dict;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.val;

import java.util.List;


/**
 * @author vir
 * @date 2024/5/11 下午2:51 14 51
 * @package_name com.example.WeBaseUtil
 */
public class api {
    //todo 本地签名
    public static Dict LocalSign(String contractName, String contractAddress, String funcName, List funcParam, String contractAbi) {
        JSONArray abiJSON = JSONUtil.parseArray(contractAbi);
        JSONObject data = JSONUtil.createObj();
        data.set("groupId", "1");
        data.set("user", "0x1cae7f57038fe17852e6b7c27ae24ad208715a66");
        data.set("contractName", contractName);
        data.set("version", "1.0");
        data.set("funcName", funcName);
        data.set("funcParam", funcParam);
        data.set("contractAddress", contractAddress);
        data.set("contractAbi", abiJSON);
        data.set("useAes", false);
        data.set("useCns", false);
        data.set("cnsName", "");
        String dataStr = JSONUtil.toJsonStr(data);
        try {
            val response = HttpRequest.post("http://47.109.71.124:5002/WeBASE-Front/trans/handle")
                    .header(Header.CONTENT_TYPE, "application/json").body(dataStr).execute();
                    String responseBody = response.body();
            response.close();
            Dict dict = new Dict();
            dict.set("result",responseBody);
            return dict;

        }catch (Exception e){
            e.printStackTrace();
            Dict errorDict = new Dict();
            errorDict.set("error", "An error occurred: " + e.getMessage());
            return errorDict;
        }


    }


}
