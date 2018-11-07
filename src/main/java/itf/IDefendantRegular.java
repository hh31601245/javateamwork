package itf;

public interface IDefendantRegular {
	/**
	 * CId自己生成
	 * @return
	 */
	/**
	 * 用正则获取该Cid下的Did
	 * @return
	 */
	public String getDid();
	/**
	 * 用正则获取该Cid和Did下的Dname
	 * @return
	 */
	public String getDname();
	/**
	 * 用正则获取该Cid和Did下的Sex
	 * @return
	 */
	public String getSex();
	/**
	 * 用正则获取该Cid和Did下的Age
	 * @return
	 */
	public int getAge();
	/**
	 * 用正则获取该Cid和Did下的DateOfBrith
	 * @return
	 */
	public String getDateOfBrith();
	/**
	 * 用正则获取该Cid和Did下的Education
	 * @return
	 */
	public String getEducation();
	/**
	 * 用正则获取该Cid和Did下的Crime
	 * @return
	 */
	public String getCrime();
	/**
	 * 用正则获取该Cid和Did下的PenaltyType
	 * @return
	 */
	public String getPenaltyType(); 
	/**
	 * 用正则获取该Cid和Did下的Sentence
	 * @return
	 */
	public String getSentence();
	/**
	 * 用正则获取该Cid和Did下的PropertyPenaltyType
	 * @return
	 */
	public String getPropertyPenaltyType();
	/**
	 * 用正则获取该Cid和Did下的PropertyPenaltyAmount
	 * @return
	 */
	public double getPropertyPenaltyAmount();
}
