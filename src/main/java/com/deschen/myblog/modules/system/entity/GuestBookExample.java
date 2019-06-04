package com.deschen.myblog.modules.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestBookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GuestBookExample() {
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

        public Criteria andGuestbookIdIsNull() {
            addCriterion("guestbook_id is null");
            return (Criteria) this;
        }

        public Criteria andGuestbookIdIsNotNull() {
            addCriterion("guestbook_id is not null");
            return (Criteria) this;
        }

        public Criteria andGuestbookIdEqualTo(Long value) {
            addCriterion("guestbook_id =", value, "guestbookId");
            return (Criteria) this;
        }

        public Criteria andGuestbookIdNotEqualTo(Long value) {
            addCriterion("guestbook_id <>", value, "guestbookId");
            return (Criteria) this;
        }

        public Criteria andGuestbookIdGreaterThan(Long value) {
            addCriterion("guestbook_id >", value, "guestbookId");
            return (Criteria) this;
        }

        public Criteria andGuestbookIdGreaterThanOrEqualTo(Long value) {
            addCriterion("guestbook_id >=", value, "guestbookId");
            return (Criteria) this;
        }

        public Criteria andGuestbookIdLessThan(Long value) {
            addCriterion("guestbook_id <", value, "guestbookId");
            return (Criteria) this;
        }

        public Criteria andGuestbookIdLessThanOrEqualTo(Long value) {
            addCriterion("guestbook_id <=", value, "guestbookId");
            return (Criteria) this;
        }

        public Criteria andGuestbookIdIn(List<Long> values) {
            addCriterion("guestbook_id in", values, "guestbookId");
            return (Criteria) this;
        }

        public Criteria andGuestbookIdNotIn(List<Long> values) {
            addCriterion("guestbook_id not in", values, "guestbookId");
            return (Criteria) this;
        }

        public Criteria andGuestbookIdBetween(Long value1, Long value2) {
            addCriterion("guestbook_id between", value1, value2, "guestbookId");
            return (Criteria) this;
        }

        public Criteria andGuestbookIdNotBetween(Long value1, Long value2) {
            addCriterion("guestbook_id not between", value1, value2, "guestbookId");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentIsNull() {
            addCriterion("guestbook_content is null");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentIsNotNull() {
            addCriterion("guestbook_content is not null");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentEqualTo(String value) {
            addCriterion("guestbook_content =", value, "guestbookContent");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentNotEqualTo(String value) {
            addCriterion("guestbook_content <>", value, "guestbookContent");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentGreaterThan(String value) {
            addCriterion("guestbook_content >", value, "guestbookContent");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentGreaterThanOrEqualTo(String value) {
            addCriterion("guestbook_content >=", value, "guestbookContent");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentLessThan(String value) {
            addCriterion("guestbook_content <", value, "guestbookContent");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentLessThanOrEqualTo(String value) {
            addCriterion("guestbook_content <=", value, "guestbookContent");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentLike(String value) {
            addCriterion("guestbook_content like", value, "guestbookContent");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentNotLike(String value) {
            addCriterion("guestbook_content not like", value, "guestbookContent");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentIn(List<String> values) {
            addCriterion("guestbook_content in", values, "guestbookContent");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentNotIn(List<String> values) {
            addCriterion("guestbook_content not in", values, "guestbookContent");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentBetween(String value1, String value2) {
            addCriterion("guestbook_content between", value1, value2, "guestbookContent");
            return (Criteria) this;
        }

        public Criteria andGuestbookContentNotBetween(String value1, String value2) {
            addCriterion("guestbook_content not between", value1, value2, "guestbookContent");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
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

        public Criteria andGuestbookUrlIsNull() {
            addCriterion("guestbook_url is null");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlIsNotNull() {
            addCriterion("guestbook_url is not null");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlEqualTo(String value) {
            addCriterion("guestbook_url =", value, "guestbookUrl");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlNotEqualTo(String value) {
            addCriterion("guestbook_url <>", value, "guestbookUrl");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlGreaterThan(String value) {
            addCriterion("guestbook_url >", value, "guestbookUrl");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlGreaterThanOrEqualTo(String value) {
            addCriterion("guestbook_url >=", value, "guestbookUrl");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlLessThan(String value) {
            addCriterion("guestbook_url <", value, "guestbookUrl");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlLessThanOrEqualTo(String value) {
            addCriterion("guestbook_url <=", value, "guestbookUrl");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlLike(String value) {
            addCriterion("guestbook_url like", value, "guestbookUrl");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlNotLike(String value) {
            addCriterion("guestbook_url not like", value, "guestbookUrl");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlIn(List<String> values) {
            addCriterion("guestbook_url in", values, "guestbookUrl");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlNotIn(List<String> values) {
            addCriterion("guestbook_url not in", values, "guestbookUrl");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlBetween(String value1, String value2) {
            addCriterion("guestbook_url between", value1, value2, "guestbookUrl");
            return (Criteria) this;
        }

        public Criteria andGuestbookUrlNotBetween(String value1, String value2) {
            addCriterion("guestbook_url not between", value1, value2, "guestbookUrl");
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