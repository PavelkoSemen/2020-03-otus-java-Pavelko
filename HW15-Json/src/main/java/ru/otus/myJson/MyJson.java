package ru.otus.myJson;

import javax.json.*;
import java.lang.reflect.*;
import java.util.Collection;

public class MyJson {

    public String toJson(Object objectToJson) {
        if (objectToJson != null) {
            try {
                return anyObjectToJson(objectToJson, objectToJson.getClass()).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            return JsonValue.NULL.toString();
        }

        return null;
    }

    private JsonValue anyObjectToJson(Object anyObject, Class<?> classAnyObject) throws IllegalAccessException {

        if (Modifier.isTransient(classAnyObject.getModifiers())) {
            return JsonValue.EMPTY_JSON_OBJECT;
        }
        if (Collection.class.isAssignableFrom(classAnyObject)) {

            return convertElementToCollection(anyObject);

        }
        if (classAnyObject.isArray()) {
            return convertElementToArray(anyObject);
        }
        if (classAnyObject.isPrimitive() || Number.class.isAssignableFrom(classAnyObject)
                || Character.class.isAssignableFrom(classAnyObject)) {
            return jsonValueFromAnyType(anyObject, anyObject.getClass());
        }
        if (String.class.isAssignableFrom(classAnyObject)) {
            return Json.createValue(anyObject.toString());
        }

        Field[] fields = classAnyObject.getDeclaredFields();

        var jsonBuilderSpecialObj = Json.createObjectBuilder();

        for (Field field : fields) {
            field.setAccessible(true);

            Object element = field.get(anyObject);
            String nameElement = field.getName();
            Class<?> typeElement = field.getType();
            jsonBuilderSpecialObj.add(nameElement, anyObjectToJson(element, typeElement));
        }

        return jsonBuilderSpecialObj.build();


    }

    private JsonValue convertElementToCollection(Object objectCollection) {

        Collection<?> objects = (Collection<?>) objectCollection;


        return Json.createArrayBuilder(objects).build();

    }

    private JsonValue convertElementToArray(Object objectArray) {
        var arrayBuilder = Json.createArrayBuilder();
        int length = Array.getLength(objectArray);
        Class<?> typeElement = length > 0 ? Array.get(objectArray, 1).getClass() : null;

        for (int i = 0; i < length; i++) {
            arrayBuilder.add(jsonValueFromAnyType(Array.get(objectArray, i), typeElement));
        }

        return arrayBuilder.build();
    }

    private JsonValue jsonValueFromAnyType(Object objectAnyType, Class<?> typeElement) {

        if (Byte.class.isAssignableFrom(typeElement)) {
            return Json.createValue((byte) objectAnyType);
        }
        if (Short.class.isAssignableFrom(typeElement)) {
            return Json.createValue((short) objectAnyType);
        }
        if (Character.class.isAssignableFrom(typeElement)) {
            return Json.createValue(objectAnyType.toString());
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


