package demo.dao.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import demo.bean.MsgException;
import demo.dao.mapper.AlmnDailyMapper;
import demo.dao.model.AlmnDaily;
import demo.dao.model.AlmnDailyExample;
import demo.dao.service.AlmnDailyService;

@Service // 告诉Spring框架这个类是一个Service，会由Spring框架协助初始化
public class AlmnDailyServiceImpl implements AlmnDailyService {
	@Autowired
	private AlmnDailyMapper dailyMapper;

	@Override
	public List<AlmnDaily> selectAllDaily(String userId, Integer flag) throws MsgException {
		// 添加查询条件
		AlmnDailyExample example = new AlmnDailyExample();
		AlmnDailyExample.Criteria criteria = example.createCriteria();
		if ( flag == 1) {
			criteria.andUserIdEqualTo(userId);
		}
		example.setOrderByClause("addtime desc");
		List<AlmnDaily> list = dailyMapper.selectByExample(example);
		return list;
	}

	@Override
	public void insertOrUpdateDaily(AlmnDaily daily) throws MsgException {
		if(daily == null) {
			throw new MsgException("请求参数错误");
		}
		System.out.println("------------------>daily"+daily.toString());
		dailyMapper.insertSelective(daily);
	}

	@Override
	public AlmnDaily selectDailyByDailyId(String dailyId) throws MsgException {
		AlmnDailyExample example = new AlmnDailyExample();
		AlmnDailyExample.Criteria criteria = example.createCriteria();
		criteria.andDailyIdEqualTo(dailyId);
		List<AlmnDaily> daily = dailyMapper.selectByExample(example);
		return daily.get(0);
	}

	@Override
	public void updateDailyEffect() {
		dailyMapper.updateDailyEffect();
	}

	@Override
	public List<AlmnDaily> gethotDailyList() {
		return dailyMapper.gethotDailyList();
	}

	@Override
	public int updateDailyTag(String dailyId, String tagInput) {
		AlmnDailyExample example = new AlmnDailyExample();
		AlmnDailyExample.Criteria criteria = example.createCriteria();
		criteria.andDailyIdEqualTo(dailyId);
		AlmnDaily daily = dailyMapper.selectByExample(example).get(0);
		AlmnDaily daily2 = new AlmnDaily();
		if( daily.getDailyTag1() == null || daily.getDailyTag1().equals("")) {
			daily2.setDailyTag1(tagInput);
		} else {
			daily2.setDailyTag2(tagInput);
		}
		dailyMapper.updateByExampleSelective(daily2, example);
		return 0;
	}

	@Override
	public List<AlmnDaily> getEnjoyDaily(String userId, String enjoyUser, String enjoyTag) {
		// 添加查询条件
		AlmnDailyExample example = new AlmnDailyExample();
		AlmnDailyExample.Criteria criteria = example.createCriteria();
//		criteria.andUserIdEqualTo(userId);
//		System.out.println("getEnjoyDaily----------------->"+enjoyUser+"--------"+enjoyTag);
		String[] userList = enjoyUser.split("@");
		String[] tagList = enjoyTag.split("@");
		int flag = 0;
		for(int i=0; i<userList.length; i++) {
			if(i==0) {
				criteria.andUserIdEqualTo(userList[0]);
				flag = 1;
			} else {
				example.or().andUserIdEqualTo(userList[i]);
			}
		}
		for(int i=0; i<tagList.length; i++) {
			if(flag == 0) {
				criteria.andDailyTag1EqualTo(tagList[0]);
				criteria.andDailyTag2EqualTo(tagList[0]);
				flag = 1;
			} else {
				example.or().andDailyTag1EqualTo(tagList[i]);
				example.or().andDailyTag2EqualTo(tagList[i]);
			}
		}
		example.setOrderByClause("effect_cnt desc, addtime desc");
		List<AlmnDaily> list = dailyMapper.selectByExample(example);
		return list;
	}

}
