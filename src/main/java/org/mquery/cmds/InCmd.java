package org.mquery.cmds;

import com.alibaba.fastjson.JSONArray;

import org.mquery.MongoCmd;
import org.mquery.MongoDefine;
import org.mquery.SqlParames;

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

    public String evaluate(SqlParames p) {
        String[] datas = this.data.stream().map((Object obj) -> {
            return p.fmtValue(obj.toString());
        }).toArray(String[]::new);
        return p.fmtField(this.field).concat(this.getJoinName()).concat(String.join(",", datas)).concat(")");
    }

    public String str() {
        SqlParames p = this.cf.getSqlDefault();
        return this.evaluate(p);

    }

}
