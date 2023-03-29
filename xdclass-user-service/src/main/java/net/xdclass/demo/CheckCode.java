package net.xdclass.demo;

import java.util.Random;

public class CheckCode {
    private static final int CODE_LENGTH = 6; // 验证码长度，可以根据需求更改

    public static void main(String[] args) {
        String phoneNumber = "17821088566"; // 假设要发送验证码的手机号
        String code = generateCode(); // 生成验证码
        sendCode(phoneNumber, code); // 发送验证码
    }

    // 生成六位验证码
    private static String generateCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        Random random = new Random();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }
        return code.toString();
    }

    // 发送验证码
    private static void sendCode(String phoneNumber, String code) {
        System.out.println("Sending verification code " + code + " to " + phoneNumber);
        // 在这里实现发送验证码的具体逻辑，可以调用第三方短信网关或者使用自己的短信接口等等
    }
}
