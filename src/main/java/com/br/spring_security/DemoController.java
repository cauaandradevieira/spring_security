package com.br.spring_security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class DemoController {

    @GetMapping("demo")
    public Map<String, Key> test()
    {
        Map<String, Key> testMap = new HashMap<>();
        String teste = "4TMdBIqleKGmRVmcoE+7GOe+V7MK/bi0cmCudUWm91t46rEoO3RUNwx1EG5ZNHJPaS+Q4vxDyN0ZguHAJ5lQ8wN1Kqwnsx05f6nB5ZxTjXwtPHAzGc53hRppUptgpImgur8CYQ4HQSDmYF16Yiww69ETKH4fSzOQsnv1sbgZjpMe3S+b4zCSHGrdF7bR0qzVmwMkvgnC/ySOPpnRhwKgpfKKlDSv+ouL3E2lWlfWiupD9mQiR+b2uyHnmZX1TLyulWK8tqKYLrCzv3JrmH3LuqMg7ws9JzcE3FYca2hPnkNLmpkd2YxmNvmtvZoadw/DqDvYcMYX+Mx9ykhZ7fAPEV+W6uyXiNkzIIVsZcsg70k=";
        byte[] keyBytes = Decoders.BASE64.decode(teste);
        testMap.put("primeiro", Keys.hmacShaKeyFor(keyBytes));

        byte[] keyBytes1 = Decoders.BASE64.decode(teste);
        testMap.put("segundo", Keys.hmacShaKeyFor(keyBytes1));

        return testMap;
    }
}
