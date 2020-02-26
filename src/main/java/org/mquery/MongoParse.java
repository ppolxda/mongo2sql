package org.mquery;

import org.mquery.cmds.CmdFactory;

public class MongoParse {
    public CmdFactory cmds;

    MongoParse(CmdFactory cmds) {
        this.cmds = cmds;
    }

    public MongoCmd parse(final String jsonData) throws MQueryError {
        return this.cmds.create(jsonData);
    }
}
