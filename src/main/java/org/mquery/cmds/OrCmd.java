package org.mquery.cmds;

import org.mquery.MongoDefine;

public class OrCmd extends AndCmd {

    public OrCmd(Object data, CmdFactory cf) {
        super(data, cf);
    }

    @Override
    public String getJoinKey() {
        return " or ";
    }

    @Override
    public String getOpName() {
        return MongoDefine.LOGICAL_OR;
    }
}
