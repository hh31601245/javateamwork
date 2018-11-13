package regularexpression;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Relation;
import itf.IRelationRugular;
import poi.LoadWord;
public class RelationRegular implements IRelationRugular{

	public static void main(String[] args)
	{
		RelationRegular r=new RelationRegular();
		r.getSameCaseRelate();
	}
	@Override
	public void getSameCaseRelate() {  //调用数据库
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getSameEducation() {
		// TODO Auto-generated method stub
		
	}
}
