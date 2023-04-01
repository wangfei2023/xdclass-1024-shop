package net.xdclass.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Slf4j
public class CommonUtil {

    /**
     * 获取ip
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }


    /**
     * MD5加密
     *
     * @param data
     * @return
     */
    public static String MD5(String data) {
        try {
            java.security.MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }

            return sb.toString().toUpperCase();
        } catch (Exception exception) {
        }
        return null;

    }


    /**
     * 获取验证码随机数
     *
     * @param length
     * @return
     */
    public static String getRandomCode(int length) {

        String sources = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < length; j++) {
            sb.append(sources.charAt(random.nextInt(9)));
        }
        return sb.toString();
    }


    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }


    /**
     * 生成uuid
     *
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    /**
     * 获取随机长度的串
     *
     * @param length
     * @return
     */
    private static final String ALL_CHAR_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String getStringNumRandom(int length) {
        //生成随机数字和字母,
        Random random = new Random();
        StringBuilder saltString = new StringBuilder(length);
        for (int i = 1; i <= length; ++i) {
            saltString.append(ALL_CHAR_NUM.charAt(random.nextInt(ALL_CHAR_NUM.length())));
        }
        return saltString.toString();
    }


    /**
     * 响应json数据给前端
     *
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj) {

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json; charset=utf-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.print(objectMapper.writeValueAsString(obj));

            response.flushBuffer();

        } catch (IOException e) {
            log.warn("响应json数据给前端异常:{}",e);
        }


    }

    public static void main(String[] args) throws Exception {
        // 读取表文件
       // File tableFile = new File("/Users/mac/Desktop/【下午】AFP录题发题模板\\(14\\).xlsx ");
//        FileInputStream inputStream = new FileInputStream(tableFile);
//        byte[] data = new byte[(int) tableFile.length()];
//        inputStream.read(data);
//        inputStream.close();
     //todo:测试md5
      String aa="　大家好，今天给大家介绍一部电影《恶棍天使》，世界第五高峰马卡鲁峰上，到处都是高高的雪山，厚厚的白雪，三个穿得里三层外三层的人在很深的雪地里走着，这时突然手机铃声响起，戴着墨镜的大哥接起电话气喘嘘嘘的问谁呀。电话里的人说他是收帐的，叫他别东躲西藏了，杀人偿命欠债还钱。大哥生气说他口气还挺大的。现在他在外地度假呢，等他回去再说吧。电话中的人急忙阻止他挂电话的动作，叫他别挂电话，说他可是属狗的，就算他跑到天边，他闻着味也会找到他的，最后警告大哥说三天之内必须打钱。大哥听见这话仰天大笑了几声答应说好呀，大哥说他们要不打个赌吧。如果三天之内，电话里的人要是能找到他，他一定会还他钱，电话里的人欣然同意，用激将法问大哥敢不敢说他现在在哪儿，大哥对着电话还是说他在马卡鲁峰，电话里的人怀疑自己听错了，问了句什么，刚刚把电话递给身后的小弟，小弟仰头看着天，冲着手机一个字一个字的喊道马卡鲁峰，3天后，三个人继续在深雪里费劲的走着，突然好像听到了什么声音。三个人都停了下来，大哥手里拿着氧气吸着，三个人转身往身后的悬崖看去，只见从下面突然费劲的上来一个人，光脚穿着短裤背心，站起来瑟瑟发抖的朝三个人缓慢的走了过去，走到大哥面前，钻到了大哥的衣服里，拿起氧气吸了几口，给大哥笔画了一个钱的手势。大哥问他是不是那条讨债的狗，那男人点头，大哥又问他这三天是咋过来的，那男人又做了几个手势，大哥说，飞机火车牛车都猜对了。男子说了一句ok，然后一个小弟突然向男子脸上打了一拳，男子直接倒在了地上。大哥呸了一口说，他的钱怎么能给他这种杂碎。男子说要走也行，那就从他的身上踩过去。然后三个人就从他的身上踩了过去。男子突然说等等，三个人都停下了回头看，男子突然从地上站了起来，竟然舞动着身体开始唱歌，结果他的声音越来越大，雪地上逐渐出现了裂痕。音越来越高，裂痕越来越大，" +
              "结果整个雪山都开始晃动。好啦，今天的电影就到这里啦。" +
              "更多精彩视频，咱们下次再见吧\n";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = aa.getBytes();
        md5.update(bytes);
        byte[] hash = md5.digest();

        // 将加密哈希打印到控制台
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        System.out.println("Hash: " + sb.toString());
    }
 }



