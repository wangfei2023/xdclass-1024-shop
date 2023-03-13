package net.xdclass.config;

import java.util.HashMap;

/**
 * @author sqa
 */
public class Response extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public Response() {

    }

    public Response(int code, String msg, Object data) {
        this.put("code", code);
        this.put("message", msg);
        this.put("data", data);
    }

    public Response(int code, String msg) {
        this.put("code", code);
        this.put("message", msg);
    }

    public static Response msg(String msg) {
        Response yunluResponse = new Response();
        yunluResponse.put("message", msg).code(1);
        return yunluResponse;
    }

    public static Response message(int code,String message) {
        Response yunluResponse = new Response();
        yunluResponse.put("message", message).code(0);
        yunluResponse.put("code", code);
        return yunluResponse;
    }

    public Response data(Object data) {
        this.put("data", data).code(1);
        return this;
    }

    public Response rows(Object data) {
        this.put("rows", data).code(1);
        return this;
    }

    public Response total(Long data) {
        this.put("total", data);
        return this;
    }

    public Response code(int code) {
        this.put("code", code);
        return this;
    }

    @Override
    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }

    public int getCode() {
        Object code = get("code");
//        if (StringUtils.isInteger(code)) {
//            return Integer.parseInt(String.valueOf(code));
//        }
        return Integer.parseInt(String.valueOf(code));
    }
}