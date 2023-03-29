package cn.moongoddess.core.model;

/**
 * 修约参数
 */
public class RoundingParams {

	/**
	 * 修约系数
	 */
	private String coefficient;

	/**
	 * 保留的小数位数
	 */
	private int decimalPlace;

	public RoundingParams(String coefficient, int decimalPlace) {
		this.coefficient = coefficient;
		this.decimalPlace = decimalPlace;
	}

	public String getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient;
	}

	public int getDecimalPlace() {
		return decimalPlace;
	}

	public void setDecimalPlace(int decimalPlace) {
		this.decimalPlace = decimalPlace;
	}
}
