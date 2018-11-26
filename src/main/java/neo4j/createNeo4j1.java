package neo4j;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;

import model.Case;
import model.Defendant;
import util.BaseException;

import static org.neo4j.driver.v1.Values.parameters;

import java.io.File;
import java.util.*;
public class createNeo4j1 {
	static Driver driver = null;

    public static void getDriver(){
        String uri = "Bolt://localhost:7687";
        String user = "neo4j";//写你自己的neo4j的用户名
        String password = "123456";//写你自己的neo4j的密码
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user,password));
    }

    public static void close(){
        if(driver!=null){
            driver.close();
        }
    }
	public static void Delete()
	{
	        getDriver();
	    	try(Session session = driver.session()){
	    		StatementResult result = session.run("MATCH (n)\r\n" + 
	    				"OPTIONAL MATCH (n)-[r]-()\r\n" + 
	    				"DELETE n,r\r\n" + 
	    				"");
	            
	    	}
	    	close(); 
	}
    public static void Create()
    {
    	getDriver();
    	try(Session session = driver.session()){
            try(Transaction tx = session.beginTransaction()){

              /*  tx.run("create(Person:Person{NAME:{NAME},TITLE:{TITLE}})",
                        parameters("NAME","james","TITLE","King"));
                tx.success();
                tx.run("create(Person:Person{NAME:{NAME},TITLE:{TITLE}})",
                        parameters("NAME","james1","TITLE","King1"));
                tx.success();
            	tx.run("create(Case:Case{ID:{ID}})",
            			parameters("ID","123"));
            	tx.success();*/
            	//tx.close();
            	for(Case c:Case.caselist)
            	{
            	/*	tx.run("create(Case:Case{Cid:{Cid},Cyear:{Cyear},CourtName:{CourtName},"
            				+ "NumberPeople:{NumberPeople},MinAge:{MinAge},FirstDefendant:"
            				+ "{FirstDefendant},DrugTypeAndNumberOrUnit:{DrugTypeAndNumberOrUnit},"
            				+ "DrugPrice:{DrugPrice}})",
            				parameters("Cid",c.getCid(),"Cyear",c.getCyear(),"CourtName",c.getCourtName(),
            						"NumberPeople",String.valueOf(c.getNumberPeople()),"MinAge",
            						String.valueOf(c.getMinAge()),"FirstDefendant",c.getFirstDefendant(),
            						"DrugTypeAndNumberOrUnit",c.getDrugTypeAndNumberOrUnit(),
            						"DrugPrice",c.getDrugPrice()));*/
            		tx.run("create(Case:Case{name:{name}})",
            				parameters("name",c.getCid()));
            		tx.success();
            		
            		List<Defendant> defendantlist=new ArrayList<Defendant>();
					try {
						defendantlist = begin.Util.defendantmanage.loadDefendant(c);
					} catch (BaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		for(Defendant d:defendantlist)
            		{
            			/*tx.run("create(Defendant:Defendant{Dname:{Dname},Sex:{Sex},Age:{Age},DateOfBrith:{DateOfBrith},"
            					+ "Education:{Education},Crime:{Crime},PenaltyType:{PenaltyType},"
            					+ "Sentence:{Sentence},PropertyPenaltyType:{PropertyPenaltyType},"
            					+ "PropertyPenaltyAmount:{PropertyPenaltyAmount},Cid:{Cid}})",
            					parameters("Dname",d.getDname(),"Sex",d.getSex(),"Age",String.valueOf(d.getAge()),
            							"DateOfBrith",d.getDateOfBrith(),"Education",d.getEducation(),"Crime",d.getCrime(),
            							"PenaltyType",d.getPenaltyType(),"Sentence",d.getSentence(),
            							"PropertyPenaltyType",d.getPropertyPenaltyType(),"PropertyPenaltyAmount",String.valueOf(d.getPropertyPenaltyAmount()),
            							"Cid",d.getCid()));
            			tx.success();*/
            			tx.run("create(Defendant:Defendant{name:{name},Cid:{Cid}})",
            					parameters("name",d.getDname(),"Cid",d.getCid()));
            			tx.success();
            			 tx.run("match(a:Defendant),(b:Case) where a.Cid=b.name create(a)-[r:is_this_case_defendant]->(b)");
            			 tx.success();
            		}
            	}
            }
          /*  try(Transaction tx = session.beginTransaction()){

               
            	//tx.close();
            }*/
            		
            		
            	
    	}
    	close();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Delete();
		//Create();
	}

}
