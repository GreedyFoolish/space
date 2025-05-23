package demo.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlmnCommentExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	public AlmnCommentExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<>();
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

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andDailysIdIsNull() {
			addCriterion("dailys_id is null");
			return (Criteria) this;
		}

		public Criteria andDailysIdIsNotNull() {
			addCriterion("dailys_id is not null");
			return (Criteria) this;
		}

		public Criteria andDailysIdEqualTo(String value) {
			addCriterion("dailys_id =", value, "dailysId");
			return (Criteria) this;
		}

		public Criteria andDailysIdNotEqualTo(String value) {
			addCriterion("dailys_id <>", value, "dailysId");
			return (Criteria) this;
		}

		public Criteria andDailysIdGreaterThan(String value) {
			addCriterion("dailys_id >", value, "dailysId");
			return (Criteria) this;
		}

		public Criteria andDailysIdGreaterThanOrEqualTo(String value) {
			addCriterion("dailys_id >=", value, "dailysId");
			return (Criteria) this;
		}

		public Criteria andDailysIdLessThan(String value) {
			addCriterion("dailys_id <", value, "dailysId");
			return (Criteria) this;
		}

		public Criteria andDailysIdLessThanOrEqualTo(String value) {
			addCriterion("dailys_id <=", value, "dailysId");
			return (Criteria) this;
		}

		public Criteria andDailysIdLike(String value) {
			addCriterion("dailys_id like", value, "dailysId");
			return (Criteria) this;
		}

		public Criteria andDailysIdNotLike(String value) {
			addCriterion("dailys_id not like", value, "dailysId");
			return (Criteria) this;
		}

		public Criteria andDailysIdIn(List<String> values) {
			addCriterion("dailys_id in", values, "dailysId");
			return (Criteria) this;
		}

		public Criteria andDailysIdNotIn(List<String> values) {
			addCriterion("dailys_id not in", values, "dailysId");
			return (Criteria) this;
		}

		public Criteria andDailysIdBetween(String value1, String value2) {
			addCriterion("dailys_id between", value1, value2, "dailysId");
			return (Criteria) this;
		}

		public Criteria andDailysIdNotBetween(String value1, String value2) {
			addCriterion("dailys_id not between", value1, value2, "dailysId");
			return (Criteria) this;
		}

		public Criteria andComment1IdIsNull() {
			addCriterion("comment1_id is null");
			return (Criteria) this;
		}

		public Criteria andComment1IdIsNotNull() {
			addCriterion("comment1_id is not null");
			return (Criteria) this;
		}

		public Criteria andComment1IdEqualTo(String value) {
			addCriterion("comment1_id =", value, "comment1Id");
			return (Criteria) this;
		}

		public Criteria andComment1IdNotEqualTo(String value) {
			addCriterion("comment1_id <>", value, "comment1Id");
			return (Criteria) this;
		}

		public Criteria andComment1IdGreaterThan(String value) {
			addCriterion("comment1_id >", value, "comment1Id");
			return (Criteria) this;
		}

		public Criteria andComment1IdGreaterThanOrEqualTo(String value) {
			addCriterion("comment1_id >=", value, "comment1Id");
			return (Criteria) this;
		}

		public Criteria andComment1IdLessThan(String value) {
			addCriterion("comment1_id <", value, "comment1Id");
			return (Criteria) this;
		}

		public Criteria andComment1IdLessThanOrEqualTo(String value) {
			addCriterion("comment1_id <=", value, "comment1Id");
			return (Criteria) this;
		}

		public Criteria andComment1IdLike(String value) {
			addCriterion("comment1_id like", value, "comment1Id");
			return (Criteria) this;
		}

		public Criteria andComment1IdNotLike(String value) {
			addCriterion("comment1_id not like", value, "comment1Id");
			return (Criteria) this;
		}

		public Criteria andComment1IdIn(List<String> values) {
			addCriterion("comment1_id in", values, "comment1Id");
			return (Criteria) this;
		}

		public Criteria andComment1IdNotIn(List<String> values) {
			addCriterion("comment1_id not in", values, "comment1Id");
			return (Criteria) this;
		}

		public Criteria andComment1IdBetween(String value1, String value2) {
			addCriterion("comment1_id between", value1, value2, "comment1Id");
			return (Criteria) this;
		}

		public Criteria andComment1IdNotBetween(String value1, String value2) {
			addCriterion("comment1_id not between", value1, value2, "comment1Id");
			return (Criteria) this;
		}

		public Criteria andComment2IdIsNull() {
			addCriterion("comment2_id is null");
			return (Criteria) this;
		}

		public Criteria andComment2IdIsNotNull() {
			addCriterion("comment2_id is not null");
			return (Criteria) this;
		}

		public Criteria andComment2IdEqualTo(String value) {
			addCriterion("comment2_id =", value, "comment2Id");
			return (Criteria) this;
		}

		public Criteria andComment2IdNotEqualTo(String value) {
			addCriterion("comment2_id <>", value, "comment2Id");
			return (Criteria) this;
		}

		public Criteria andComment2IdGreaterThan(String value) {
			addCriterion("comment2_id >", value, "comment2Id");
			return (Criteria) this;
		}

		public Criteria andComment2IdGreaterThanOrEqualTo(String value) {
			addCriterion("comment2_id >=", value, "comment2Id");
			return (Criteria) this;
		}

		public Criteria andComment2IdLessThan(String value) {
			addCriterion("comment2_id <", value, "comment2Id");
			return (Criteria) this;
		}

		public Criteria andComment2IdLessThanOrEqualTo(String value) {
			addCriterion("comment2_id <=", value, "comment2Id");
			return (Criteria) this;
		}

		public Criteria andComment2IdLike(String value) {
			addCriterion("comment2_id like", value, "comment2Id");
			return (Criteria) this;
		}

		public Criteria andComment2IdNotLike(String value) {
			addCriterion("comment2_id not like", value, "comment2Id");
			return (Criteria) this;
		}

		public Criteria andComment2IdIn(List<String> values) {
			addCriterion("comment2_id in", values, "comment2Id");
			return (Criteria) this;
		}

		public Criteria andComment2IdNotIn(List<String> values) {
			addCriterion("comment2_id not in", values, "comment2Id");
			return (Criteria) this;
		}

		public Criteria andComment2IdBetween(String value1, String value2) {
			addCriterion("comment2_id between", value1, value2, "comment2Id");
			return (Criteria) this;
		}

		public Criteria andComment2IdNotBetween(String value1, String value2) {
			addCriterion("comment2_id not between", value1, value2, "comment2Id");
			return (Criteria) this;
		}

		public Criteria andCommentContentIsNull() {
			addCriterion("comment_content is null");
			return (Criteria) this;
		}

		public Criteria andCommentContentIsNotNull() {
			addCriterion("comment_content is not null");
			return (Criteria) this;
		}

		public Criteria andCommentContentEqualTo(String value) {
			addCriterion("comment_content =", value, "commentContent");
			return (Criteria) this;
		}

		public Criteria andCommentContentNotEqualTo(String value) {
			addCriterion("comment_content <>", value, "commentContent");
			return (Criteria) this;
		}

		public Criteria andCommentContentGreaterThan(String value) {
			addCriterion("comment_content >", value, "commentContent");
			return (Criteria) this;
		}

		public Criteria andCommentContentGreaterThanOrEqualTo(String value) {
			addCriterion("comment_content >=", value, "commentContent");
			return (Criteria) this;
		}

		public Criteria andCommentContentLessThan(String value) {
			addCriterion("comment_content <", value, "commentContent");
			return (Criteria) this;
		}

		public Criteria andCommentContentLessThanOrEqualTo(String value) {
			addCriterion("comment_content <=", value, "commentContent");
			return (Criteria) this;
		}

		public Criteria andCommentContentLike(String value) {
			addCriterion("comment_content like", value, "commentContent");
			return (Criteria) this;
		}

		public Criteria andCommentContentNotLike(String value) {
			addCriterion("comment_content not like", value, "commentContent");
			return (Criteria) this;
		}

		public Criteria andCommentContentIn(List<String> values) {
			addCriterion("comment_content in", values, "commentContent");
			return (Criteria) this;
		}

		public Criteria andCommentContentNotIn(List<String> values) {
			addCriterion("comment_content not in", values, "commentContent");
			return (Criteria) this;
		}

		public Criteria andCommentContentBetween(String value1, String value2) {
			addCriterion("comment_content between", value1, value2, "commentContent");
			return (Criteria) this;
		}

		public Criteria andCommentContentNotBetween(String value1, String value2) {
			addCriterion("comment_content not between", value1, value2, "commentContent");
			return (Criteria) this;
		}

		public Criteria andAddtimeIsNull() {
			addCriterion("addtime is null");
			return (Criteria) this;
		}

		public Criteria andAddtimeIsNotNull() {
			addCriterion("addtime is not null");
			return (Criteria) this;
		}

		public Criteria andAddtimeEqualTo(Date value) {
			addCriterion("addtime =", value, "addtime");
			return (Criteria) this;
		}

		public Criteria andAddtimeNotEqualTo(Date value) {
			addCriterion("addtime <>", value, "addtime");
			return (Criteria) this;
		}

		public Criteria andAddtimeGreaterThan(Date value) {
			addCriterion("addtime >", value, "addtime");
			return (Criteria) this;
		}

		public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
			addCriterion("addtime >=", value, "addtime");
			return (Criteria) this;
		}

		public Criteria andAddtimeLessThan(Date value) {
			addCriterion("addtime <", value, "addtime");
			return (Criteria) this;
		}

		public Criteria andAddtimeLessThanOrEqualTo(Date value) {
			addCriterion("addtime <=", value, "addtime");
			return (Criteria) this;
		}

		public Criteria andAddtimeIn(List<Date> values) {
			addCriterion("addtime in", values, "addtime");
			return (Criteria) this;
		}

		public Criteria andAddtimeNotIn(List<Date> values) {
			addCriterion("addtime not in", values, "addtime");
			return (Criteria) this;
		}

		public Criteria andAddtimeBetween(Date value1, Date value2) {
			addCriterion("addtime between", value1, value2, "addtime");
			return (Criteria) this;
		}

		public Criteria andAddtimeNotBetween(Date value1, Date value2) {
			addCriterion("addtime not between", value1, value2, "addtime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeIsNull() {
			addCriterion("updatetime is null");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeIsNotNull() {
			addCriterion("updatetime is not null");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeEqualTo(Date value) {
			addCriterion("updatetime =", value, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeNotEqualTo(Date value) {
			addCriterion("updatetime <>", value, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeGreaterThan(Date value) {
			addCriterion("updatetime >", value, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
			addCriterion("updatetime >=", value, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeLessThan(Date value) {
			addCriterion("updatetime <", value, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
			addCriterion("updatetime <=", value, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeIn(List<Date> values) {
			addCriterion("updatetime in", values, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeNotIn(List<Date> values) {
			addCriterion("updatetime not in", values, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeBetween(Date value1, Date value2) {
			addCriterion("updatetime between", value1, value2, "updatetime");
			return (Criteria) this;
		}

		public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
			addCriterion("updatetime not between", value1, value2, "updatetime");
			return (Criteria) this;
		}

		public Criteria andDeltimeIsNull() {
			addCriterion("deltime is null");
			return (Criteria) this;
		}

		public Criteria andDeltimeIsNotNull() {
			addCriterion("deltime is not null");
			return (Criteria) this;
		}

		public Criteria andDeltimeEqualTo(Integer value) {
			addCriterion("deltime =", value, "deltime");
			return (Criteria) this;
		}

		public Criteria andDeltimeNotEqualTo(Integer value) {
			addCriterion("deltime <>", value, "deltime");
			return (Criteria) this;
		}

		public Criteria andDeltimeGreaterThan(Integer value) {
			addCriterion("deltime >", value, "deltime");
			return (Criteria) this;
		}

		public Criteria andDeltimeGreaterThanOrEqualTo(Integer value) {
			addCriterion("deltime >=", value, "deltime");
			return (Criteria) this;
		}

		public Criteria andDeltimeLessThan(Integer value) {
			addCriterion("deltime <", value, "deltime");
			return (Criteria) this;
		}

		public Criteria andDeltimeLessThanOrEqualTo(Integer value) {
			addCriterion("deltime <=", value, "deltime");
			return (Criteria) this;
		}

		public Criteria andDeltimeIn(List<Integer> values) {
			addCriterion("deltime in", values, "deltime");
			return (Criteria) this;
		}

		public Criteria andDeltimeNotIn(List<Integer> values) {
			addCriterion("deltime not in", values, "deltime");
			return (Criteria) this;
		}

		public Criteria andDeltimeBetween(Integer value1, Integer value2) {
			addCriterion("deltime between", value1, value2, "deltime");
			return (Criteria) this;
		}

		public Criteria andDeltimeNotBetween(Integer value1, Integer value2) {
			addCriterion("deltime not between", value1, value2, "deltime");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table almn_comment
	 * @mbg.generated  Thu Apr 22 13:25:30 CST 2021
	 */
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table almn_comment
     *
     * @mbg.generated do_not_delete_during_merge Wed Apr 21 17:00:47 CST 2021
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}