package cn.moongoddess.core.metrology;

import cn.moongoddess.core.model.RoundingParams;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *  修约值算法
 *  @author yanghd
 */
public class RoundingOff {

	/**
	 *  修约值算法
	 *  规则：四舍六入 50% 奇进偶不进
	 * @param value 修约前的值
	 * @param level 等级，支持（0.02，0.05，0.2，0.5，1，2）
	 * @return 修约后的值
	 */
	public String get(String value, String level){
		RoundingParams rp;
		switch (level) {
			case "0.02":
				rp = new RoundingParams("2", 3);
				break;
			case "0.05":
				rp = new RoundingParams("5", 3);
				break;
			case "0.2":
				rp = new RoundingParams("2", 2);
				break;
			case "0.5":
				rp = new RoundingParams("5", 2);
				break;
			case "1":
				rp = new RoundingParams("1", 1);
				break;
			case "2":
				rp = new RoundingParams("2", 1);
				break;
			default:
				rp = null;
		}

		if (rp == null) return null;

		//四舍六入
		BigDecimal multiply = new BigDecimal(value)
				.divide(new BigDecimal(rp.getCoefficient()))
				.setScale(rp.getDecimalPlace(), RoundingMode.HALF_EVEN)
				.multiply(new BigDecimal(rp.getCoefficient()));
		return numDecimal(multiply.toString(), rp.getDecimalPlace());
	}

	/**
	 * 小数位不够自动补 “0”
	 * @param num 数值
	 * @param len 保留几位小数
	 * @return 处理小数后的值
	 */
	private String numDecimal(String num, int len) {
		if (len==0) return num.split("\\.")[0];
		String decimal;
		if (!num.contains(".")) num += ".0";
		String[] split = num.split("\\.");
		decimal = split[1];
		num = split[0];
		if (decimal.length()>len){
			decimal = decimal.substring(0,len);
			return num + "." + decimal;
		}else {
			StringBuilder builder = new StringBuilder();
			builder.append(decimal);
			while (decimal.length() != len){
				builder.append("0");
			}
			return num + "." + builder;
		}
	}
}
