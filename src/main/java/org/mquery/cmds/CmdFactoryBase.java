package org.mquery.cmds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.mquery.MQueryError;
import org.mquery.MongoCmd;
import org.mquery.MongoDefine;
import org.mquery.Pair;
import org.mquery.SqlParames;

public class CmdFactoryBase implements CmdFactory {

    // public boolean hasOpType(JSONObject jsonObject) {
    // final Set<String> keys = jsonObject.keySet();
    // final Iterator<String> itr = keys.iterator();

    // while (itr.hasNext()) {
    // final String key = itr.next();
    // if (key.startsWith("$")) {
    // return true;
    // }
    // }
    // return false;
    // }

    public static SqlParames sqDefault = new SqlParames();

    public boolean isErrorOpType(JSONObject jsonObject) {
        final Set<String> keys = jsonObject.keySet();
        final Iterator<String> itr = keys.iterator();
        int cmdCount = 0;
        boolean hasCmd = false;
        boolean hasField = false;

        while (itr.hasNext()) {
            final String key = itr.next();
            if (key.startsWith("$")) {
                hasCmd = true;
                cmdCount += 1;
            } else {
                hasField = true;
            }
        }
        return (hasCmd && hasField) || cmdCount > 1;
    }

    public Pair<String, Object> getFirstField(JSONObject jsonObject) throws MQueryError {
        final Set<String> keys = jsonObject.keySet();
        final Iterator<String> itr = keys.iterator();

        while (itr.hasNext()) {
            final String key = itr.next();
            return new Pair<String, Object>(key, jsonObject.get(key));
        }
        throw new NumberFormatException("json object empty");
    }

    public MongoCmd create(String obj) {
        JSONObject jsonObject = JSON.parseObject(obj);
        return this.create(jsonObject);
    }

    public MongoCmd create(JSONObject obj) throws MQueryError {
        if (this.isErrorOpType(obj)) {
            throw new NumberFormatException("query command error");
        }

        Pair<String, Object> data = this.getFirstField(obj);
        if (data.getFirst().startsWith("$")) {
            MongoCmd cmd = this.createLogical(data.getFirst(), data.getSecond());
            if (cmd != null) {
                return cmd;
            }
            throw new NumberFormatException("query command error");
            // MongoCmd cmd = this.createCmd(data.getFirst(), data.getSecond());
            // if (cmd != null) {
            // return cmd;
            // }

        } else {
            final Set<String> keys = obj.keySet();
            final Iterator<String> itr = keys.iterator();
            List<Object> b = new ArrayList<Object>();

            while (itr.hasNext()) {
                final String key = itr.next();
                final Object value = obj.get(key);
                b.add(this.createCmd(key, value));
            }

            return this.createLogical(MongoDefine.LOGICAL_AND, new JSONArray(b));
        }
    }

    public MongoCmd createLogical(String cmd, Object obj) {
        if (cmd.equals(MongoDefine.LOGICAL_AND)) {
            return new AndCmd(obj, this);
        } else if (cmd.equals(MongoDefine.LOGICAL_OR)) {
            return new OrCmd(obj, this);
        }
        return null;
    }

    public MongoCmd createCmd(String field, Object obj) throws MQueryError {
        if (obj instanceof JSONObject) {
            JSONObject robj = (JSONObject) obj;
            if (this.isErrorOpType(robj)) {
                throw new NumberFormatException("query subcommand error");
            }

            Pair<String, Object> data = this.getFirstField(robj);
            if (data.getFirst().startsWith("$")) {
                return this.createCmdFunc(data.getFirst(), field, data.getSecond());
            } else {
                return new EqualCmd(field, obj, this);
            }

        } else {
            return new EqualCmd(field, obj, this);
        }
    }

    public MongoCmd createCmdFunc(String cmd, String field, Object obj) {
        if (cmd.equals(MongoDefine.C_EQ)) {
            return new EqualCmd(field, obj, this);
        } else if (cmd.equals(MongoDefine.C_GT)) {
            return new GreaterCmd(field, obj, this);
        } else if (cmd.equals(MongoDefine.C_GTE)) {
            return new GreaterEqualCmd(field, obj, this);
        } else if (cmd.equals(MongoDefine.C_LT)) {
            return new LessCmd(field, obj, this);
        } else if (cmd.equals(MongoDefine.C_LTE)) {
            return new LessEqualCmd(field, obj, this);
        } else if (cmd.equals(MongoDefine.C_IN)) {
            return new InCmd(field, obj, this);
        } else if (cmd.equals(MongoDefine.C_NE)) {
            return new NotEqualCmd(field, obj, this);
        } else if (cmd.equals(MongoDefine.C_NIN)) {
            return new NotInCmd(field, obj, this);
        } else if (cmd.equals(MongoDefine.C_LIKE)) {
            return new LileCmd(field, obj, this);
        } else if (cmd.equals(MongoDefine.C_BETWEEN)) {
            return new BetweenCmd(field, obj, this);
        } else {
            return new EqualCmd(field, obj, this);
        }
    }

    public SqlParames getSqlDefault() {
        return CmdFactoryBase.sqDefault;
    }

}