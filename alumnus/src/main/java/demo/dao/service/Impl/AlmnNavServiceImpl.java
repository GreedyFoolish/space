package demo.dao.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.mapper.AlmnNavMapper;
import demo.dao.model.AlmnNav;
import demo.dao.model.AlmnNavExample;
import demo.dao.service.AlmnNavService;

@Service // 告诉Spring框架这个类是一个Service，会由Spring框架协助初始化
public class AlmnNavServiceImpl implements AlmnNavService {
	@Autowired
	private AlmnNavMapper navMapper;

	@Override
	public List<AlmnNav> selectNavList() throws MsgException {
		AlmnNavExample example = new AlmnNavExample();
		return navMapper.selectByExample(example);
	}

	@Override
	public void addNav(AlmnNav nav) {
		navMapper.insertSelective(nav);
	}

	@Override
	public void updateNav(AlmnNav nav) {
		AlmnNavExample example = new AlmnNavExample();
		AlmnNavExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(nav.getId());
		navMapper.updateByExampleSelective(nav, example);
	}

}
