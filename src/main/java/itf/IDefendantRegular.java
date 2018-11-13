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
	public String getDname(String defendantword);
	/**
	 * 用正则获取该Cid和Did下的Sex
	 * @return
	 */
	public String getSex(String defendantword);
	/**
	 * 用正则获取该Cid和Did下的Age
	 * @return
	 */
	public int getAge(String defendantword);
	/**
	 * 用正则获取该Cid和Did下的DateOfBrith
	 * @return
	 */
	public String getDateOfBrith(String defendantword);
	/**
	 * 用正则获取该Cid和Did下的Education
	 * @return
	 */
	public String getEducation(String defendantword);
	/**
	 * 用正则获取该Cid和Did下的Crime
	 * @return
	 */
	public String getCrime(String defendantword);
	/**
	 * 用正则获取该Cid和Did下的PenaltyType
	 * @return
	 */
	public String getPenaltyType(String defendantword); 
	/**
	 * 用正则获取该Cid和Did下的Sentence
	 * @return
	 */
	public String getSentence(String defendantword);
	/**
	 * 用正则获取该Cid和Did下的PropertyPenaltyType
	 * @return
	 */
	public String getPropertyPenaltyType(String defendantword);
	/**
	 * 用正则获取该Cid和Did下的PropertyPenaltyAmount
	 * @return
	 */
	public double getPropertyPenaltyAmount(String defendantword);
}
