package org.mquery.cmds;

import com.alibaba.fastjson.JSONObject;

import org.mquery.MongoCmd;
import org.mquery.SqlParames;

public interface CmdFactory {

    public MongoCmd create(String obj);

    public MongoCmd create(JSONObject obj);

    public SqlParames getSqlDefault();
}