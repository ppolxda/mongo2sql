package org.mquery.cmds;

import org.mquery.MongoDefine;

public class LileCmd extends EqualCmd {

    public LileCmd(String field, Object data, CmdFactory cf) {
        super(field, data, cf);
    }

    @Override
    public String getJoinName() {
        return " like ";
    }

    @Override
    public String getOpName() {
        return MongoDefine.C_LIKE;
    }
}
