package com.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pxc on 15/5/6.
 */
public class JSONUtils {

    public static String toJSONString(Object obj, String... excludeNames) {
        return convert(obj, true, excludeNames);
    }

    public static String toJSONStringInclude(Object obj, String... includeNames) {
        return convert(obj, false, includeNames);
    }

    public static JSON toJSON(Object obj, String... excludeNames) {
        return convertJSON(obj, true, excludeNames);
    }

    public static JSON toJSONInclude(Object obj, String... includeNames) {
        return convertJSON(obj, false, includeNames);
    }

    private static JSON convertJSON(Object obj, boolean exclude, String... names) {
        if (obj instanceof Collection) {
            return JSON.parseArray(convert(obj, exclude, names));
        } else {
            return JSON.parseObject(convert(obj, exclude, names));
        }
    }

    private static String convert(Object obj, boolean exclude, String... names) {
        if (obj instanceof Collection) {
            return JSONArray.toJSONString(obj, new PropertyNameFilter(names, exclude), SerializerFeature
                    .DisableCircularReferenceDetect, SerializerFeature.WriteDateUseDateFormat);
        } else {
            return JSONObject.toJSONString(obj, new PropertyNameFilter(names, exclude), SerializerFeature
                    .DisableCircularReferenceDetect, SerializerFeature.WriteDateUseDateFormat);
        }
    }

    static class PropertyNameFilter implements PropertyFilter {

        private Set<String> names;
        private boolean exclude;

        public PropertyNameFilter(String[] names, boolean exclude) {
            this.names = new HashSet(Arrays.asList(names));
            this.exclude = exclude;
        }

        @Override
        public boolean apply(Object object, String name, Object value) {
            return exclude ? !this.names.contains(name) : this.names.contains(name);
        }
    }
}
