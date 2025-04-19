package org.fir.firsystem.GenAi;

//import com.donorsync.main.service.GroqApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class GroqController {
    @Autowired
    private GroqApiService groqApiService;

    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.api.model}")
    private String model;
//    @PostMapping("/api/groq")
    public String callApi(@RequestBody String content) {
        return groqApiService.callGroqApi(apiKey, model, content);
    }
}