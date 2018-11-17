package regularexpression;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import mysql.*;
import poi.LoadWord;
import util.BaseException;
import begin.Util;
//import mysql.DefendantManage;
public class Leading_in {  //单纯只是把word的数据存到数据库中
	public static void Leading_in_Case(String title)
	{
		CaseRegular caseregular=new CaseRegular();
		try {
			String casewords=LoadWord.LoadAllWord(title);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Case c=new Case(caseregular.getCid(casewords),caseregular.getCyear(casewords),caseregular.getCourtName(casewords),caseregular.getNumberPeople(title)
				,caseregular.getMinAge(casewords),caseregular.getFirstDefendant(casewords),caseregular.getDrugTypeAndNumberOrUnit(casewords)
				,caseregular.getDrugPrice(casewords));
		try {
			Util.casemanage.addCase(c);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void Leading_in_Defendant(String title)
	{
		//String 
//		DefendantRegular defendantregular=new DefendantRegular();
//		Defendant d=new Defendant(defendantregular)
		List<String> defendantwords=new ArrayList<String>();
		CaseRegular caseregular=new CaseRegular();
		defendantwords=LoadWord.LoadDefendantWord(title);
		for(String defendantword:defendantwords)
		{
			DefendantRegular defendantregular=new DefendantRegular();
			Defendant d=new Defendant(defendantregular.getDid(),defendantregular.getDname(defendantword),defendantregular.getSex(defendantword),
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
