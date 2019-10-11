package com.deschen.myblog.modules.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EpPermissionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EpPermissionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPermIdIsNull() {
            addCriterion("perm_id is null");
            return (Criteria) this;
        }

        public Criteria andPermIdIsNotNull() {
            addCriterion("perm_id is not null");
            return (Criteria) this;
        }

        public Criteria andPermIdEqualTo(Long value) {
            addCriterion("perm_id =", value, "permId");
            return (Criteria) this;
        }

        public Criteria andPermIdNotEqualTo(Long value) {
            addCriterion("perm_id <>", value, "permId");
            return (Criteria) this;
        }

        public Criteria andPermIdGreaterThan(Long value) {
            addCriterion("perm_id >", value, "permId");
            return (Criteria) this;
        }

        public Criteria andPermIdGreaterThanOrEqualTo(Long value) {
            addCriterion("perm_id >=", value, "permId");
            return (Criteria) this;
        }

        public Criteria andPermIdLessThan(Long value) {
            addCriterion("perm_id <", value, "permId");
            return (Criteria) this;
        }

        public Criteria andPermIdLessThanOrEqualTo(Long value) {
            addCriterion("perm_id <=", value, "permId");
            return (Criteria) this;
        }

        public Criteria andPermIdIn(List<Long> values) {
            addCriterion("perm_id in", values, "permId");
            return (Criteria) this;
        }

        public Criteria andPermIdNotIn(List<Long> values) {
            addCriterion("perm_id not in", values, "permId");
            return (Criteria) this;
        }

        public Criteria andPermIdBetween(Long value1, Long value2) {
            addCriterion("perm_id between", value1, value2, "permId");
            return (Criteria) this;
        }

        public Criteria andPermIdNotBetween(Long value1, Long value2) {
            addCriterion("perm_id not between", value1, value2, "permId");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionIsNull() {
            addCriterion("perm_description is null");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionIsNotNull() {
            addCriterion("perm_description is not null");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionEqualTo(String value) {
            addCriterion("perm_description =", value, "permDescription");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionNotEqualTo(String value) {
            addCriterion("perm_description <>", value, "permDescription");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionGreaterThan(String value) {
            addCriterion("perm_description >", value, "permDescription");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("perm_description >=", value, "permDescription");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionLessThan(String value) {
            addCriterion("perm_description <", value, "permDescription");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionLessThanOrEqualTo(String value) {
            addCriterion("perm_description <=", value, "permDescription");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionLike(String value) {
            addCriterion("perm_description like", value, "permDescription");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionNotLike(String value) {
            addCriterion("perm_description not like", value, "permDescription");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionIn(List<String> values) {
            addCriterion("perm_description in", values, "permDescription");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionNotIn(List<String> values) {
            addCriterion("perm_description not in", values, "permDescription");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionBetween(String value1, String value2) {
            addCriterion("perm_description between", value1, value2, "permDescription");
            return (Criteria) this;
        }

        public Criteria andPermDescriptionNotBetween(String value1, String value2) {
            addCriterion("perm_description not between", value1, value2, "permDescription");
            return (Criteria) this;
        }

        public Criteria andReqMethodIsNull() {
            addCriterion("req_method is null");
            return (Criteria) this;
        }

        public Criteria andReqMethodIsNotNull() {
            addCriterion("req_method is not null");
            return (Criteria) this;
        }

        public Criteria andReqMethodEqualTo(Boolean value) {
            addCriterion("req_method =", value, "reqMethod");
            return (Criteria) this;
        }

        public Criteria andReqMethodNotEqualTo(Boolean value) {
            addCriterion("req_method <>", value, "reqMethod");
            return (Criteria) this;
        }

        public Criteria andReqMethodGreaterThan(Boolean value) {
            addCriterion("req_method >", value, "reqMethod");
            return (Criteria) this;
        }

        public Criteria andReqMethodGreaterThanOrEqualTo(Boolean value) {
            addCriterion("req_method >=", value, "reqMethod");
            return (Criteria) this;
        }

        public Criteria andReqMethodLessThan(Boolean value) {
            addCriterion("req_method <", value, "reqMethod");
            return (Criteria) this;
        }

        public Criteria andReqMethodLessThanOrEqualTo(Boolean value) {
            addCriterion("req_method <=", value, "reqMethod");
            return (Criteria) this;
        }

        public Criteria andReqMethodIn(List<Boolean> values) {
            addCriterion("req_method in", values, "reqMethod");
            return (Criteria) this;
        }

        public Criteria andReqMethodNotIn(List<Boolean> values) {
            addCriterion("req_method not in", values, "reqMethod");
            return (Criteria) this;
        }

        public Criteria andReqMethodBetween(Boolean value1, Boolean value2) {
            addCriterion("req_method between", value1, value2, "reqMethod");
            return (Criteria) this;
        }

        public Criteria andReqMethodNotBetween(Boolean value1, Boolean value2) {
            addCriterion("req_method not between", value1, value2, "reqMethod");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixIsNull() {
            addCriterion("zuul_prefix is null");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixIsNotNull() {
            addCriterion("zuul_prefix is not null");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixEqualTo(String value) {
            addCriterion("zuul_prefix =", value, "zuulPrefix");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixNotEqualTo(String value) {
            addCriterion("zuul_prefix <>", value, "zuulPrefix");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixGreaterThan(String value) {
            addCriterion("zuul_prefix >", value, "zuulPrefix");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixGreaterThanOrEqualTo(String value) {
            addCriterion("zuul_prefix >=", value, "zuulPrefix");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixLessThan(String value) {
            addCriterion("zuul_prefix <", value, "zuulPrefix");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixLessThanOrEqualTo(String value) {
            addCriterion("zuul_prefix <=", value, "zuulPrefix");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixLike(String value) {
            addCriterion("zuul_prefix like", value, "zuulPrefix");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixNotLike(String value) {
            addCriterion("zuul_prefix not like", value, "zuulPrefix");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixIn(List<String> values) {
            addCriterion("zuul_prefix in", values, "zuulPrefix");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixNotIn(List<String> values) {
            addCriterion("zuul_prefix not in", values, "zuulPrefix");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixBetween(String value1, String value2) {
            addCriterion("zuul_prefix between", value1, value2, "zuulPrefix");
            return (Criteria) this;
        }

        public Criteria andZuulPrefixNotBetween(String value1, String value2) {
            addCriterion("zuul_prefix not between", value1, value2, "zuulPrefix");
            return (Criteria) this;
        }

        public Criteria andServerMethodIsNull() {
            addCriterion("server_method is null");
            return (Criteria) this;
        }

        public Criteria andServerMethodIsNotNull() {
            addCriterion("server_method is not null");
            return (Criteria) this;
        }

        public Criteria andServerMethodEqualTo(String value) {
            addCriterion("server_method =", value, "serverMethod");
            return (Criteria) this;
        }

        public Criteria andServerMethodNotEqualTo(String value) {
            addCriterion("server_method <>", value, "serverMethod");
            return (Criteria) this;
        }

        public Criteria andServerMethodGreaterThan(String value) {
            addCriterion("server_method >", value, "serverMethod");
            return (Criteria) this;
        }

        public Criteria andServerMethodGreaterThanOrEqualTo(String value) {
            addCriterion("server_method >=", value, "serverMethod");
            return (Criteria) this;
        }

        public Criteria andServerMethodLessThan(String value) {
            addCriterion("server_method <", value, "serverMethod");
            return (Criteria) this;
        }

        public Criteria andServerMethodLessThanOrEqualTo(String value) {
            addCriterion("server_method <=", value, "serverMethod");
            return (Criteria) this;
        }

        public Criteria andServerMethodLike(String value) {
            addCriterion("server_method like", value, "serverMethod");
            return (Criteria) this;
        }

        public Criteria andServerMethodNotLike(String value) {
            addCriterion("server_method not like", value, "serverMethod");
            return (Criteria) this;
        }

        public Criteria andServerMethodIn(List<String> values) {
            addCriterion("server_method in", values, "serverMethod");
            return (Criteria) this;
        }

        public Criteria andServerMethodNotIn(List<String> values) {
            addCriterion("server_method not in", values, "serverMethod");
            return (Criteria) this;
        }

        public Criteria andServerMethodBetween(String value1, String value2) {
            addCriterion("server_method between", value1, value2, "serverMethod");
            return (Criteria) this;
        }

        public Criteria andServerMethodNotBetween(String value1, String value2) {
            addCriterion("server_method not between", value1, value2, "serverMethod");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Boolean value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Boolean value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Boolean value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Boolean value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Boolean> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Boolean> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}