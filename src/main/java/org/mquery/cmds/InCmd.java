package org.mquery.cmds;

import com.alibaba.fastjson.JSONArray;

import org.mquery.MongoCmd;
import org.mquery.MongoDefine;

public class InCmd implements MongoCmd {

    public Object obj;
    public String field;
    public JSONArray data;
    public CmdFactory cf;

    public InCmd(String field, Object data, CmdFactory cf) {
        this.obj = data;
        this.field = field;
        this.data = (JSONArray) data;
        this.cf = cf;
    }

    public String getJoinName() {
        return " in (";
    }

    public String getOpName() {
        return MongoDefine.C_IN;
    }

    public String evaluate() {
        return "";
    }

    public String str() {
        String[] datas = this.data.stream().map((Object obj) -> {
            return this.cf.fmtValue(obj.toString());
        }).toArray(String[]::new);
        return this.cf.fmtField(this.field).concat(this.getJoinName()).concat(String.join(",", datas)).concat(")");
    }

}
