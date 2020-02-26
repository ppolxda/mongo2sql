package org.mquery.cmds;

import com.alibaba.fastjson.JSONArray;

import org.mquery.MongoCmd;
import org.mquery.MongoDefine;

public class BetweenCmd implements MongoCmd {

    public Object obj;
    public String field;
    public JSONArray data;
    public CmdFactory cf;

    public BetweenCmd(String field, Object data, CmdFactory cf) {
        this.obj = data;
        this.field = field;
        this.data = (JSONArray) data;
        this.cf = cf;
    }

    public String getJoinName() {
        return " between ";
    }

    public String getOpName() {
        return MongoDefine.C_BETWEEN;
    }

    public String evaluate() {
        return "";
    }

    @Override
    public String str() {
        return this.cf.fmtField(this.field).concat(this.getJoinName()).concat(this.cf.fmtValue(this.data.getString(0)))
                .concat(" and ").concat(this.cf.fmtValue(this.data.getString(1)));
    }

}
