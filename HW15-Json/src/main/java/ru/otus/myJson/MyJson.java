package ru.otus.myJson;

import javax.json.*;
import java.lang.reflect.*;
import java.util.Collection;

public class MyJson {

    public String toJson(Object objectToJson) throws IllegalAccessException {

        if (objectToJson == null) {

            return JsonValue.NULL.toString();

        }
        return toJson(objectToJson, objectToJson.getClass());

    }

    private String toJson(Object objectToJson, Class<?> classOfObject) throws IllegalAccessException {

        var jsonObjectForString = Json.createObjectBuilder();

        Field[] fields = classOfObject.getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);

            Object element = field.get(objectToJson);
            String nameElement = field.getName();
            Class<?> typeElement = field.getType();

            if (element != null) {

                if (Modifier.isTransient(field.getModifiers())) {
                    continue;
                }
                if (Collection.class.isAssignableFrom(typeElement)) {

                    jsonObjectForString.add(nameElement, convertElementToCollection(element));
                    continue;
                }
                if (typeElement.isArray()) {
                    jsonObjectForString.add(nameElement, convertElementToArray(element));
                    continue;
                }
                if (typeElement.isPrimitive()) {
                    jsonObjectForString.add(nameElement, jsonValueFromAnyType(element, element.getClass()));
                    continue;
                }

                jsonObjectForString.add(nameElement, element.toString());

            }

        }

        return jsonObjectForString.build().toString();
    }

    private JsonArrayBuilder convertElementToCollection(Object objectCollection) {

        Collection<?> objects = (Collection<?>) objectCollection;
        return Json.createArrayBuilder(objects);

    }

    private JsonArrayBuilder convertElementToArray(Object objectArray) {
        var arrayBuilder = Json.createArrayBuilder();
        int length = Array.getLength(objectArray);
        Class<?> typeElement = length > 0 ? Array.get(objectArray, 1).getClass() : null;

        for (int i = 0; i < length; i++) {
            arrayBuilder.add(jsonValueFromAnyType(Array.get(objectArray, i), typeElement));
        }


        return arrayBuilder;
    }

    private JsonValue jsonValueFromAnyType(Object objectAnyType, Class<?> typeElement) {

        if (Byte.class.isAssignableFrom(typeElement)) {
            return Json.createValue((byte) objectAnyType);
        }
        if (Short.class.isAssignableFrom(typeElement)) {
            return Json.createValue((short) objectAnyType);
        }
        if (Character.class.isAssignableFrom(typeElement)) {
            return Json.createValue((char) objectAnyType);
        }
        if (Integer.class.isAssignableFrom(typeElement)) {
            return Json.createValue((int) objectAnyType);
        }
        if (Long.class.isAssignableFrom(typeElement)) {
            return Json.createValue((long) objectAnyType);
        }

        if (Double.class.isAssignableFrom(typeElement)) {
            return Json.createValue((double) objectAnyType);
        }
        if (Float.class.isAssignableFrom(typeElement)) {
            return Json.createValue((float) objectAnyType);
        }

        return (boolean) objectAnyType ? JsonValue.TRUE : JsonValue.FALSE;

    }

}


