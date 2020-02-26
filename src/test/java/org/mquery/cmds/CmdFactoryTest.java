package org.mquery.cmds;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CmdFactoryTest {

    public static CmdFactory f = new CmdFactoryBase();

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldEqual() {
        JSONObject obj = JSON.parseObject("{\"a\": 1, \"b\":2, \"c\": 3, \"d\":4}");
        String r = CmdFactoryTest.f.create(obj).str();
        assertTrue(r.equals("(\"a\" = '1' and \"b\" = '2' and \"c\" = '3' and \"d\" = '4')"));
    }

    @Test
    public void shouldEqualAnd() {
        JSONObject obj = JSON.parseObject("{\"$and\": [{\"a\": 1, \"b\":2}, {\"c\": 3, \"d\":4}]}");
        String r = CmdFactoryTest.f.create(obj).str();
        assertTrue(r.equals("((\"a\" = '1' and \"b\" = '2') and (\"c\" = '3' and \"d\" = '4'))"));
    }

    @Test
    public void shouldEqualOr() {
        JSONObject obj = JSON.parseObject("{\"$or\": [{\"a\": 1, \"b\":2}, {\"c\": 3, \"d\":4}]}");
        String r = CmdFactoryTest.f.create(obj).str();
        assertTrue(r.equals("((\"a\" = '1' and \"b\" = '2') or (\"c\" = '3' and \"d\" = '4'))"));
    }

    @Test
    public void shouldIn() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$in\": [1,2,3,4]}}");
        String r = CmdFactoryTest.f.create(obj).str();
        assertTrue(r.equals("(\"a\" in ('1','2','3','4'))"));
    }

    @Test
    public void shouldNotIn() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$nin\": [1,2,3,4]}}");
        String r = CmdFactoryTest.f.create(obj).str();
        assertTrue(r.equals("(\"a\" not in ('1','2','3','4'))"));
    }

    @Test
    public void shouldGT() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$gt\": 100}, \"b\": 1}");
        String r = CmdFactoryTest.f.create(obj).str();
        assertTrue(r.equals("(\"a\" > '100' and \"b\" = '1')"));
    }

    @Test
    public void shouldGTE() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$gte\": 100}, \"b\": 1}");
        String r = CmdFactoryTest.f.create(obj).str();
        assertTrue(r.equals("(\"a\" >= '100' and \"b\" = '1')"));
    }

    @Test
    public void shouldLT() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$lt\": 100}, \"b\": 1}");
        String r = CmdFactoryTest.f.create(obj).str();
        assertTrue(r.equals("(\"a\" < '100' and \"b\" = '1')"));
    }

    @Test
    public void shouldLTE() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$lte\": 100}, \"b\": 1}");
        String r = CmdFactoryTest.f.create(obj).str();
        assertTrue(r.equals("(\"a\" <= '100' and \"b\" = '1')"));
    }

}
