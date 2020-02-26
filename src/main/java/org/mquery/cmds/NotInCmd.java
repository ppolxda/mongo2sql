package org.mquery.cmds;

import org.mquery.MongoDefine;

public class NotInCmd extends InCmd {

    public NotInCmd(String field, Object data, CmdFactory cf) {
        super(field, data, cf);
    }

    @Override
    public String getJoinName() {
        return " not in (";
    }

    @Override
    public String getOpName() {
        return MongoDefine.C_NIN;
    }
}
