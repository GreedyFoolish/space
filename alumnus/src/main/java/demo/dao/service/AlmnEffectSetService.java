package demo.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.model.AlmnEffectSet;

@Service
public interface AlmnEffectSetService {

	List<AlmnEffectSet> getAllEffectSet() throws MsgException ;

	int setEffectSet(String key, Integer val);

}
