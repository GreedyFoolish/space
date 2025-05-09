package demo.dao.mapper;

import java.util.List;

import demo.dao.model.AlmnUser;
import demo.dao.model.AlmnUserExample;
import org.apache.ibatis.annotations.Param;

public interface AlmnUserMapper {
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_user
	 * @mbg.generated  Sun May 30 20:25:24 CST 2021
	 */
	long countByExample(AlmnUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_user
	 * @mbg.generated  Sun May 30 20:25:24 CST 2021
	 */
	int deleteByExample(AlmnUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_user
	 * @mbg.generated  Sun May 30 20:25:24 CST 2021
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_user
	 * @mbg.generated  Sun May 30 20:25:24 CST 2021
	 */
	int insert(AlmnUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_user
	 * @mbg.generated  Sun May 30 20:25:24 CST 2021
	 */
	int insertSelective(AlmnUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_user
	 * @mbg.generated  Sun May 30 20:25:24 CST 2021
	 */
	List<AlmnUser> selectByExample(AlmnUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_user
	 * @mbg.generated  Sun May 30 20:25:24 CST 2021
	 */
	AlmnUser selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_user
	 * @mbg.generated  Sun May 30 20:25:24 CST 2021
	 */
	int updateByExampleSelective(@Param("record") AlmnUser record, @Param("example") AlmnUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_user
	 * @mbg.generated  Sun May 30 20:25:24 CST 2021
	 */
	int updateByExample(@Param("record") AlmnUser record, @Param("example") AlmnUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_user
	 * @mbg.generated  Sun May 30 20:25:24 CST 2021
	 */
	int updateByPrimaryKeySelective(AlmnUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table almn_user
	 * @mbg.generated  Sun May 30 20:25:24 CST 2021
	 */
	int updateByPrimaryKey(AlmnUser record);

	AlmnUser findUserByUid(String userId);
	
	AlmnUser getUserIconByUid(String userId);

	void updateDelTimeByUid(AlmnUser user);
	/**
	 * 	恢复deltime=0
	 * @param userId
	 */
	void updateDelTimeByUid2(String userId);

	List<AlmnUser> selectDelUser();

	int updateUserIcon(AlmnUser user);

	void updateUserEffect();

	List<AlmnUser> gethotUserList();
	
}
