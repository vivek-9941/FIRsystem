package org.fir.firsystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
public class OtpService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    private final String OTP_PREFIX = "OTP:";

    public void saveOtp(String email, String otp) {
        String key = OTP_PREFIX + email;
        redisTemplate.opsForValue().set(key, otp, Duration.ofMinutes(5));
// Auto-expire in 5 mins
    }

    public boolean verifyOtp(String email, String otp) {
        System.out.println(email + ":" + otp);
        String key = OTP_PREFIX + email;
        String storedOtp = (String) redisTemplate.opsForValue().get(key);
        if (storedOtp == null){
            return false;
        }
        System.out.println("OTP from user: [" + otp + "] length: " + otp.length());
        System.out.println("Stored OTP   : [" + storedOtp + "] length: " + storedOtp.length());

        return otp.trim().replaceAll("\\s+", "").equals(storedOtp.trim());
    }

    public void deleteOtp(String email) {
        redisTemplate.delete(OTP_PREFIX + email);
    }
}
