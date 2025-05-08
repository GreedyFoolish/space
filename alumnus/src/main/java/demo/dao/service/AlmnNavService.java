package demo.dao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo.bean.MsgException;
import demo.dao.model.AlmnNav;

@Service
public interface AlmnNavService {

	List<AlmnNav> selectNavList() throws MsgException ;

	void addNav(AlmnNav nav);

	void updateNav(AlmnNav nav);

}
