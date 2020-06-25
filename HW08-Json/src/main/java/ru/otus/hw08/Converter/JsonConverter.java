package ru.otus.hw08.Converter;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;


public class JsonConverter {


    public String toJson(Object obj) throws IllegalAccessException{
        return toJsonValue(obj).toString();
    }

    private JsonValue convert(Object obj) throws IllegalAccessException {

        var clazz = obj.getClass();
        var builder = Json.createObjectBuilder();
        for(var field:clazz.getDeclaredFields()) {
            if (Modifier.isTransient(field.getModifiers())) {
                continue;
            }
            if (Modifier.isPrivate(field.getModifiers())){
                field.setAccessible(true);
            }
            Object o = field.get(obj);
            var value = toJsonValue(o);
            if (value != JsonValue.NULL) {
                builder.add(field.getName(), value);
            }
        }
        return builder.build();
    }

    private JsonArray toJsonArray(Object array) throws IllegalAccessException {
        var builder = Json.createArrayBuilder();
        var length = Array.getLength(array);
        for (int i = 0;i < length;i++){
            var o = Array.get(array,i);
            builder.add(toJsonValue(o));
        }
        return builder.build();
    }

    private JsonValue toJsonValue(Object o) throws IllegalAccessException {
        if( Objects.isNull(o)){
            return JsonValue.NULL;
        }
        if (Boolean.class.equals(o.getClass())){
            boolean aBoolean = (Boolean)o;
            return aBoolean ? JsonValue.TRUE : JsonValue.FALSE;
        }else if (Integer.class.equals(o.getClass())){
            int a = (Integer)o;
            return Json.createValue(a);
        }else if(Byte.class.equals(o.getClass())) {
            byte a = (Byte)o;
            return Json.createValue(a);
        }else if (Character.class.equals(o.getClass())) {
            Character a = (Character)o;
            return  Json.createValue(a.toString());
        }else if (Short.class.equals(o.getClass())) {
            Short a = (Short)o;
            return  Json.createValue(a);
        }else if (Long.class.equals(o.getClass())) {
            Long a = (Long)o;
            return  Json.createValue(a);
        }else if (Float.class.equals(o.getClass())) {
            var str = o.toString();
            return  Json.createValue(Double.valueOf(str));
        }else if (Double.class.equals(o.getClass())) {
            Double a = (Double)o;
            return  Json.createValue(a);
        }else if(String.class.equals(o.getClass())){
            String a = (String)o;
            return Json.createValue(a);
        }else if (Collection.class.isAssignableFrom(o.getClass())) {
            Collection collection = (Collection) o;
            return toJsonArray(collection.toArray());
        }else if  (o.getClass().isArray()) {
            return toJsonArray(o);
        }
        return convert(o);
    }


}
