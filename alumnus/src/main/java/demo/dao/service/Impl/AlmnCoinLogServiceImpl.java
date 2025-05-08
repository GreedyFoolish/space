package demo.dao.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.mapper.AlmnCoinLogMapper;
import demo.dao.model.AlmnCoinLog;
import demo.dao.model.AlmnCoinLogExample;
import demo.dao.service.AlmnCoinLogService;

@Service
public class AlmnCoinLogServiceImpl implements AlmnCoinLogService {
	@Autowired
	private AlmnCoinLogMapper coinLogMapper;

	@Override
	public AlmnCoinLog selectCoinLogByDailyId(String dailyId) throws MsgException {
		AlmnCoinLogExample example = new AlmnCoinLogExample();
		AlmnCoinLogExample.Criteria criteria = example.createCriteria();
		criteria.andDailysIdEqualTo(dailyId);
		List<AlmnCoinLog> AlmnCoinLog = coinLogMapper.selectByExample(example);
		return AlmnCoinLog.get(0);
	}

	@Override
	public void insert(AlmnCoinLog coinLog) throws MsgException {
		AlmnCoinLogExample example = new AlmnCoinLogExample();
		AlmnCoinLogExample.Criteria criteria = example.createCriteria();
		criteria.andDailysIdEqualTo(coinLog.getDailysId());
		criteria.andUser2IdEqualTo(coinLog.getUser2Id());
		if(coinLogMapper.countByExample(example) > 0) {
			AlmnCoinLog log = new AlmnCoinLog();
			log.setCoinCnt(coinLog.getCoinCnt());
			coinLogMapper.updateByExampleSelective(log, example);
		} else {
			coinLogMapper.insertSelective(coinLog);
		}
	}

	@Override
	public int selectCoinCnt(AlmnCoinLog coinLog) throws MsgException {
		AlmnCoinLogExample example = new AlmnCoinLogExample();
		AlmnCoinLogExample.Criteria criteria = example.createCriteria();
		criteria.andDailysIdEqualTo(coinLog.getDailysId());
		criteria.andUser2IdEqualTo(coinLog.getUser2Id());
		List<AlmnCoinLog> coinLogs = coinLogMapper.selectByExample(example);
		int cnt = 0;
		for(AlmnCoinLog coin : coinLogs) {
			cnt += coin.getCoinCnt();
		}
		return cnt;
	}

	@Override
	public List<AlmnCoinLog> getCoinLog(String userId) {
		AlmnCoinLogExample example = new AlmnCoinLogExample();
		AlmnCoinLogExample.Criteria criteria = example.createCriteria();
		criteria.andUser2IdEqualTo(userId);
		return coinLogMapper.selectByExample(example);
	}

}
