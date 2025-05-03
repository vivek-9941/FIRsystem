package org.fir.firsystem.GenAi;

//import com.donorsync.main.service.GroqApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/user")
@RestController
public class GroqController {
    @Autowired
    private GroqApiService groqApiService;

    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.api.model}")
    private String model;
    @PostMapping("/api/groq")
    public String callApi(@RequestBody String content) {
        String finalcontent =  "You are a virtual assistant for a police complaint filing system. Your purpose is to give users give information and solve their doubts not  asking a question just give the answer to them .  ";
        content = content  + finalcontent ;
        return groqApiService.callGroqApi(apiKey, model, content);
    }
}