package demo.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.model.AlmnDaily;

@Service
public interface AlmnDailyService {
	/**
	 * 	flag = 1 表示查某人，否则为查所有
	 * @param userId
	 * @param flag
	 * @return
	 * @throws MsgException
	 */
	List<AlmnDaily> selectAllDaily(String userId, Integer flag) throws MsgException ;
	
	void insertOrUpdateDaily(AlmnDaily daily) throws MsgException ;

	AlmnDaily selectDailyByDailyId(String dailyId) throws MsgException ;

	void updateDailyEffect();

	List<AlmnDaily> gethotDailyList();

	int updateDailyTag(String dailyId, String tagInput);

	List<AlmnDaily> getEnjoyDaily(String userId, String enjoyUser, String enjoyTag);

}
