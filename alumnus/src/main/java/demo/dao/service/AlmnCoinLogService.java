package demo.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.model.AlmnCoinLog;

@Service
public interface AlmnCoinLogService {
	
	AlmnCoinLog selectCoinLogByDailyId(String dailyId) throws MsgException ;
	
	void insert(AlmnCoinLog coinLog) throws MsgException ;
	
	int selectCoinCnt(AlmnCoinLog coinLog) throws MsgException ;

	List<AlmnCoinLog> getCoinLog(String userId);
	
}
