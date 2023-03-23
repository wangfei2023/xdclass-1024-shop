package net.xdclass.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.xdclass.json.pojo.Person;

/*
{
  "name": "John",
  "age": 30,
  "email": "john@example.com"
}
 */
//todo:将JSON字符串转换为Java对象
public class ObjectJson {
    public static void main(String[] args) throws Exception{
        String jsonString="{\n" +
                "  \"name\": \"John\",\n" +
                "  \"age\": 30,\n" +
                "  \"email\": \"john@example.com\"\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        String name = objectMapper.readValue(jsonString, Person.class).getName();
        System.out.println(name);
    }
}
