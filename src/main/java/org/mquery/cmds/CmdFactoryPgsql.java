package org.mquery.cmds;

public class CmdFactoryPgsql extends CmdFactoryBase {

    @Override
    public String fmtField(String name) {
        return "'".concat(name).concat("'");
    }

    @Override
    public String fmtValue(Object value) {
        return "'".concat(value.toString()).concat("'");
    }
}