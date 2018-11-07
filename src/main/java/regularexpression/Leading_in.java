package regularexpression;
import java.io.IOException;

import model.*;
import mysql.*;
import poi.LoadWord;
//import mysql.DefendantManage;
public class Leading_in {  //单纯只是把word的数据存到数据库中
	public void Leading_in_Case()
	{
		CaseRegular caseregular=new CaseRegular();
		Case c=new Case(caseregular.getCid(),caseregular.getCourtName(),caseregular.getNumberPeople()
				,caseregular.getMinAge(),caseregular.getFirstDefendant(),caseregular.getCrime()
				,caseregular.getPenaltyType(),caseregular.getSentence(),caseregular.getPropertyPenaltyType()
				,caseregular.getPropertyPenaltyAmount(),caseregular.getDrugTypeAndNumberOrUnit()
				,caseregular.getDrugPrice());
		
	}
	public void Leading_in_Defendant()
	{
		//String 
//		DefendantRegular defendantregular=new DefendantRegular();
//		Defendant d=new Defendant(defendantregular)
		CaseRegular caseregular=new CaseRegular();
		try {
			String word=LoadWord.LoadAllWord();
			String[] wordlist=word.split(caseregular.getCourtName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
