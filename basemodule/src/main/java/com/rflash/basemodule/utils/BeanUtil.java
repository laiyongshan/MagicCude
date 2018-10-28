package com.rflash.basemodule.utils;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yangfan on 2017/11/27.
 */

public class BeanUtil {
    private static final String JAVAP = "java.";
    private static final String JAVADATESTR = "java.util.Date";

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    /**
     * serialVersionUID bean默认的属性 必须排除
     *
     * @param obj
     * @param excludeFields 需要排除的属性
     * @return
     * @throws Exception
     */
    public static TreeMap<String, String> objectToMap(Object obj, String... excludeFields) throws Exception {
        if (obj == null) {
            return null;
        }

        TreeMap<String, String> map = new TreeMap<>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            String name = field.getName();
            if (excludeFields.length != 0) {
                List<String> array = Arrays.asList(excludeFields);
                if (array.contains(name))
                    continue;
            }
            if (name.equals("serialVersionUID")) {
                continue;
            }
            String type = field.getType().toString();
            Object value = field.get(obj);
            Log.i("----", "name:" + name + "   value:" + value + "   type:" + type);

            map.put(name, (String) value);

        }

        return map;
    }


}
