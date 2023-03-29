package cn.moongoddess.core;

public class StringTools {

    /**
     * 获取字符串首尾不是数字的下标
     * @param s 输入字符串
     * @return 下标
     */
    public static int getFirstIndexNotDigit(String s){
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                index = i;
                break;
            }
        }
        return index;
    }

}
