package ru.otus.myJson;


import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MyJson {
    private final String stringFormat = "\"%s\"";


    public String toJson(Object objectToJson) throws IllegalAccessException {

        if (objectToJson == null) {
            return null;
        }
        return toJson(objectToJson, objectToJson.getClass());

    }

    private String toJson(Object objectToJson, Class<?> classOfObject) throws IllegalAccessException {

        StringBuilder stringBuilder = new StringBuilder("{");

        Field[] fields = classOfObject.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            if (field.get(objectToJson) != null) {

                if (Modifier.isTransient(field.getModifiers())) {
                    continue;
                }

                stringBuilder.append(String.format(stringFormat, field.getName()))
                        .append(":");

                if (field.getType().equals(String.class)) {
                    stringBuilder.append(String.format(stringFormat, field.get(objectToJson)))
                            .append(",");
                    continue;
                }
                if (Collection.class.isAssignableFrom(field.getType())) {

                    stringBuilder.append(convertCollectionToString(field.get(objectToJson), field))
                            .append(",");
                    continue;
                }
                if (field.getType().isArray()) {
//
//
//                    stringBuilder.append(convertArrayToString(field.get(objectToJson), field))
//                    .append(",");
                    continue;
                }

                stringBuilder.append(field.get(objectToJson))
                        .append(",");

            }

        }
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "}");

        return stringBuilder.toString();
    }


    private String convertCollectionToString(Object objectCollection, Field fieldCollection) {


        Type genericFieldType = fieldCollection.getGenericType();
        ParameterizedType pType = (ParameterizedType) genericFieldType;
        Type[] typesOfParameters = pType.getActualTypeArguments();

        if (typesOfParameters[0].equals(String.class)) {
            Collection<?> objects = (Collection<?>) objectCollection;
            Object[] arrayObjects = objects.toArray(new Object[0]);

            return addQuotationMark(arrayObjects);


        }

        return objectCollection.toString();
    }


//    private  String convertArrayToString(Object objectArray, Field fieldArray){
//        Type componentType = fieldArray.getType().getComponentType();
//
//
//
//        if (componentType.equals(String.class)) {
//        Object [] objects = (Object[]) objectArray;
//
//            return addQuotationMark(objects);
//
//        }
//
//        int [] ints = (int[]) objectArray;
//        System.out.println(Arrays.toString(ints));
//        List<Object> arrayList = Arrays.asList(objectArray);
//
//        return arrayList.toString();
//
//    }

    private String addQuotationMark(Object[] objects){

        for (int i = 0; i < objects.length; i++) {

            objects[i] = String.format(stringFormat, objects[i]);

        }

        return Arrays.toString(objects);
    }


}
