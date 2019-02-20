package org.smirnowku.em2e;

import java.util.Map;

public class ParameterizedStringFormatter {

    public static String format(String s, Map<String, Object> params) {
        if (s == null) return null;
        for (String key : params.keySet()) {
            s = s.replace("{" + key + "}", params.get(key).toString());
        }
        return s;
    }
}
