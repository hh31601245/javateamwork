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
	public static void Leading_in_Case()
	{
		CaseRegular caseregular=new CaseRegular();
		Case c=new Case(caseregular.getCid(),caseregular.getCourtName(),caseregular.getNumberPeople()
				,caseregular.getMinAge(),caseregular.getFirstDefendant(),caseregular.getCrime()
				,caseregular.getPenaltyType(),caseregular.getSentence(),caseregular.getPropertyPenaltyType()
				,caseregular.getPropertyPenaltyAmount(),caseregular.getDrugTypeAndNumberOrUnit()
				,caseregular.getDrugPrice());
		try {
			Util.casemanage.addCase(c);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void Leading_in_Defendant()
	{
		//String 
//		DefendantRegular defendantregular=new DefendantRegular();
//		Defendant d=new Defendant(defendantregular)
		List<String> defendantwords=new ArrayList<String>();
		CaseRegular caseregular=new CaseRegular();
		defendantwords=LoadWord.LoadDefendantWord();
		for(String defendantword:defendantwords)
		{
			DefendantRegular defendantregular=new DefendantRegular();
			Defendant d=new Defendant(defendantregular.getDid(),defendantregular.getDname(defendantword),defendantregular.getSex(defendantword),
					defendantregular.getAge(defendantword),defendantregular.getDateOfBrith(defendantword),defendantregular.getEducation(defendantword),
					defendantregular.getCrime(defendantword),defendantregular.getPenaltyType(defendantword),defendantregular.getSentence(defendantword),
					defendantregular.getPropertyPenaltyType(defendantword),defendantregular.getPropertyPenaltyAmount(defendantword),
					defendantregular.getCid());
			try {
				Util.defendantmanage.addDefendant(d);
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void Leading_in_Relation()
	{
		
	}
}
