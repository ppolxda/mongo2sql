package org.mquery.cmds;

import org.mquery.MongoCmd;
import org.mquery.MongoDefine;

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

    public String evaluate() {
        return "";
    }

    public String str() {
        return String.join(this.getJoinName(), this.cf.fmtField(this.field), this.cf.fmtValue(this.data));
    }

}
