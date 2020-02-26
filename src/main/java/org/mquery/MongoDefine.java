package org.mquery;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MongoDefine {
    public static String C_EQ = "$eq";
    public static String C_GT = "$gt"; // >
    public static String C_GTE = "$gte"; // >=
    public static String C_IN = "$in"; // in
    public static String C_LT = "$lt"; // <
    public static String C_LTE = "$lte"; // <=
    public static String C_NE = "$ne"; // !=
    public static String C_NIN = "$nin"; // not in
    public static String C_LIKE = "$like"; // like
    public static String C_BETWEEN = "$between"; // range
    public static String C_JSON_EQ = "@>"; // pg json

    public static String SQL_EQ = "=";
    public static String SQL_GT = ">";
    public static String SQL_GTE = ">=";
    public static String SQL_IN = "IN";
    public static String SQL_LT = "<";
    public static String SQL_LTE = "<=";
    public static String SQL_NE = "<>";
    public static String SQL_NIN = "NOT IN";
    public static String SQL_LIKE = "LIKE";
    public static String SQL_BETWEEN = "BETWEEN";
    public static String SQL_JSON_EQ = "@>";

    // can not allow client to use
    public static String C_EXISTS = "$exists";
    public static String C_NEXISTS = "$nexists";
    public static String C_SQL = "$sql";
    public static String SQL_EXISTS = "EXISTS";
    public static String SQL_NEXISTS = "NOT EXISTS";
    public static String SQL_SQL = "";

    public static String C_REGEX = "$regex";
    public static String SQL_REGEX = "REGEXP";

    public static Map<String, String> C2SQL = new HashMap<String, String>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            put(C_EQ, SQL_EQ);
            put(C_GT, SQL_GT);
            put(C_GTE, SQL_GTE);
            put(C_IN, SQL_IN);
            put(C_LT, SQL_LT);
            put(C_LTE, SQL_LTE);
            put(C_NE, SQL_NE);
            put(C_NIN, SQL_NIN);
            put(C_LIKE, SQL_LIKE);
            put(C_EXISTS, SQL_EXISTS);
            put(C_NEXISTS, SQL_NEXISTS);
            put(C_SQL, SQL_SQL);
            put(C_REGEX, SQL_REGEX);
            put(C_BETWEEN, SQL_BETWEEN);
            put(C_JSON_EQ, SQL_JSON_EQ);
        }
    };

    public static Set<String> LIMIT_C = new HashSet<String>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            add(C_EXISTS);
            add(C_NEXISTS);
            add(C_SQL);
            add(C_REGEX);
        }
    };

    public static String LOGICAL_AND = "$and";
    public static String LOGICAL_OR = "$or";
    public static String LOGICAL_NOR = "$nor";

    public static String SQL_AND = " AND ";
    public static String SQL_OR = " OR ";

    public static Map<String, String> L2SQL = new HashMap<String, String>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            put(LOGICAL_AND, SQL_AND);
            put(LOGICAL_OR, SQL_OR);
        }
    };
};
