package org.mquery.cmds;

import org.mquery.MongoDefine;

public class LessCmd extends EqualCmd {

    public LessCmd(String field, Object data, CmdFactory cf) {
        super(field, data, cf);
    }

    @Override
    public String getJoinName() {
        return " < ";
    }

    @Override
    public String getOpName() {
        return MongoDefine.C_LT;
    }
}
