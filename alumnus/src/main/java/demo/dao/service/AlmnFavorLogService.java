package demo.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.model.AlmnFavorLog;

@Service
public interface AlmnFavorLogService {
	
	AlmnFavorLog selectFavorLogByDailyId(String dailyId) throws MsgException ;
	
	void insert(AlmnFavorLog favorLog) throws MsgException ;
	
	void cancelFavorLog(AlmnFavorLog favorLog) throws MsgException ;

	void updateAdd(AlmnFavorLog favorLog) throws MsgException ;
	
	int countFavorLog(AlmnFavorLog favorLog, String flag) throws MsgException ;
	
	List<AlmnFavorLog> getFavorLog(String userId) throws MsgException ;
	
}
