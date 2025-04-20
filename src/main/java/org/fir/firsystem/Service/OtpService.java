package org.fir.firsystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
public class OtpService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private final String OTP_PREFIX = "OTP:";

    public void saveOtp(String email, String otp) {
        String key = OTP_PREFIX + email;
        redisTemplate.opsForValue().set(key, otp);
        redisTemplate.expire(key, Duration.ofMinutes(2)); // Auto-expire in 2 mins
    }

    public boolean verifyOtp(String email, String otp) {
        String key = OTP_PREFIX + email;
        String storedOtp = (String) redisTemplate.opsForValue().get(key);
        return otp.equals(storedOtp);
    }

    public void deleteOtp(String email) {
        redisTemplate.delete(OTP_PREFIX + email);
    }
}
