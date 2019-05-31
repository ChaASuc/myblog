package com.deschen.myblog.modules.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserConfigExample() {
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

        public Criteria andConfigIdIsNull() {
            addCriterion("config_id is null");
            return (Criteria) this;
        }

        public Criteria andConfigIdIsNotNull() {
            addCriterion("config_id is not null");
            return (Criteria) this;
        }

        public Criteria andConfigIdEqualTo(Long value) {
            addCriterion("config_id =", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdNotEqualTo(Long value) {
            addCriterion("config_id <>", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdGreaterThan(Long value) {
            addCriterion("config_id >", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdGreaterThanOrEqualTo(Long value) {
            addCriterion("config_id >=", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdLessThan(Long value) {
            addCriterion("config_id <", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdLessThanOrEqualTo(Long value) {
            addCriterion("config_id <=", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdIn(List<Long> values) {
            addCriterion("config_id in", values, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdNotIn(List<Long> values) {
            addCriterion("config_id not in", values, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdBetween(Long value1, Long value2) {
            addCriterion("config_id between", value1, value2, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdNotBetween(Long value1, Long value2) {
            addCriterion("config_id not between", value1, value2, "configId");
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

        public Criteria andArticleSumIsNull() {
            addCriterion("article_sum is null");
            return (Criteria) this;
        }

        public Criteria andArticleSumIsNotNull() {
            addCriterion("article_sum is not null");
            return (Criteria) this;
        }

        public Criteria andArticleSumEqualTo(Integer value) {
            addCriterion("article_sum =", value, "articleSum");
            return (Criteria) this;
        }

        public Criteria andArticleSumNotEqualTo(Integer value) {
            addCriterion("article_sum <>", value, "articleSum");
            return (Criteria) this;
        }

        public Criteria andArticleSumGreaterThan(Integer value) {
            addCriterion("article_sum >", value, "articleSum");
            return (Criteria) this;
        }

        public Criteria andArticleSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_sum >=", value, "articleSum");
            return (Criteria) this;
        }

        public Criteria andArticleSumLessThan(Integer value) {
            addCriterion("article_sum <", value, "articleSum");
            return (Criteria) this;
        }

        public Criteria andArticleSumLessThanOrEqualTo(Integer value) {
            addCriterion("article_sum <=", value, "articleSum");
            return (Criteria) this;
        }

        public Criteria andArticleSumIn(List<Integer> values) {
            addCriterion("article_sum in", values, "articleSum");
            return (Criteria) this;
        }

        public Criteria andArticleSumNotIn(List<Integer> values) {
            addCriterion("article_sum not in", values, "articleSum");
            return (Criteria) this;
        }

        public Criteria andArticleSumBetween(Integer value1, Integer value2) {
            addCriterion("article_sum between", value1, value2, "articleSum");
            return (Criteria) this;
        }

        public Criteria andArticleSumNotBetween(Integer value1, Integer value2) {
            addCriterion("article_sum not between", value1, value2, "articleSum");
            return (Criteria) this;
        }

        public Criteria andVisitSumIsNull() {
            addCriterion("visit_sum is null");
            return (Criteria) this;
        }

        public Criteria andVisitSumIsNotNull() {
            addCriterion("visit_sum is not null");
            return (Criteria) this;
        }

        public Criteria andVisitSumEqualTo(Integer value) {
            addCriterion("visit_sum =", value, "visitSum");
            return (Criteria) this;
        }

        public Criteria andVisitSumNotEqualTo(Integer value) {
            addCriterion("visit_sum <>", value, "visitSum");
            return (Criteria) this;
        }

        public Criteria andVisitSumGreaterThan(Integer value) {
            addCriterion("visit_sum >", value, "visitSum");
            return (Criteria) this;
        }

        public Criteria andVisitSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("visit_sum >=", value, "visitSum");
            return (Criteria) this;
        }

        public Criteria andVisitSumLessThan(Integer value) {
            addCriterion("visit_sum <", value, "visitSum");
            return (Criteria) this;
        }

        public Criteria andVisitSumLessThanOrEqualTo(Integer value) {
            addCriterion("visit_sum <=", value, "visitSum");
            return (Criteria) this;
        }

        public Criteria andVisitSumIn(List<Integer> values) {
            addCriterion("visit_sum in", values, "visitSum");
            return (Criteria) this;
        }

        public Criteria andVisitSumNotIn(List<Integer> values) {
            addCriterion("visit_sum not in", values, "visitSum");
            return (Criteria) this;
        }

        public Criteria andVisitSumBetween(Integer value1, Integer value2) {
            addCriterion("visit_sum between", value1, value2, "visitSum");
            return (Criteria) this;
        }

        public Criteria andVisitSumNotBetween(Integer value1, Integer value2) {
            addCriterion("visit_sum not between", value1, value2, "visitSum");
            return (Criteria) this;
        }

        public Criteria andThumbupSumIsNull() {
            addCriterion("thumbup_sum is null");
            return (Criteria) this;
        }

        public Criteria andThumbupSumIsNotNull() {
            addCriterion("thumbup_sum is not null");
            return (Criteria) this;
        }

        public Criteria andThumbupSumEqualTo(Integer value) {
            addCriterion("thumbup_sum =", value, "thumbupSum");
            return (Criteria) this;
        }

        public Criteria andThumbupSumNotEqualTo(Integer value) {
            addCriterion("thumbup_sum <>", value, "thumbupSum");
            return (Criteria) this;
        }

        public Criteria andThumbupSumGreaterThan(Integer value) {
            addCriterion("thumbup_sum >", value, "thumbupSum");
            return (Criteria) this;
        }

        public Criteria andThumbupSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("thumbup_sum >=", value, "thumbupSum");
            return (Criteria) this;
        }

        public Criteria andThumbupSumLessThan(Integer value) {
            addCriterion("thumbup_sum <", value, "thumbupSum");
            return (Criteria) this;
        }

        public Criteria andThumbupSumLessThanOrEqualTo(Integer value) {
            addCriterion("thumbup_sum <=", value, "thumbupSum");
            return (Criteria) this;
        }

        public Criteria andThumbupSumIn(List<Integer> values) {
            addCriterion("thumbup_sum in", values, "thumbupSum");
            return (Criteria) this;
        }

        public Criteria andThumbupSumNotIn(List<Integer> values) {
            addCriterion("thumbup_sum not in", values, "thumbupSum");
            return (Criteria) this;
        }

        public Criteria andThumbupSumBetween(Integer value1, Integer value2) {
            addCriterion("thumbup_sum between", value1, value2, "thumbupSum");
            return (Criteria) this;
        }

        public Criteria andThumbupSumNotBetween(Integer value1, Integer value2) {
            addCriterion("thumbup_sum not between", value1, value2, "thumbupSum");
            return (Criteria) this;
        }

        public Criteria andCommentSumIsNull() {
            addCriterion("comment_sum is null");
            return (Criteria) this;
        }

        public Criteria andCommentSumIsNotNull() {
            addCriterion("comment_sum is not null");
            return (Criteria) this;
        }

        public Criteria andCommentSumEqualTo(Integer value) {
            addCriterion("comment_sum =", value, "commentSum");
            return (Criteria) this;
        }

        public Criteria andCommentSumNotEqualTo(Integer value) {
            addCriterion("comment_sum <>", value, "commentSum");
            return (Criteria) this;
        }

        public Criteria andCommentSumGreaterThan(Integer value) {
            addCriterion("comment_sum >", value, "commentSum");
            return (Criteria) this;
        }

        public Criteria andCommentSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_sum >=", value, "commentSum");
            return (Criteria) this;
        }

        public Criteria andCommentSumLessThan(Integer value) {
            addCriterion("comment_sum <", value, "commentSum");
            return (Criteria) this;
        }

        public Criteria andCommentSumLessThanOrEqualTo(Integer value) {
            addCriterion("comment_sum <=", value, "commentSum");
            return (Criteria) this;
        }

        public Criteria andCommentSumIn(List<Integer> values) {
            addCriterion("comment_sum in", values, "commentSum");
            return (Criteria) this;
        }

        public Criteria andCommentSumNotIn(List<Integer> values) {
            addCriterion("comment_sum not in", values, "commentSum");
            return (Criteria) this;
        }

        public Criteria andCommentSumBetween(Integer value1, Integer value2) {
            addCriterion("comment_sum between", value1, value2, "commentSum");
            return (Criteria) this;
        }

        public Criteria andCommentSumNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_sum not between", value1, value2, "commentSum");
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