package itf;

import java.util.List;

import model.Judgment;

public interface IJudgmentManage {
	/**
	 * 添加新的审判书
	 * @param j
	 */
	public void addJudgment(Judgment j);
	/**
	 * 输出所有的审判书的标题
	 * @return
	 */
	public List<String> loadAll();
	/**
	 * 根据标题输出审判书
	 * @param title
	 * @return
	 */
	public Judgment searchJudgment(String title);
	/**
	 * 删除新的审判书
	 * @param title
	 */
	public void deleteJudgment(String title);
	/**
	 * 这里主要对新加入的审判书的一些格式化处理，比如是否中文的（，阿拉伯数字数字之类的。
	 * @param j
	 */
	public void modifyJudgment(Judgment j);
}
