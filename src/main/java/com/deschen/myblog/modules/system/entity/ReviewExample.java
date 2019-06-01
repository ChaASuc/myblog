package com.deschen.myblog.modules.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReviewExample() {
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

        public Criteria andReviewIdIsNull() {
            addCriterion("review_id is null");
            return (Criteria) this;
        }

        public Criteria andReviewIdIsNotNull() {
            addCriterion("review_id is not null");
            return (Criteria) this;
        }

        public Criteria andReviewIdEqualTo(Long value) {
            addCriterion("review_id =", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdNotEqualTo(Long value) {
            addCriterion("review_id <>", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdGreaterThan(Long value) {
            addCriterion("review_id >", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdGreaterThanOrEqualTo(Long value) {
            addCriterion("review_id >=", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdLessThan(Long value) {
            addCriterion("review_id <", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdLessThanOrEqualTo(Long value) {
            addCriterion("review_id <=", value, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdIn(List<Long> values) {
            addCriterion("review_id in", values, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdNotIn(List<Long> values) {
            addCriterion("review_id not in", values, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdBetween(Long value1, Long value2) {
            addCriterion("review_id between", value1, value2, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewIdNotBetween(Long value1, Long value2) {
            addCriterion("review_id not between", value1, value2, "reviewId");
            return (Criteria) this;
        }

        public Criteria andReviewContentIsNull() {
            addCriterion("review_content is null");
            return (Criteria) this;
        }

        public Criteria andReviewContentIsNotNull() {
            addCriterion("review_content is not null");
            return (Criteria) this;
        }

        public Criteria andReviewContentEqualTo(String value) {
            addCriterion("review_content =", value, "reviewContent");
            return (Criteria) this;
        }

        public Criteria andReviewContentNotEqualTo(String value) {
            addCriterion("review_content <>", value, "reviewContent");
            return (Criteria) this;
        }

        public Criteria andReviewContentGreaterThan(String value) {
            addCriterion("review_content >", value, "reviewContent");
            return (Criteria) this;
        }

        public Criteria andReviewContentGreaterThanOrEqualTo(String value) {
            addCriterion("review_content >=", value, "reviewContent");
            return (Criteria) this;
        }

        public Criteria andReviewContentLessThan(String value) {
            addCriterion("review_content <", value, "reviewContent");
            return (Criteria) this;
        }

        public Criteria andReviewContentLessThanOrEqualTo(String value) {
            addCriterion("review_content <=", value, "reviewContent");
            return (Criteria) this;
        }

        public Criteria andReviewContentLike(String value) {
            addCriterion("review_content like", value, "reviewContent");
            return (Criteria) this;
        }

        public Criteria andReviewContentNotLike(String value) {
            addCriterion("review_content not like", value, "reviewContent");
            return (Criteria) this;
        }

        public Criteria andReviewContentIn(List<String> values) {
            addCriterion("review_content in", values, "reviewContent");
            return (Criteria) this;
        }

        public Criteria andReviewContentNotIn(List<String> values) {
            addCriterion("review_content not in", values, "reviewContent");
            return (Criteria) this;
        }

        public Criteria andReviewContentBetween(String value1, String value2) {
            addCriterion("review_content between", value1, value2, "reviewContent");
            return (Criteria) this;
        }

        public Criteria andReviewContentNotBetween(String value1, String value2) {
            addCriterion("review_content not between", value1, value2, "reviewContent");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNull() {
            addCriterion("article_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNotNull() {
            addCriterion("article_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleIdEqualTo(Long value) {
            addCriterion("article_id =", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotEqualTo(Long value) {
            addCriterion("article_id <>", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThan(Long value) {
            addCriterion("article_id >", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("article_id >=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThan(Long value) {
            addCriterion("article_id <", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThanOrEqualTo(Long value) {
            addCriterion("article_id <=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIn(List<Long> values) {
            addCriterion("article_id in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotIn(List<Long> values) {
            addCriterion("article_id not in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdBetween(Long value1, Long value2) {
            addCriterion("article_id between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotBetween(Long value1, Long value2) {
            addCriterion("article_id not between", value1, value2, "articleId");
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

        public Criteria andReviewParentIsNull() {
            addCriterion("review_parent is null");
            return (Criteria) this;
        }

        public Criteria andReviewParentIsNotNull() {
            addCriterion("review_parent is not null");
            return (Criteria) this;
        }

        public Criteria andReviewParentEqualTo(Long value) {
            addCriterion("review_parent =", value, "reviewParent");
            return (Criteria) this;
        }

        public Criteria andReviewParentNotEqualTo(Long value) {
            addCriterion("review_parent <>", value, "reviewParent");
            return (Criteria) this;
        }

        public Criteria andReviewParentGreaterThan(Long value) {
            addCriterion("review_parent >", value, "reviewParent");
            return (Criteria) this;
        }

        public Criteria andReviewParentGreaterThanOrEqualTo(Long value) {
            addCriterion("review_parent >=", value, "reviewParent");
            return (Criteria) this;
        }

        public Criteria andReviewParentLessThan(Long value) {
            addCriterion("review_parent <", value, "reviewParent");
            return (Criteria) this;
        }

        public Criteria andReviewParentLessThanOrEqualTo(Long value) {
            addCriterion("review_parent <=", value, "reviewParent");
            return (Criteria) this;
        }

        public Criteria andReviewParentIn(List<Long> values) {
            addCriterion("review_parent in", values, "reviewParent");
            return (Criteria) this;
        }

        public Criteria andReviewParentNotIn(List<Long> values) {
            addCriterion("review_parent not in", values, "reviewParent");
            return (Criteria) this;
        }

        public Criteria andReviewParentBetween(Long value1, Long value2) {
            addCriterion("review_parent between", value1, value2, "reviewParent");
            return (Criteria) this;
        }

        public Criteria andReviewParentNotBetween(Long value1, Long value2) {
            addCriterion("review_parent not between", value1, value2, "reviewParent");
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

        public Criteria andReviewUrlIsNull() {
            addCriterion("review_url is null");
            return (Criteria) this;
        }

        public Criteria andReviewUrlIsNotNull() {
            addCriterion("review_url is not null");
            return (Criteria) this;
        }

        public Criteria andReviewUrlEqualTo(String value) {
            addCriterion("review_url =", value, "reviewUrl");
            return (Criteria) this;
        }

        public Criteria andReviewUrlNotEqualTo(String value) {
            addCriterion("review_url <>", value, "reviewUrl");
            return (Criteria) this;
        }

        public Criteria andReviewUrlGreaterThan(String value) {
            addCriterion("review_url >", value, "reviewUrl");
            return (Criteria) this;
        }

        public Criteria andReviewUrlGreaterThanOrEqualTo(String value) {
            addCriterion("review_url >=", value, "reviewUrl");
            return (Criteria) this;
        }

        public Criteria andReviewUrlLessThan(String value) {
            addCriterion("review_url <", value, "reviewUrl");
            return (Criteria) this;
        }

        public Criteria andReviewUrlLessThanOrEqualTo(String value) {
            addCriterion("review_url <=", value, "reviewUrl");
            return (Criteria) this;
        }

        public Criteria andReviewUrlLike(String value) {
            addCriterion("review_url like", value, "reviewUrl");
            return (Criteria) this;
        }

        public Criteria andReviewUrlNotLike(String value) {
            addCriterion("review_url not like", value, "reviewUrl");
            return (Criteria) this;
        }

        public Criteria andReviewUrlIn(List<String> values) {
            addCriterion("review_url in", values, "reviewUrl");
            return (Criteria) this;
        }

        public Criteria andReviewUrlNotIn(List<String> values) {
            addCriterion("review_url not in", values, "reviewUrl");
            return (Criteria) this;
        }

        public Criteria andReviewUrlBetween(String value1, String value2) {
            addCriterion("review_url between", value1, value2, "reviewUrl");
            return (Criteria) this;
        }

        public Criteria andReviewUrlNotBetween(String value1, String value2) {
            addCriterion("review_url not between", value1, value2, "reviewUrl");
            return (Criteria) this;
        }

        public Criteria andReviewAreaIdIsNull() {
            addCriterion("review_area_id is null");
            return (Criteria) this;
        }

        public Criteria andReviewAreaIdIsNotNull() {
            addCriterion("review_area_id is not null");
            return (Criteria) this;
        }

        public Criteria andReviewAreaIdEqualTo(Long value) {
            addCriterion("review_area_id =", value, "reviewAreaId");
            return (Criteria) this;
        }

        public Criteria andReviewAreaIdNotEqualTo(Long value) {
            addCriterion("review_area_id <>", value, "reviewAreaId");
            return (Criteria) this;
        }

        public Criteria andReviewAreaIdGreaterThan(Long value) {
            addCriterion("review_area_id >", value, "reviewAreaId");
            return (Criteria) this;
        }

        public Criteria andReviewAreaIdGreaterThanOrEqualTo(Long value) {
            addCriterion("review_area_id >=", value, "reviewAreaId");
            return (Criteria) this;
        }

        public Criteria andReviewAreaIdLessThan(Long value) {
            addCriterion("review_area_id <", value, "reviewAreaId");
            return (Criteria) this;
        }

        public Criteria andReviewAreaIdLessThanOrEqualTo(Long value) {
            addCriterion("review_area_id <=", value, "reviewAreaId");
            return (Criteria) this;
        }

        public Criteria andReviewAreaIdIn(List<Long> values) {
            addCriterion("review_area_id in", values, "reviewAreaId");
            return (Criteria) this;
        }

        public Criteria andReviewAreaIdNotIn(List<Long> values) {
            addCriterion("review_area_id not in", values, "reviewAreaId");
            return (Criteria) this;
        }

        public Criteria andReviewAreaIdBetween(Long value1, Long value2) {
            addCriterion("review_area_id between", value1, value2, "reviewAreaId");
            return (Criteria) this;
        }

        public Criteria andReviewAreaIdNotBetween(Long value1, Long value2) {
            addCriterion("review_area_id not between", value1, value2, "reviewAreaId");
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