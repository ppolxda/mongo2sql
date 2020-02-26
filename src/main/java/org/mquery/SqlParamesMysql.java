package org.mquery;

public abstract class SqlParamesMysql extends SqlParames {

    @Override
    public String fmtField(String name) {
        return "`".concat(name).concat("`");
    }

}
