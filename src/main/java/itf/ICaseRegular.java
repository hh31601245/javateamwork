package itf;

public interface ICaseRegular {
	/**
	 * 通过正则获得Cid
	 * @return
	 */
	public String getCid();
	/**
	 * 通过正则获得CourtName
	 * @return
	 */
  	public String getCourtName() ;
  	/**
  	 * 通过正则获得NumberPeople
  	 * @return
  	 */
  	public int getNumberPeople() ;
  	/**
  	 * 通过正则获得MinAge
  	 * @return
  	 */
  	public int getMinAge() ;
  	/**
  	 * 通过正则获得FirstDefendant
  	 * @return
  	 */
  	public String getFirstDefendant();
  	/**
  	 * 通过正则获得Crime
  	 * @return
  	 */
  	public String getCrime();
  	/**
  	 * 通过正则获得PenaltyType
  	 * @return
  	 */
  	public String getPenaltyType();
  	/**
  	 * 通过正则获得Sentence
  	 * @return
  	 */
  	public String getSentence();
  	/**
  	 * 通过正则获得PropertyPenaltyType
  	 * @return
  	 */
  	public String getPropertyPenaltyType();
  	/**
  	 * 通过正则获得PropertyPenaltyAmount
  	 * @return
  	 */
  	public double getPropertyPenaltyAmount();
  	/**
  	 * 通过正则获得DrugTypeAndNumberOrUnit
  	 * @return
  	 */
  	public String getDrugTypeAndNumberOrUnit();
  	/**
  	 * 通过正则获得DrugPrice
  	 * @return
  	 */
  	public String getDrugPrice();
}
