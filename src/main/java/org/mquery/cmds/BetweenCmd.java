package org.mquery.cmds;

import com.alibaba.fastjson.JSONArray;

import org.mquery.MongoCmd;
import org.mquery.MongoDefine;
import org.mquery.SqlParames;

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

    @Override
    public String evaluate(SqlParames p) {
        return p.fmtField(this.field).concat(this.getJoinName()).concat(p.fmtValue(this.data.getString(0)))
                .concat(" and ").concat(p.fmtValue(this.data.getString(1)));
    }

    @Override
    public String str() {
        SqlParames p = this.cf.getSqlDefault();
        return this.evaluate(p);
    }

}
