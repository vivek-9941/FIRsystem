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
        String finalcontent =  "assume you are a chatbot for a online FIR system give answers accordingly for the query:";
        content = content  + finalcontent ;
        return groqApiService.callGroqApi(apiKey, model, content);
    }
}