package demo.dao.mapper;

import demo.dao.model.AlmnInfoCheck;
import demo.dao.model.AlmnInfoCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlmnInfoCheckMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_info_check
	 * @mbg.generated  Wed Apr 21 17:00:47 CST 2021
	 */
	long countByExample(AlmnInfoCheckExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_info_check
	 * @mbg.generated  Wed Apr 21 17:00:47 CST 2021
	 */
	int deleteByExample(AlmnInfoCheckExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_info_check
	 * @mbg.generated  Wed Apr 21 17:00:47 CST 2021
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_info_check
	 * @mbg.generated  Wed Apr 21 17:00:47 CST 2021
	 */
	int insert(AlmnInfoCheck record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_info_check
	 * @mbg.generated  Wed Apr 21 17:00:47 CST 2021
	 */
	int insertSelective(AlmnInfoCheck record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_info_check
	 * @mbg.generated  Wed Apr 21 17:00:47 CST 2021
	 */
	List<AlmnInfoCheck> selectByExample(AlmnInfoCheckExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_info_check
	 * @mbg.generated  Wed Apr 21 17:00:47 CST 2021
	 */
	AlmnInfoCheck selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_info_check
	 * @mbg.generated  Wed Apr 21 17:00:47 CST 2021
	 */
	int updateByExampleSelective(@Param("record") AlmnInfoCheck record, @Param("example") AlmnInfoCheckExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_info_check
	 * @mbg.generated  Wed Apr 21 17:00:47 CST 2021
	 */
	int updateByExample(@Param("record") AlmnInfoCheck record, @Param("example") AlmnInfoCheckExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_info_check
	 * @mbg.generated  Wed Apr 21 17:00:47 CST 2021
	 */
	int updateByPrimaryKeySelective(AlmnInfoCheck record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_info_check
	 * @mbg.generated  Wed Apr 21 17:00:47 CST 2021
	 */
	int updateByPrimaryKey(AlmnInfoCheck record);
}