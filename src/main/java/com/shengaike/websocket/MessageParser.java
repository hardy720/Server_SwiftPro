package com.shengaike.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class MessageParser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String,Object> SToJson (String jsonString) throws JsonProcessingException {
        Map<String, Object> messageMap = objectMapper.readValue(jsonString, Map.class);
        return messageMap;
    }

    public String parseAndReassemble(String jsonString) throws IOException {
        String outputJsonStr = "";
        // 解析JSON字符串为Map
        Map<String, Object> messageMap = objectMapper.readValue(jsonString, Map.class);
        // 从Map中提取数据
        Integer msgType = (Integer) messageMap.get("msg_Type");
        switch (msgType){
            case 0://心跳
                messageMap.put("data","pong");
                break;
            case 1:
                break;
        }
        return objectMapper.writeValueAsString(messageMap);
    }
}
