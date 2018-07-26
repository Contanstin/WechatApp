package com.hpmont.service.wechat.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;


@Service
public class CustomerJsonService {
    @Value(value="classpath:customer.json")
    private Resource data;

    /**
     *     读取文件类容为字符串
     * @param file
     * @return
     */
      private String jsonRead(File file){
            Scanner scanner = null;
            StringBuilder buffer = new StringBuilder();
            try {
                scanner = new Scanner(file, "utf-8");
                while (scanner.hasNextLine()) {
                    buffer.append(scanner.nextLine());
                }
            } catch (Exception e) {

            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
            return buffer.toString();
        }

    public String getData(){
        try {

            File file = data.getFile();
            String jsonData = this.jsonRead(file);
            return jsonData;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Map<String,String>> getCustomer(){
        List<Map<String, String>> list = new ArrayList<>();
        JSONArray array = JSONArray.parseArray(getData());
        for (Object o : array) {
            JSONObject jsonObject= (JSONObject) o;
            Map<String, String> map = new HashMap<>();
            map.put("name",jsonObject.getString("name"));
            map.put("phone",jsonObject.getString("phone"));
            map.put("wechat",jsonObject.getString("wechat"));
            map.put("area",jsonObject.getString("area"));
            list.add(map);
        };
      return list;
    }

}
