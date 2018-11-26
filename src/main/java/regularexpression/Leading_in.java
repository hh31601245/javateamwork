package regularexpression;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.*;
import mysql.*;
import poi.LoadWord;
import util.BaseException;
import begin.Util;
//import mysql.DefendantManage;
public class Leading_in {  //单纯只是把word的数据存到数据库中
	public static void Leading_in_Case(String url,String title)
	{
		CaseRegular caseregular=new CaseRegular();
		String casewords=null;
		System.out.println(url);
		try {
			casewords=LoadWord.LoadAllWord(url);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	/*	Case c=new Case(caseregular.getCid(title),caseregular.getCyear(casewords),caseregular.getCourtName(casewords),caseregular.getNumberPeople(url)
				,caseregular.getMinAge(url),caseregular.getFirstDefendant(url),caseregular.getDrugTypeAndNumberOrUnit(casewords)
				,caseregular.getDrugPrice(casewords));*/

		try {
			Case c=new Case();
			c.setCid(caseregular.getCid(title));
			c.setCourtName(caseregular.getCourtName(casewords));
			c.setCyear(caseregular.getCyear(casewords,url));
			c.setDrugPrice(caseregular.getDrugPrice(casewords));
			c.setDrugTypeAndNumberOrUnit(caseregular.getDrugTypeAndNumberOrUnit(casewords));
			c.setFirstDefendant(caseregular.getFirstDefendant(url));
			c.setMinAge(caseregular.getMinAge(url));
			c.setNumberPeople(caseregular.getNumberPeople(url));
			Util.casemanage.addCase(c);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "现在的正则无法正确筛选这个word", "标题",JOptionPane.WARNING_MESSAGE);
			begin.Util.judgmentmanage.deleteJudgment(title);
			e.printStackTrace();
		}
	}
	public static void Leading_in_Defendant(String url,String title)
	{
		//String 
//		DefendantRegular defendantregular=new DefendantRegular();
//		Defendant d=new Defendant(defendantregular)
		List<String> defendantwords=new ArrayList<String>();
		CaseRegular caseregular=new CaseRegular();
		defendantwords=LoadWord.LoadDefendantWord(url);
		for(String defendantword:defendantwords)
		{
			DefendantRegular defendantregular=new DefendantRegular();
			Defendant d=new Defendant(defendantregular.getDid(title),defendantregular.getDname(defendantword),defendantregular.getSex(defendantword),
					defendantregular.getAge(defendantword),defendantregular.getDateOfBrith(defendantword),defendantregular.getEducation(defendantword),
					defendantregular.getCrime(defendantword),defendantregular.getPenaltyType(defendantword),defendantregular.getSentence(defendantword),
					defendantregular.getPropertyPenaltyType(defendantword),defendantregular.getPropertyPenaltyAmount(defendantword),
					defendantregular.getCid(title));
			try {
				Util.defendantmanage.addDefendant(d);
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
