package ru.otus.myJson;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MyJson {

    public String toJson(Object objectToJson) throws IllegalAccessException {

        if (objectToJson == null) {
            return null;
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



            Json.createArrayBuilder();
            if (element != null) {

                if (Modifier.isTransient(field.getModifiers())) {
                    continue;
                }
                if (Collection.class.isAssignableFrom(typeElement)) {

                    jsonObjectForString.add(nameElement, convertElementToCollection(element));
                    continue;
                }
                if (typeElement.isArray()) {

                    continue;
                }
                if (byte.class.equals(typeElement)) {
                    jsonObjectForString.add(nameElement, (byte) element);
                    continue;
                }
                if (short.class.equals(typeElement)) {
                    jsonObjectForString.add(nameElement, (short) element);
                    continue;
                }
                if (char.class.equals(typeElement)) {
                    jsonObjectForString.add(nameElement, (char) element);
                    continue;
                }
                if (int.class.equals(typeElement)) {
                    jsonObjectForString.add(nameElement, (int) element);
                    continue;
                }
                if (long.class.equals(typeElement)) {
                    jsonObjectForString.add(nameElement, (long) element);
                    continue;
                }
                if (float.class.equals(typeElement)) {
                    jsonObjectForString.add(nameElement, (float) element);
                    continue;
                }
                if (double.class.equals(typeElement)) {
                    jsonObjectForString.add(nameElement, (double) element);
                    continue;
                }
                if (String.class.equals(typeElement)) {
                    jsonObjectForString.add(nameElement, (String) element);
                }

            }

        }

        return jsonObjectForString.build().toString();
    }


    private JsonArrayBuilder convertElementToCollection(Object objectCollection) {

        Collection<?> objects = (Collection<?>) objectCollection;
        return Json.createArrayBuilder(objects);


    }

    private <T> JsonArray createJsonArray(T [] arrayAnyType){
        var jsonValues = Json.createArrayBuilder();
        for (int i = 0; i < arrayAnyType.length; i++){
            jsonValues.add( arrayAnyType[i]);
        }

        return jsonValues.build();

    }

    private  JsonArrayBuilder convertElementToArray(Object objectArray, Field fieldArray){
        Type componentType = fieldArray.getType().getComponentType();
//        JsonArray arr = Json.createArrayBuilder();


        if (byte.class.equals(componentType)) {
            return Json.createArrayBuilder(Arrays.asList(objectArray));
        }
        if (short.class.equals(componentType)) {
        }
        if (char.class.equals(componentType)) {
        }
        if (int.class.equals(componentType)) {

        }
        if (long.class.equals(componentType)) {

        }
        if (float.class.equals(componentType)) {

        }
        if (double.class.equals(componentType)) {

        }



    }

    }

}
