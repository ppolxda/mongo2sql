package org.mquery;

import com.alibaba.fastjson.JSONObject;

import org.mquery.cmds.CmdFactory;
import org.mquery.cmds.CmdFactoryBase;

public class MongoParse {
    public CmdFactory cmds;

    MongoParse() {
        this.cmds = new CmdFactoryBase();
    }

    MongoParse(CmdFactory cmds) {
        this.cmds = cmds;
    }

    public MongoCmd parse(final String jsonData) throws MQueryError {
        return this.cmds.create(jsonData);
    }

    public MongoCmd parse(final JSONObject jsonData) throws MQueryError {
        return this.cmds.create(jsonData);
    }
}
