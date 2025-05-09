package demo.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlmnNavExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	public AlmnNavExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
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

		public Criteria andParentIdIsNull() {
			addCriterion("parent_id is null");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNotNull() {
			addCriterion("parent_id is not null");
			return (Criteria) this;
		}

		public Criteria andParentIdEqualTo(Integer value) {
			addCriterion("parent_id =", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotEqualTo(Integer value) {
			addCriterion("parent_id <>", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThan(Integer value) {
			addCriterion("parent_id >", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("parent_id >=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThan(Integer value) {
			addCriterion("parent_id <", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThanOrEqualTo(Integer value) {
			addCriterion("parent_id <=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdIn(List<Integer> values) {
			addCriterion("parent_id in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotIn(List<Integer> values) {
			addCriterion("parent_id not in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdBetween(Integer value1, Integer value2) {
			addCriterion("parent_id between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("parent_id not between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andNavNameIsNull() {
			addCriterion("nav_name is null");
			return (Criteria) this;
		}

		public Criteria andNavNameIsNotNull() {
			addCriterion("nav_name is not null");
			return (Criteria) this;
		}

		public Criteria andNavNameEqualTo(String value) {
			addCriterion("nav_name =", value, "navName");
			return (Criteria) this;
		}

		public Criteria andNavNameNotEqualTo(String value) {
			addCriterion("nav_name <>", value, "navName");
			return (Criteria) this;
		}

		public Criteria andNavNameGreaterThan(String value) {
			addCriterion("nav_name >", value, "navName");
			return (Criteria) this;
		}

		public Criteria andNavNameGreaterThanOrEqualTo(String value) {
			addCriterion("nav_name >=", value, "navName");
			return (Criteria) this;
		}

		public Criteria andNavNameLessThan(String value) {
			addCriterion("nav_name <", value, "navName");
			return (Criteria) this;
		}

		public Criteria andNavNameLessThanOrEqualTo(String value) {
			addCriterion("nav_name <=", value, "navName");
			return (Criteria) this;
		}

		public Criteria andNavNameLike(String value) {
			addCriterion("nav_name like", value, "navName");
			return (Criteria) this;
		}

		public Criteria andNavNameNotLike(String value) {
			addCriterion("nav_name not like", value, "navName");
			return (Criteria) this;
		}

		public Criteria andNavNameIn(List<String> values) {
			addCriterion("nav_name in", values, "navName");
			return (Criteria) this;
		}

		public Criteria andNavNameNotIn(List<String> values) {
			addCriterion("nav_name not in", values, "navName");
			return (Criteria) this;
		}

		public Criteria andNavNameBetween(String value1, String value2) {
			addCriterion("nav_name between", value1, value2, "navName");
			return (Criteria) this;
		}

		public Criteria andNavNameNotBetween(String value1, String value2) {
			addCriterion("nav_name not between", value1, value2, "navName");
			return (Criteria) this;
		}

		public Criteria andNavUrlIsNull() {
			addCriterion("nav_url is null");
			return (Criteria) this;
		}

		public Criteria andNavUrlIsNotNull() {
			addCriterion("nav_url is not null");
			return (Criteria) this;
		}

		public Criteria andNavUrlEqualTo(String value) {
			addCriterion("nav_url =", value, "navUrl");
			return (Criteria) this;
		}

		public Criteria andNavUrlNotEqualTo(String value) {
			addCriterion("nav_url <>", value, "navUrl");
			return (Criteria) this;
		}

		public Criteria andNavUrlGreaterThan(String value) {
			addCriterion("nav_url >", value, "navUrl");
			return (Criteria) this;
		}

		public Criteria andNavUrlGreaterThanOrEqualTo(String value) {
			addCriterion("nav_url >=", value, "navUrl");
			return (Criteria) this;
		}

		public Criteria andNavUrlLessThan(String value) {
			addCriterion("nav_url <", value, "navUrl");
			return (Criteria) this;
		}

		public Criteria andNavUrlLessThanOrEqualTo(String value) {
			addCriterion("nav_url <=", value, "navUrl");
			return (Criteria) this;
		}

		public Criteria andNavUrlLike(String value) {
			addCriterion("nav_url like", value, "navUrl");
			return (Criteria) this;
		}

		public Criteria andNavUrlNotLike(String value) {
			addCriterion("nav_url not like", value, "navUrl");
			return (Criteria) this;
		}

		public Criteria andNavUrlIn(List<String> values) {
			addCriterion("nav_url in", values, "navUrl");
			return (Criteria) this;
		}

		public Criteria andNavUrlNotIn(List<String> values) {
			addCriterion("nav_url not in", values, "navUrl");
			return (Criteria) this;
		}

		public Criteria andNavUrlBetween(String value1, String value2) {
			addCriterion("nav_url between", value1, value2, "navUrl");
			return (Criteria) this;
		}

		public Criteria andNavUrlNotBetween(String value1, String value2) {
			addCriterion("nav_url not between", value1, value2, "navUrl");
			return (Criteria) this;
		}

		public Criteria andNavIconIsNull() {
			addCriterion("nav_icon is null");
			return (Criteria) this;
		}

		public Criteria andNavIconIsNotNull() {
			addCriterion("nav_icon is not null");
			return (Criteria) this;
		}

		public Criteria andNavIconEqualTo(String value) {
			addCriterion("nav_icon =", value, "navIcon");
			return (Criteria) this;
		}

		public Criteria andNavIconNotEqualTo(String value) {
			addCriterion("nav_icon <>", value, "navIcon");
			return (Criteria) this;
		}

		public Criteria andNavIconGreaterThan(String value) {
			addCriterion("nav_icon >", value, "navIcon");
			return (Criteria) this;
		}

		public Criteria andNavIconGreaterThanOrEqualTo(String value) {
			addCriterion("nav_icon >=", value, "navIcon");
			return (Criteria) this;
		}

		public Criteria andNavIconLessThan(String value) {
			addCriterion("nav_icon <", value, "navIcon");
			return (Criteria) this;
		}

		public Criteria andNavIconLessThanOrEqualTo(String value) {
			addCriterion("nav_icon <=", value, "navIcon");
			return (Criteria) this;
		}

		public Criteria andNavIconLike(String value) {
			addCriterion("nav_icon like", value, "navIcon");
			return (Criteria) this;
		}

		public Criteria andNavIconNotLike(String value) {
			addCriterion("nav_icon not like", value, "navIcon");
			return (Criteria) this;
		}

		public Criteria andNavIconIn(List<String> values) {
			addCriterion("nav_icon in", values, "navIcon");
			return (Criteria) this;
		}

		public Criteria andNavIconNotIn(List<String> values) {
			addCriterion("nav_icon not in", values, "navIcon");
			return (Criteria) this;
		}

		public Criteria andNavIconBetween(String value1, String value2) {
			addCriterion("nav_icon between", value1, value2, "navIcon");
			return (Criteria) this;
		}

		public Criteria andNavIconNotBetween(String value1, String value2) {
			addCriterion("nav_icon not between", value1, value2, "navIcon");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table almn_nav
	 * @mbg.generated  Wed May 12 16:23:44 CST 2021
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
     * This class corresponds to the database table almn_nav
     *
     * @mbg.generated do_not_delete_during_merge Wed Apr 21 17:00:47 CST 2021
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}