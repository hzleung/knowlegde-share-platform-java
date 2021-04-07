package com.ly.cloud.user.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

/**
 * 将list转化成Map<String,Map<String,Object>>形式
 * @param list
 * @param outerKeyStr 外层map的Key取值str，如果取出空值则抛出NullPointerException
 * @param innerKeyStr 内层map的key取值str，如果取出空值则抛出NullPointerException
 * @param valueStr 内层map的value取值str，如果取出空值则抛出NullPointerException
 * @return
 */
    public static Map<String, Map<String, Object>> cloneListToBigMap(List<Map<String, Object>> list, String outerKeyStr, String innerKeyStr, String valueStr) {
        Map<String,Map<String,Object>> result=new HashMap<String,Map<String,Object>>();
        for(Map<String,Object> map:list){
            String outerKey=(String)map.get(outerKeyStr);
            if(outerKey==null){
                throw new NullPointerException();
            }
            Map<String,Object> tmp=result.get(outerKey);
            if(tmp==null){
                tmp=new HashMap<String,Object>();
                result.put(outerKey, tmp);
            }
            String innerKey=(String)map.get(innerKeyStr);
            Object value=map.get(valueStr);
            if(innerKey==null || value==null){
                throw new NullPointerException();
            }
            tmp.put(innerKey, value);
        }
        return result;
    }

    public static Map<String, List<Map<String, Object>>> cloneListToMapList(List<Map<String, Object>> list, String key) {
        Map<String,List<Map<String,Object>>> result=new HashMap<String,List<Map<String,Object>>>();
        for(Map<String,Object> m:list){
            String keystr=(String)m.get(key);
            List<Map<String,Object>> children=result.get(keystr);
            if(children==null){
                children=new ArrayList<Map<String,Object>>();
                result.put(keystr, children);
            }
            children.add(m);
        }
        return result;
    }

    /**
     * 将 List<JavaBean>对象转化为List<Map>
     * @param beanList
     * @return
     * @throws Exception
     */
    public static <T> List<Map<String, Object>> convertListBean2ListMap(List<T> beanList, Class<T> T)
            throws Exception
    {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0, n = beanList.size(); i < n; i++)
        {
            Object bean = beanList.get(i);
            Map<String, Object> map = convertBean2Map(bean);
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 将一个 JavaBean 对象转化为一个 Map
     * @param bean
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Map<String, Object> convertBean2Map(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException
    {
        Class<? extends Object> type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++)
        {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!"class".equals(propertyName))
            {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null)
                {
                    returnMap.put(propertyName, result);
                }
                else
                {
                    returnMap.put(propertyName, null);
                }
            }
        }
        return returnMap;
    }

}
