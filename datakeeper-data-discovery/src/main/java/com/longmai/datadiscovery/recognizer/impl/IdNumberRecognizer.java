package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

import java.time.LocalDate;
import java.util.*;

/**
 * 身份证识别器
 */
public class IdNumberRecognizer extends AbstractRecognizer {

    private static final String ID_REGEX_18 = "(([1-6][1-9]|50)\\d{4}(18|19|20)\\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx])";
    private static final String ID_REGEX_15 = "(([1-6][1-9]|50)\\d{4}\\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\\d{3})";

    public IdNumberRecognizer(String name){
        super(name);
    }

    @Override
    public List<String> recognize(String text){
        List<String> idNumberList = super.recognize(text);
        List<String> result = new ArrayList<>();
        for (String idNumber : idNumberList){
            if (idNumber.length() == 15){
                idNumber = upgrade_18(idNumber);
            }
            if (checkProvinceCode(idNumber) && checkBirthCode(idNumber) && verifyCheckCode(idNumber)){
                result.add(idNumber);
            }
        }
        return result;
    }

    @Override
    public String getRegex() {
        return ID_REGEX_18.concat("|").concat(ID_REGEX_15);
    }

    public static void main(String[] args){

        String text = "阿的维尔大哦2519640斯\t\b蒂芬俄问题110225640302612阿斯地方而且为110225196426127\n asdfwerpuqwer 110225196426127awsd54523455231sdfg234的其二确认台湾\n";
        IdNumberRecognizer idNumberRecognizer = new IdNumberRecognizer(null);
        System.out.println(idNumberRecognizer.recognize(text));
    }

    private String upgrade_18(String idNumber){
        //从1999年10月1日起，全国实行公民身份证号码制度，居民身份证编号由原15位升至18位。
        //前6位为地址码；第七位至14位为出生日期码，
        StringBuffer sbf = new StringBuffer(idNumber);
        if (idNumber.length() == 15){
            char[] chars = idNumber.toCharArray();
            Integer year = Integer.parseInt(new String(new char[]{chars[6], chars[7]}));
            Integer month = Integer.parseInt(new String(new char[]{chars[8], chars[9]}));
            if(year == 99 && month > 9){
                throw new IllegalArgumentException("15位身份证号不合法");
            }
            sbf.insert(6,"19");
            sbf.append(generateCheckCode(sbf.toString()));
        }
        System.out.println("18位："+sbf.toString());
        return sbf.toString();
    }

    private boolean checkBirthCode(String idNumber){
        char[] chars = idNumber.toCharArray();
        int year = Integer.parseInt(""+chars[6]+chars[7]+chars[8]+chars[9]);
        int month = Integer.parseInt(""+chars[10]+chars[11]);
        int day = Integer.parseInt(""+chars[12]+chars[13]);
        try {
            LocalDate.of(year,month,day);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    private boolean checkProvinceCode(String idNumber){
        /**
         * 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",
         * 36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",
         * 61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门"
         */
        List<String> list = Arrays.asList(  "11","12","13","14","15","21","22","23","31",
                                            "32","33","34","35","36","37","41","42","43",
                                            "44","45","46","50","51","52","53","54","61",
                                            "62","63","64","65","71","81","82");
        String provinceCode = idNumber.substring(0, 2);
        return list.contains(provinceCode);
    }

    private boolean verifyCheckCode(String idNumber){
        String checkCode = generateCheckCode(idNumber);
        return checkCode.equals(idNumber.charAt(17)+"");
    }

    /**
     * 生成最后以为校验码
     * @param idNumber
     * @return
     */
    private String generateCheckCode(String idNumber){
        /**
         * 位数	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17
         * 乘数	7,  9,  10, 5,  8,  4,  2,  1,  6,  3,  7,  9,  10,  5,  8,  4,  2
         */
        int[] factorNumArr = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
        int sum = 0;
        for (int i=0; i<17; i++){
            char c = idNumber.charAt(i);
            sum += Integer.parseInt(c+"") * factorNumArr[i];
        }
        /**
         * 尾数对应表：
         * 0 1 2 3 4 5 6 7 8 9 10
         * 1 0 X 9 8 7 6 5 4 3 2
         */
        String[] checkCodeArr = {"1","0","X","9","8","7","6","5","4","3","2"};
        return checkCodeArr[sum % 11];
    }

}
