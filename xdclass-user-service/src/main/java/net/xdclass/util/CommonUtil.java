package net.xdclass.util;

import org.apache.logging.log4j.message.Message;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.http.HttpRequest;
import java.security.MessageDigest;

public class CommonUtil {

    /**
     * 获取ip
     * 在使用springboot时，需要获取访问客户端的IP地址， 客户端正常访问ip地址;　
     *
     * @param request * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x- forwarded-for");
            if (ipAddress == null ||
                    ipAddress.length() == 0 ||
                    "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress =
                        request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 ||
                    "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null ||
                    ipAddress.length() == 0 ||
                    "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress =
                        request.getRemoteAddr();
                if
                (ipAddress.equals("127.0.0.1")) {
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
// 对于通过多个代理的情况，第一个IP为客户 端真实IP,多个IP按照','分割


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
    public static String MD5(String data){
        try {
            java.security.MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item:digest){
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        }catch (Exception e){
        }

        return null;
    }
}
