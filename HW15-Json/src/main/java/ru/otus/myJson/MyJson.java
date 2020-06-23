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
                    System.out.println(Array.getLength(element));
                    jsonObjectForString.add(nameElement, convertElementToArray(element, typeElement));
                    continue;
                }

                jsonObjectForString.add(nameElement, jsonValueFromAnyType(element, typeElement));

            }

        }

        return jsonObjectForString.build().toString();
    }

    private JsonArrayBuilder convertElementToCollection(Object objectCollection) {

        Collection<?> objects = (Collection<?>) objectCollection;
        return Json.createArrayBuilder(objects);


    }

    private JsonArrayBuilder convertElementToArray(Object objectArray, Class<?> fieldArray) {
        Class<?> componentType = fieldArray.componentType();
        var arrayBuilder = Json.createArrayBuilder();

        int length = Array.getLength(objectArray);

        for (int i = 0; i < length; i++) {
            arrayBuilder.add(jsonValueFromAnyType(Array.get(objectArray, i), componentType));
        }


        return arrayBuilder;
    }

    private JsonValue jsonValueFromAnyType(Object objectAnyType, Class<?> typeElement) {

        if (byte.class.equals(typeElement) || short.class.equals(typeElement)
                || char.class.equals(typeElement) || int.class.equals(typeElement)) {
            return Json.createValue((int) objectAnyType);
        }
        if (long.class.equals(typeElement)) {
            return Json.createValue((int) objectAnyType);

        }
        if (float.class.equals(typeElement) || double.class.equals(typeElement)) {
            return Json.createValue((double) objectAnyType);

        }

        if (boolean.class.equals(typeElement)) {
            return (boolean) objectAnyType ? JsonValue.TRUE : JsonValue.FALSE;
        }

        return Json.createValue((String) objectAnyType);

    }

}


