package com.example.android.topic41.domain.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Theme {
    private String requestParameter;
    private Map<String, String> requestParameterValues;
    private final String DEFAULT_KEY = "Software";
    private final String DEFAULT_VALUE = "software";
    private final String EXTRA_KEY_0 = "General";
    private final String EXTRA_VALUE_0 = "general";
    private final String EXTRA_KEY_1 = "Health";
    private final String EXTRA_VALUE_1 = "health";
    private final String EXTRA_KEY_2 = "Science";
    private final String EXTRA_VALUE_2 = "science";
    private final String EXTRA_KEY_3 = "Technology";
    private final String EXTRA_VALUE_3 = "technology";

    public Theme() {
        requestParameterValues = new HashMap<>();
        requestParameter = "q";
        requestParameterValues.put(DEFAULT_KEY, DEFAULT_VALUE);
        requestParameterValues.put(EXTRA_KEY_0, EXTRA_VALUE_0);
        requestParameterValues.put(EXTRA_KEY_1, EXTRA_VALUE_1);
        requestParameterValues.put(EXTRA_KEY_2, EXTRA_VALUE_2);
        requestParameterValues.put(EXTRA_KEY_3, EXTRA_VALUE_3);
    }

    public String getRequestParameter() {
        return requestParameter;
    }


    public Map<String, String> getRequestParameterValues() {
        return requestParameterValues;
    }


    public List<String> getRequestParameterKeys() {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> requestParameterValue : requestParameterValues.entrySet()) {
            if (requestParameterValue.getKey().equals(DEFAULT_KEY)){
                list.add(0, requestParameterValue.getKey());
            } else {
                list.add(requestParameterValue.getKey());
            }
        }
        return list;
    }
}
