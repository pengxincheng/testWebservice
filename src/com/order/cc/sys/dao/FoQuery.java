/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.order.cc.sys.dao;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import java.util.Date;
import java.util.LinkedList;

public class FoQuery {
    private class PreparedField {

        private String print() {
            return (new StringBuilder("[")).append(index).append("] [")
                    .append(name).append("] [").append(getTypeStr(type))
                    .append("] [").append(stringValue).append("]").toString();
        }

        private void setStringField(int type, String v) {
            switch (type) {
            case 0: // '\0'
                intValue = Integer.parseInt(v);
                break;

            case 1: // '\001'
                longValue = Long.parseLong(v);
                break;

            case 2: // '\002'
                shortValue = Short.parseShort(v);
                break;

            case 4: // '\004'
                doubleValue = Double.parseDouble(v);
                break;

            case 5: // '\005'
                floatValue = Float.parseFloat(v);
                break;
            }
        }

        private void setLongField(int type, long v) {
            switch (type) {
            case 0: // '\0'
                intValue = (int) v;
                break;

            case 1: // '\001'
                longValue = v;
                break;

            case 2: // '\002'
                shortValue = (short) (int) v;
                break;
            }
        }

        private void setDoubleField(int type, double v) {
            switch (type) {
            case 5: // '\005'
                floatValue = (float) v;
                break;

            case 4: // '\004'
                doubleValue = v;
                break;
            }
        }
        
        private void setDateField(int type, Date v) {
            dateValue = v;
        }
        
        private void setArrayField(int type, Object[] v) {
            arrayValue = v;
        }

        public void setObjectField(int type, Object v) {
            objValue = v;
        }

        private void setQueryStmt(Query query) {
            switch (type) {
            case 0: // '\0'
                if (name == null)
                    query.setInteger(index, intValue);
                else
                    query.setInteger(name, intValue);
                break;

            case 1: // '\001'
                if (name == null)
                    query.setLong(index, longValue);
                else
                    query.setLong(name, longValue);
                break;

            case 2: // '\002'
                if (name == null)
                    query.setShort(index, shortValue);
                else
                    query.setShort(name, shortValue);
                break;

            case 4: // '\004'
                if (name == null)
                    query.setDouble(index, doubleValue);
                else
                    query.setDouble(name, doubleValue);
                break;

            case 5: // '\005'
                if (name == null)
                    query.setFloat(index, floatValue);
                else
                    query.setFloat(name, floatValue);
                break;
                
            case 6:
                if(name == null)
                    query.setParameter(index, dateValue);
                else
                    query.setParameter(name, dateValue);
                break;

            case 7:
                if(name != null)
                    query.setParameterList(name, arrayValue);
                break;
            case 8:
                if(name == null)
                    query.setParameter(index, objValue);
                else
                    query.setParameter(name, objValue);
                break;
            case 3: // '\003'
            default:
                if (name == null)
                    query.setString(index, stringValue);
                else
                    query.setString(name, stringValue);
                break;
            }
        }

        private String getTypeStr(int nType) {
            switch (nType) {
            case 0: // '\0'
                return "int";

            case 1: // '\001'
                return "long";

            case 2: // '\002'
                return "short";

            case 3: // '\003'
                return "string";

            case 4: // '\004'
                return "double";

            case 5: // '\005'
                return "float";
                
            case 6:
                return "date";
            case 7:
                return "array";
            case 8:
                return "object";
            }
            
            return "null";
        }

        private String name;

        private int type;

        private int index;

        private int intValue;

        private short shortValue;

        private long longValue;

        private double doubleValue;

        private float floatValue;

        private String stringValue;
        
        private Date dateValue;
        
        private Object[] arrayValue;

        private Object objValue;

        private PreparedField(String name, int type, String value) {
            this.name = name;
            index = -1;
            this.type = type;
            stringValue = value;
            setStringField(type, value);
        }

        private PreparedField(int index, int type, String value) {
            this.index = index;
            this.type = type;
            stringValue = value;
            setStringField(type, value);
        }

        private PreparedField(int index, int type, long value) {
            this.index = index;
            this.type = type;
            stringValue = (new StringBuilder()).append(value).toString();
            setLongField(type, value);
        }

        private PreparedField(String name, int type, long value) {
            this.name = name;
            index = -1;
            this.type = type;
            stringValue = (new StringBuilder()).append(value).toString();
            setLongField(type, value);
        }

        private PreparedField(int index, int type, double value) {
            this.index = index;
            this.type = type;
            stringValue = (new StringBuilder()).append(value).toString();
            setDoubleField(type, value);
        }

        private PreparedField(String name, int type, double value) {
            this.name = name;
            index = -1;
            this.type = type;
            stringValue = (new StringBuilder()).append(value).toString();
            setDoubleField(type, value);
        }
        
        private PreparedField(int index, int type, Date value) {
            this.index = index;
            this.type = type;
            dateValue = value;
            setDateField(type, value);
        }
        
        private PreparedField(String name, int type, Date value) {
            this.name = name;
            this.index = -1;
            this.type = type;
            dateValue = value;
            setDateField(type, value);
        }

        private PreparedField(int index, int type, Object value) {
            this.index = index;
            this.type = type;
            objValue = value;
            setObjectField(type, value);
        }

        private PreparedField(String name, int type, Object value) {
            this.name = name;
            this.index = -1;
            this.type = type;
            objValue = value;
            setObjectField(type, value);
        }
        
        private PreparedField(String name, int type, Object[] value) {
            this.name = name;
            this.index = -1;
            this.type = type;
            arrayValue = value;
            setArrayField(type, value);
        }

        PreparedField(String s, int i, String s1, PreparedField preparedfield) {
            this(s, i, s1);
        }

        PreparedField(int i, int j, String s, PreparedField preparedfield) {
            this(i, j, s);
        }

        PreparedField(String s, int i, long l, PreparedField preparedfield) {
            this(s, i, l);
        }

        PreparedField(String s, int i, double d, PreparedField preparedfield) {
            this(s, i, d);
        }
    }

    FoQuery() {
        stmtList = new LinkedList();
        pageSize = 0;
        pageNum = 0;
    }

    public void setpageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public boolean isEntityMap() {
        return entityMap;
    }

    public void setEntityMap(boolean entityMap) {
        this.entityMap = entityMap;
    }

    public Query fillQueryStmt(Query query, boolean count) {
        for (int i = 0; i < stmtList.size(); i++)
            ((PreparedField) stmtList.get(i)).setQueryStmt(query);
        if(!count && entityMap) {
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        return query;
    }

    public void setString(String name, String value) {
        stmtList.add(new PreparedField(name, 3, value, null));
    }

    public void setString(int nIndex, String value) {
        stmtList.add(new PreparedField(nIndex, 3, value, null));
    }

    public void setInt(String name, int value) {
        stmtList.add(new PreparedField(name, 0, value, null));
    }

    public void setInt(int nIndex, String value) {
        stmtList.add(new PreparedField(nIndex, 0, value, null));
    }

    public void setShort(String name, short value) {
        stmtList.add(new PreparedField(name, 2, value, null));
    }

    public void setShort(int nIndex, String value) {
        stmtList.add(new PreparedField(nIndex, 2, value, null));
    }

    public void setLong(String name, long value) {
        stmtList.add(new PreparedField(name, 1, value, null));
    }

    public void setLong(int nIndex, String value) {
        stmtList.add(new PreparedField(nIndex, 1, value, null));
    }

    public void setDouble(String name, double value) {
        stmtList.add(new PreparedField(name, 4, value, null));
    }

    public void setDouble(int nIndex, String value) {
        stmtList.add(new PreparedField(nIndex, 4, value, null));
    }

    public void setFloat(String name, float value) {
        stmtList.add(new PreparedField(name, 5, value, null));
    }

    public void setFloat(int nIndex, String value) {
        stmtList.add(new PreparedField(nIndex, 5, value, null));
    }
    
    public void setDate(String name, Date value) {
        stmtList.add(new PreparedField(name, 6, value));
    }
    
    public void setDate(int nIndex, Date value) {
        stmtList.add(new PreparedField(nIndex, 6, value));
    }
    
    public void setArray(String name, Object[] value) {
        stmtList.add(new PreparedField(name, 7, value));
    }

    public void setObject(int nIndex, Object obj) {
        stmtList.add(new PreparedField(nIndex, 8, obj));
    }

    public void setObject(String name, Object obj) {
        stmtList.add(new PreparedField(name, 8, obj));
    }

    public String[] getSortFields() {
        return sortFields;
    }

    public void setSortFields(String[] sortFields) {
        this.sortFields = sortFields;
    }

    public String print() {
        String debug = "";
        for (int i = 0; i < stmtList.size(); i++)
            debug = (new StringBuilder(String.valueOf(debug)))
                    .append("\n\t\t\t")
                    .append(((PreparedField) stmtList.get(i)).print())
                    .toString();

        return debug;
    }

    private LinkedList stmtList;

    private int pageSize;

    private int pageNum;
    
    private String[] sortFields;
    
    private boolean entityMap;
}