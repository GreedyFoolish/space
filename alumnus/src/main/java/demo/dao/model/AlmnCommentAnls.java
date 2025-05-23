package demo.dao.model;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table almn_comment_anls
 */
public class AlmnCommentAnls extends BaseModel {

	/**
	 * 好评关键词
	 * @ColumnNamepraise_set
	 * @mbg.generated   2021-05-27 15:24:54
	 */
	private String praiseSet;
	/**
	 * 差评关键词
	 * @ColumnNamenegative_set
	 * @mbg.generated   2021-05-27 15:24:54
	 */
	private String negativeSet;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column almn_comment_anls.praise_set
	 * @return  the value of almn_comment_anls.praise_set
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	public String getPraiseSet() {
		return praiseSet;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column almn_comment_anls.praise_set
	 * @param praiseSet  the value for almn_comment_anls.praise_set
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	public void setPraiseSet(String praiseSet) {
		this.praiseSet = praiseSet == null ? null : praiseSet.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column almn_comment_anls.negative_set
	 * @return  the value of almn_comment_anls.negative_set
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	public String getNegativeSet() {
		return negativeSet;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column almn_comment_anls.negative_set
	 * @param negativeSet  the value for almn_comment_anls.negative_set
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	public void setNegativeSet(String negativeSet) {
		this.negativeSet = negativeSet == null ? null : negativeSet.trim();
	}
}