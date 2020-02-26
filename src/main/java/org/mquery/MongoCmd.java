package org.mquery;

public interface MongoCmd {

    public String getOpName();

    public String evaluate(SqlParames p);

    public String str();
}
