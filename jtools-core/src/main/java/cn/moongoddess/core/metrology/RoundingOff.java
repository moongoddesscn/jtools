package cn.moongoddess.core.metrology;

import java.math.BigDecimal;

/**
 *  系统字符串工具类
 *  @Author yanghd3
 */
public class RoundingOff {

	/**
	 * 修约系数
	 * @param level
	 * @return
	 */
	public static String getRoundingCoefficient(String level) {
		String coefficient;
		switch (level) {
			case "0.02":
			case "0.2":
			case "2":
				coefficient = "2"; break;
			case "0.05":
			case "0.5":
				coefficient = "5"; break;
			case "1":
				coefficient = "1"; break;
			default:
				coefficient = null; break;
		}
		return coefficient;
	}

	/**
	 * 修约 保留小数位数
	 * @param level
	 * @return
	 */
	public static int getRoundingDecimalPlace(String level) {
		int decimalPlace;
		switch (level) {
			case "0.02":
			case "0.05":
				decimalPlace = 3; break;
			case "0.2":
			case "0.5":
				decimalPlace = 2; break;
			case "1":
			case "2":
				decimalPlace = 1; break;
			default:
				decimalPlace = 0; break;
		}
		return decimalPlace;
	}

	/**
	 *  修约值算法
	 *  规则：四舍六入 50% 奇进偶不进
	 * @param value 修约前的值
	 * @param level 等级，支持（0.02，0.05，0.2，0.5，1，2）
	 * @return 修约后的值
	 */
	public static String  getByValueAndLevel(String value, String level){
		//四舍六入
		BigDecimal multiply = new BigDecimal(value)
				.divide(new BigDecimal(getRoundingCoefficient(level)))
				.setScale(getRoundingDecimalPlace(level), BigDecimal.ROUND_HALF_EVEN)
				.multiply(new BigDecimal(getRoundingCoefficient(level)));
		return numDecimal(multiply.toString(), getRoundingDecimalPlace(level));
	}

	/**
	 * 小数位不够自动补 “0”
	 * @param num 数值
	 * @param len 保留几位小数
	 */
	public static String numDecimal(String num, int len) {
		if (len==0) return num.split("\\.")[0];
		String decimal;
		if (num.indexOf(".") == -1 && len!=0) num += ".0";
		String[] split = num.split("\\.");
		decimal = split[1];
		num = split[0];
		if (decimal.length()>len){
			decimal = decimal.substring(0,len);
		}else {
			while (decimal.length() != len){
				decimal += "0";
			}
		}
		return num + "." + decimal;
	}
}
