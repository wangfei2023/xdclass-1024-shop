package net.xdclass.Response;

import org.springframework.util.StringUtils;

import java.util.HashMap;
 
 
/**
 * @author sqa
 */
public class Response extends HashMap<String, Object> {
 
    private static final long serialVersionUID = -8713837118340960775L;
 
    public Response(){
 
    }
 
    public Response(int code, String msg, Object data){
    	this.put("code", code);
    	this.put("message", msg);
    	this.put("data", data);
    }
 
    public Response(int code, String msg){
    	this.put("code", code);
    	this.put("message", msg);
    }
 
    public static Response msg(String msg) {
        Response yunluResponse = new Response();
        yunluResponse.put("message", msg).code(1);
        return yunluResponse;
    }
 
    public Response message(String data,String message) {
        this.put("message", message).code(0);
        this.put("data", data).code(0);
        return this;
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
 
//    public int getCode() {
//        Object code = get("code");
//        if(StringUtils.isInteger(code)){
//        	return Integer.parseInt(String.valueOf(code));
//        }
//        return 0;
//    }
}

