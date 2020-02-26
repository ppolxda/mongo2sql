package org.mquery.cmds;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.junit.Test;
import org.mquery.SqlParames;
import org.mquery.SqlParamesQuery;

/**
 * Unit test for simple App.
 */
public class SqlParamesQueryTest {

    public static CmdFactory f = new CmdFactoryBase();
    public static Map<String, String> fdine = new HashMap<String, String>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            put("a", "db.a");
            put("b", "db.b");
            put("c", "db.c");
            put("d", "db.d");
        }
    };

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldEqual() {
        SqlParames p = new SqlParamesQuery(SqlParamesQueryTest.fdine);
        JSONObject obj = JSON.parseObject("{\"a\": 1, \"b\":2, \"c\": 3, \"d\":4}");
        String r = SqlParamesQueryTest.f.create(obj).evaluate(p);
        assertTrue(r.equals("(db.a = #{p1} and db.b = #{p2} and db.c = #{p3} and db.d = #{p4})"));
    }

    @Test
    public void shouldEqualAnd() {
        SqlParames p = new SqlParamesQuery(SqlParamesQueryTest.fdine);
        JSONObject obj = JSON.parseObject("{\"$and\": [{\"a\": 1, \"b\":2}, {\"c\": 3, \"d\":4}]}");
        String r = SqlParamesQueryTest.f.create(obj).evaluate(p);
        assertTrue(r.equals("((db.a = #{p1} and db.b = #{p2}) and (db.c = #{p3} and db.d = #{p4}))"));
    }

    @Test
    public void shouldEqualOr() {
        JSONObject obj = JSON.parseObject("{\"$or\": [{\"a\": 1, \"b\":2}, {\"c\": 3, \"d\":4}]}");
        String r = SqlParamesQueryTest.f.create(obj).str();
        assertTrue(r.equals("((a = '1' and b = '2') or (c = '3' and d = '4'))"));
    }

    @Test
    public void shouldIn() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$in\": [1,2,3,4]}}");
        String r = SqlParamesQueryTest.f.create(obj).str();
        assertTrue(r.equals("(a in ('1','2','3','4'))"));
    }

    @Test
    public void shouldNotIn() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$nin\": [1,2,3,4]}}");
        String r = SqlParamesQueryTest.f.create(obj).str();
        assertTrue(r.equals("(a not in ('1','2','3','4'))"));
    }

    @Test
    public void shouldGT() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$gt\": 100}, \"b\": 1}");
        String r = SqlParamesQueryTest.f.create(obj).str();
        assertTrue(r.equals("(a > '100' and b = '1')"));
    }

    @Test
    public void shouldGTE() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$gte\": 100}, \"b\": 1}");
        String r = SqlParamesQueryTest.f.create(obj).str();
        assertTrue(r.equals("(a >= '100' and b = '1')"));
    }

    @Test
    public void shouldLT() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$lt\": 100}, \"b\": 1}");
        String r = SqlParamesQueryTest.f.create(obj).str();
        assertTrue(r.equals("(a < '100' and b = '1')"));
    }

    @Test
    public void shouldLTE() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$lte\": 100}, \"b\": 1}");
        String r = SqlParamesQueryTest.f.create(obj).str();
        assertTrue(r.equals("(a <= '100' and b = '1')"));
    }

    @Test
    public void shouldEqualAndOr() {
        JSONObject obj = JSON.parseObject(
                "{\"$and\": [{\"a\": 1, \"b\":2}, {\"$or\": [{\"a\": 1, \"b\":2}, {\"c\": 3, \"d\":4}]}]}");
        String r = SqlParamesQueryTest.f.create(obj).str();
        assertTrue(r.equals("((a = '1' and b = '2') and ((a = '1' and b = '2') or (c = '3' and d = '4')))"));
    }

    @Test
    public void shouldLike() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$like\": \"bbbbb%\"}}");
        String r = SqlParamesQueryTest.f.create(obj).str();
        assertTrue(r.equals("(a like 'bbbbb%')"));
    }

    @Test
    public void shouldBetween() {
        JSONObject obj = JSON.parseObject("{\"a\": {\"$between\": [122, 2333]}}");
        String r = SqlParamesQueryTest.f.create(obj).str();
        assertTrue(r.equals("(a between '122' and '2333')"));
    }
}
