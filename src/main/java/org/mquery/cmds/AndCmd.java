package org.mquery.cmds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.mquery.MongoCmd;
import org.mquery.MongoDefine;
import org.mquery.SqlParames;

public class AndCmd implements MongoCmd {

    public Object obj;
    public JSONArray data;
    public CmdFactory cf;

    public AndCmd(Object data, CmdFactory cf) {
        this.obj = data;
        this.data = (JSONArray) data;
        this.cf = cf;
    }

    public String getJoinKey() {
        return " and ";
    }

    public String getOpName() {
        return MongoDefine.LOGICAL_AND;
    }

    public String evaluate(SqlParames p) {
        List<MongoCmd> xcmds = this.cmds();
        String[] datas = xcmds.stream().map((MongoCmd obj) -> {
            return obj.evaluate(p);
        }).toArray(String[]::new);
        return "(".concat(String.join(this.getJoinKey(), Arrays.asList(datas))).concat(")");
    }

    public List<MongoCmd> cmds() {
        List<MongoCmd> result = new ArrayList<MongoCmd>();
        Iterator<Object> iter = this.data.iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (obj instanceof JSONObject) {
                result.add(this.cf.create((JSONObject) obj));
            } else if (obj instanceof MongoCmd) {
                result.add((MongoCmd) obj);
            } else {
                throw new NumberFormatException("AndCmd command error");
            }
        }
        return result;
    }

    public String str() {
        List<MongoCmd> xcmds = this.cmds();
        String[] datas = xcmds.stream().map((MongoCmd obj) -> {
            return obj.str();
        }).toArray(String[]::new);
        return "(".concat(String.join(this.getJoinKey(), Arrays.asList(datas))).concat(")");
    }
}
