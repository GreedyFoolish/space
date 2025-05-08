package demo.dao.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.mapper.AlmnEffectSetMapper;
import demo.dao.model.AlmnEffectSet;
import demo.dao.model.AlmnEffectSetExample;
import demo.dao.service.AlmnEffectSetService;

@Service // 告诉Spring框架这个类是一个Service，会由Spring框架协助初始化
public class AlmnEffectSetServiceImpl implements AlmnEffectSetService {
	@Autowired
	private AlmnEffectSetMapper effectSetMapper;

	@Override
	public List<AlmnEffectSet> getAllEffectSet() throws MsgException {

		AlmnEffectSetExample example = new AlmnEffectSetExample();
		List<AlmnEffectSet> list = effectSetMapper.selectByExample(example);
		return list;
	}

	@Override
	public int setEffectSet(String key, Integer val) {
		AlmnEffectSetExample example = new AlmnEffectSetExample();
		AlmnEffectSetExample.Criteria criteria = example.createCriteria();
		criteria.andEffectKeyEqualTo(key);
		AlmnEffectSet effectSet = new AlmnEffectSet();
		effectSet.setEffectVal(val);
		effectSetMapper.updateByExampleSelective(effectSet, example);
		return 0;
	}

}
