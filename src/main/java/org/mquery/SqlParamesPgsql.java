package org.mquery;

public abstract class SqlParamesPgsql extends SqlParames {

    @Override
    public String fmtField(String name) {
        return "'".concat(name).concat("'");
    }
}
