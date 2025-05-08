package demo.dao.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.mapper.AlmnFavorLogMapper;
import demo.dao.model.AlmnFavorLog;
import demo.dao.model.AlmnFavorLogExample;
import demo.dao.service.AlmnFavorLogService;

@Service // 告诉Spring框架这个类是一个Service，会由Spring框架协助初始化
public class AlmnFavorLogServiceImpl implements AlmnFavorLogService {
	@Autowired
	private AlmnFavorLogMapper favorLogMapper;

	@Override
	public AlmnFavorLog selectFavorLogByDailyId(String dailyId) throws MsgException {
		AlmnFavorLogExample example = new AlmnFavorLogExample();
		AlmnFavorLogExample.Criteria criteria = example.createCriteria();
		criteria.andDailysIdEqualTo(dailyId);
		List<AlmnFavorLog> AlmnFavorLog = favorLogMapper.selectByExample(example);
		return AlmnFavorLog.get(0);
	}

	@Override
	public void insert(AlmnFavorLog favorLog) throws MsgException {
		favorLogMapper.insertSelective(favorLog);
	}

	@Override
	public void cancelFavorLog(AlmnFavorLog favorLog) throws MsgException {
		AlmnFavorLogExample example = new AlmnFavorLogExample();
		AlmnFavorLogExample.Criteria criteria = example.createCriteria();
		criteria.andDailysIdEqualTo(favorLog.getDailysId());
		criteria.andUser2IdEqualTo(favorLog.getUser2Id());
		AlmnFavorLog mfavorLog = new AlmnFavorLog();
		mfavorLog.setDeltime((int) System.currentTimeMillis());
		favorLogMapper.updateByExampleSelective(mfavorLog, example);
	}
	
	@Override
	public void updateAdd(AlmnFavorLog favorLog) throws MsgException {
		AlmnFavorLogExample example = new AlmnFavorLogExample();
		AlmnFavorLogExample.Criteria criteria = example.createCriteria();
		criteria.andDailysIdEqualTo(favorLog.getDailysId());
		criteria.andUser2IdEqualTo(favorLog.getUser2Id());
		AlmnFavorLog mfavorLog = new AlmnFavorLog();
		mfavorLog.setDeltime(0);
		favorLogMapper.updateByExampleSelective(mfavorLog, example);
	}

	@Override
	public int countFavorLog(AlmnFavorLog favorLog, String flag) throws MsgException {
		AlmnFavorLogExample example = new AlmnFavorLogExample();
		AlmnFavorLogExample.Criteria criteria = example.createCriteria();
		criteria.andDailysIdEqualTo(favorLog.getDailysId());
		criteria.andUser2IdEqualTo(favorLog.getUser2Id());
//		System.out.println("==============>getUser2Id:"+favorLog.getUser2Id());
		Long cnt = favorLogMapper.countByExample(example);
		if(flag.equals("add")) {
			criteria.andDeltimeNotEqualTo(0);
			// 未添加
			if(cnt < 1) {
				return 1;
			// 已经添加但是deltime!=0（即取消点赞）
			} else if(favorLogMapper.countByExample(example) > 0) {
//				System.out.println("已经添加但是deltime!=0（即取消点赞）");
				return 2;
			}
			return 3;
		} else if(flag.equals("cancel")) {
			criteria.andDeltimeEqualTo(0);
			// 未添加，则无法取消点赞
			if(cnt < 1) {
				return 1;
			// 已添加且处于点赞状态，才可取消点赞
			} else if(favorLogMapper.countByExample(example) > 0) {
				System.out.println("已添加且处于点赞状态，才可取消点赞");
				return 2;
			}
			return 3;
		}
		return 5;
	}
	
	@Override
	public List<AlmnFavorLog> getFavorLog(String userId) throws MsgException {
		AlmnFavorLogExample example = new AlmnFavorLogExample();
		AlmnFavorLogExample.Criteria criteria = example.createCriteria();
		criteria.andUser2IdEqualTo(userId);
		List<AlmnFavorLog> list = favorLogMapper.selectByExample(example);
		return list;
	}

}
