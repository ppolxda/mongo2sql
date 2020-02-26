package org.mquery.cmds;

import org.mquery.MongoCmd;
import org.mquery.MongoDefine;
import org.mquery.SqlParames;

public class EqualCmd implements MongoCmd {

    public Object obj;
    public String field;
    public String data;
    public CmdFactory cf;

    public EqualCmd(String field, Object data, CmdFactory cf) {
        this.obj = data;
        this.field = field;
        this.data = data.toString();
        this.cf = cf;
    }

    public String getJoinName() {
        return " = ";
    }

    public String getOpName() {
        return MongoDefine.C_EQ;
    }

    public String evaluate(SqlParames p) {
        return String.join(this.getJoinName(), p.fmtField(this.field), p.fmtValue(this.data));
    }

    public String str() {
        SqlParames p = this.cf.getSqlDefault();
        return this.evaluate(p);
    }

}
