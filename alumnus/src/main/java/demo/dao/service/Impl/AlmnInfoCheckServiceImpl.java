package demo.dao.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.mapper.AlmnInfoCheckMapper;
import demo.dao.model.AlmnInfoCheck;
import demo.dao.model.AlmnInfoCheckExample;
import demo.dao.service.AlmnInfoCheckService;

@Service
public class AlmnInfoCheckServiceImpl implements AlmnInfoCheckService {
	@Autowired
	private AlmnInfoCheckMapper infoCheckMapper;

	@Override
	public int countInsertUser(AlmnInfoCheck user) throws MsgException {

		AlmnInfoCheckExample example = new AlmnInfoCheckExample();
		AlmnInfoCheckExample.Criteria criteria = example.createCriteria();
		// 学号和名字相同
		if(user.getUserRealname() == null) {
			criteria.andUserIdEqualTo(user.getUserId());
		} else {
			criteria.andUserIdEqualTo(user.getUserId()).andUserRealnameEqualTo(user.getUserRealname());
		}
		// 学号和身份证相同
		if(user.getUserIdcard() != null) {
			example.or().andUserIdEqualTo(user.getUserId()).andUserIdcardEqualTo(user.getUserIdcard());
		}
		// 学号和联系电话相同
		if(user.getUserPhone() != null) {
			example.or().andUserIdEqualTo(user.getUserId()).andUserIdcardEqualTo(user.getUserPhone());
		}
		// 学号和联系邮箱相同
		if(user.getUserMail() != null) {
			example.or().andUserIdEqualTo(user.getUserId()).andUserIdcardEqualTo(user.getUserMail());
		}
		int cnt = (int) infoCheckMapper.countByExample(example);
//		int sxxx= infoCheckMapper.insertSelective(user);
//		System.out.println("infoCheckMapper ===============> "+cnt);
		if(cnt > 0) {
			return 0;
		}
		return infoCheckMapper.insertSelective(user);
	}

	@Override
	public List<AlmnInfoCheck> getAllUserInfo() throws MsgException {
		AlmnInfoCheckExample example = new AlmnInfoCheckExample();
		List<AlmnInfoCheck> list = infoCheckMapper.selectByExample(example);
//		return infoCheckMapper.selectByExample(example);
		for(int i=0;i<list.size();i++) {
			
			System.out.println(i+"check info ==========>"+list.indexOf(i));
		}
		for(AlmnInfoCheck v : list) {

			System.out.println("check info ==========>"+v.toString());
		}
		return list;
	}
	
}
