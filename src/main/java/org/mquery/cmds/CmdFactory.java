package org.mquery.cmds;

import com.alibaba.fastjson.JSONObject;

import org.mquery.MongoCmd;
// import org.mquery.MongoDefine;

public interface CmdFactory {

    public MongoCmd create(String obj);

    public MongoCmd create(JSONObject obj);

    public String fmtField(String name);

    public String fmtValue(Object value);
}