package demo.dao.mapper;

import demo.dao.model.AlmnCommentAnls;
import demo.dao.model.AlmnCommentAnlsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlmnCommentAnlsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment_anls
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	long countByExample(AlmnCommentAnlsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment_anls
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	int deleteByExample(AlmnCommentAnlsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment_anls
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment_anls
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	int insert(AlmnCommentAnls record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment_anls
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	int insertSelective(AlmnCommentAnls record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment_anls
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	List<AlmnCommentAnls> selectByExample(AlmnCommentAnlsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment_anls
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	AlmnCommentAnls selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment_anls
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	int updateByExampleSelective(@Param("record") AlmnCommentAnls record,
			@Param("example") AlmnCommentAnlsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment_anls
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	int updateByExample(@Param("record") AlmnCommentAnls record, @Param("example") AlmnCommentAnlsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment_anls
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	int updateByPrimaryKeySelective(AlmnCommentAnls record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_comment_anls
	 * @mbg.generated  Thu May 27 15:24:54 CST 2021
	 */
	int updateByPrimaryKey(AlmnCommentAnls record);
}