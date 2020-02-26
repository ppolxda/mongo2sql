package org.mquery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SqlParamesQuery extends SqlParames {
    private int pindex = 0;
    private Map<String, String> fields;
    private Map<String, String> parames = new HashMap<String, String>();

    public SqlParamesQuery(Map<String, String> fields) {
        this.fields = fields;
    }

    public int inc() {
        this.pindex += 1;
        return this.pindex;
    }

    public ArrayList<String> addParames(final JSONArray value) {
        ArrayList<String> r = new ArrayList<String>();
        Iterator<Object> iter = value.iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            Pair<String, String> p = this.addParame(obj.toString());
            r.add(p.getFirst());
        }
        return r;
    }

    public Pair<String, String> addParame(final String value) {
        int pid = this.inc();
        String key = String.format("#{p%s}", Integer.toString(pid));
        parames.put(key, value);
        return new Pair<String, String>(key, value);
    }

    public Map<String, String> GetParames(final JSONObject jsonData) {
        return this.parames;
    }

    public String fmtField(String name) {
        String val = this.fields.get(name);
        if (val == null) {
            return name;
        }
        return val;
    }

    public String fmtValue(String value) {
        return this.addParame(value).getFirst();
    }
}
