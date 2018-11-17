package itf;

public interface ICaseRegular {
	/**
	 * 通过正则获得Cid
	 * @return
	 */
	public String getCid(String word);
	/**
	 * 通过正则获得判决年份
	 * @return
	 */
	public String getCyear(String word);
	/**
	 * 通过正则获得CourtName
	 * @return
	 */
  	public String getCourtName(String word) ;
  	/**
  	 * 通过正则获得NumberPeople
  	 * @return
  	 */
  	public int getNumberPeople(String title) ;
  	/**
  	 * 通过正则获得MinAge
  	 * @return
  	 */
  	public int getMinAge(String word);
  	/**
  	 * 通过正则获得FirstDefendant
  	 * @return
  	 */
  	public String getFirstDefendant(String word);
  	/**
  	 * 通过正则获得DrugTypeAndNumberOrUnit
  	 * @return
  	 */
  	public String getDrugTypeAndNumberOrUnit(String word);
  	/**
  	 * 通过正则获得DrugPrice
  	 * @return
  	 */
  	public String getDrugPrice(String word);
}
