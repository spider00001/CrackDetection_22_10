package com.bluemsun.controller;

import com.bluemsun.util.GsonUtils;
import com.bluemsun.util.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/detection")
public class CrackDetectionController {

    @PostMapping("/classify")
    @ResponseBody
    public String easydlImageClassify(HttpServletRequest req) {
        // 请求url
        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/segmentation/cracktest1";
        FileInputStream inputStream = null;
        try {
            Map<String, Object> map = new HashMap<>();
            Part part = req.getPart("file");
            inputStream = (FileInputStream) part.getInputStream();
            Base64.Encoder encoder = Base64.getEncoder();
            int available = inputStream.available();
            byte[] bytes = new byte[available];
            inputStream.read(bytes);
            String image = encoder.encodeToString(bytes);
            map.put("image", image);
            map.put("top_num", 6);
            String param = GsonUtils.toJson(map);
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
