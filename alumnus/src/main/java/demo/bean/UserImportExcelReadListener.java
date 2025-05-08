package demo.bean;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import demo.dao.service.AlmnUserService;

public class UserImportExcelReadListener extends AnalysisEventListener<ExcelUserImport> {
	
	private AlmnUserService userService;
	private List<ExcelUserImport> errorList;

	public UserImportExcelReadListener(AlmnUserService userService) {
		super();
		this.userService = userService;
		errorList = new ArrayList<ExcelUserImport>();
	}
	
	@Override
	public void invoke(ExcelUserImport data, AnalysisContext context) {
		try {
			if(userService.addUserByExcel(data) < 1) {
				errorList.add(data);
			}
		} catch (MsgException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		// TODO Auto-generated method stub
		
	}
	public List<ExcelUserImport> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ExcelUserImport> errorList) {
		this.errorList = errorList;
	}
}
