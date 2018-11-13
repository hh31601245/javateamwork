package itf;
import model.Relation;;
public interface IRelationRugular {
	
	/**
	 * 获取为同一个案子里这样的关系，存入数据库
	 * @return
	 */
	public void getSameCaseRelate();
	/**
	 * 获取是同一学历这个关系，存入数据库
	 * @return
	 */
	public void getSameEducation();
	
}
