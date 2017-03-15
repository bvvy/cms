package org.bvvy.basic.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import java.util.ArrayList;
import java.util.Map;

public class EnumUtils {
    public static List<Integer> enum2Ordinal(Class<? extends Enum> clz) {
        if(!clz.isEnum()) return null;
        Enum[] enums = clz.getEnumConstants();
        List<Integer> rels = new ArrayList<>();
        for (Enum en : enums) {
            rels.add(en.ordinal());
        }
        return rels;
    }

    public static List<String> enum2Name(Class<? extends Enum> clz) {
        if(!clz.isEnum()) return null;
        Enum[] enums = clz.getEnumConstants();
        List<String> rels = new ArrayList<>();
        for (Enum en : enums) {
            rels.add(en.name());
        }
        return rels;
    }

    public static Map<Integer,String> enum2Map(Class<? extends Enum> clz) {
        if(!clz.isEnum()) return null;
        Enum[] enums = clz.getEnumConstants();
        Map<Integer,String> rels = new HashMap<>();
        for (Enum en : enums) {
            rels.put(en.ordinal(), en.name());
        }
        return rels;
    }

    public static List<String> enumProp2List(Class<? extends Enum> clz, String propName) {
        if(!clz.isEnum()) return null;
        try {
            List<String> rels = new ArrayList<>();
            Enum[] enums = clz.getEnumConstants();
            for (Enum e : enums) {
                rels.add((String) PropertyUtils.getProperty(e, propName));
            }
            return rels;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<Integer,String> enumProp2OrdinalMap(Class<? extends Enum> clz, String propName) {
        if(!clz.isEnum()) return null;
        try {
            Map<Integer,String> rels = new HashMap<>();
            Enum[] enums = clz.getEnumConstants();
            for (Enum e : enums) {
                rels.put(e.ordinal(), (String) PropertyUtils.getProperty(e, propName));
            }
            return rels;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Map<String,String> enumProp2Map(Class<? extends Enum> clz, String keyProp,String valueProp) {
        if(!clz.isEnum()) return null;
        try {
            Map<String,String> rels = new HashMap<>();
            Enum[] enums = clz.getEnumConstants();
            for (Enum e : enums) {
                rels.put((String) PropertyUtils.getProperty(e, keyProp),(String) PropertyUtils.getProperty(e, valueProp));
            }
            return rels;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String,String> enumProp2NameMap(Class<? extends Enum> clz,String propName) {
        if(!clz.isEnum()) return null;
        try {
            Map<String,String> rels = new HashMap<>();
            Enum[] enums = clz.getEnumConstants();
            for (Enum e : enums) {
                rels.put(e.name(),(String) PropertyUtils.getProperty(e, propName));
            }
            return rels;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
