package org.mquery;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class SqlParames {
    public Map<String, String> GetParames(final JSONObject jsonData) {
        return new HashMap<String, String>();
    }

    public String fmtField(String name) {
        return name;
    }

    public String fmtValue(String value) {
        return "'".concat(value.toString()).concat("'");
    }
}
