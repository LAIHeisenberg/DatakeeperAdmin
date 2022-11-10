package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class GpsRecognizer extends AbstractRecognizer {


    /**
     * 89°00'00.0"S 180°00'00.0"E
     * -89.000000, 180.000000
     * @param name
     */
    //经度  ddd.ddddd °【度 . 度 格式】的十进制小数部分（6位）
    private final String LONGITUDE_REGX_1 = "([+-]?(((1[0-7][0-9])|180)|([1-9]{2}|10|20|30|40|50|60|70|80|90)|([0-9]{1}))\\.[0-9]{5,6})";
    //经度  ddd°mm.mmm’ 【度 . 分 . 分 格式】的十进制小数部分（3位）
    private final String LONGITUDE_REGX_2 = "([+-]?(((1[0-7][0-9])|180)|([1-9]{2}|10|20|30|40|50|60|70|80|90)|([0-9]{1}))°(([1-5]{1}[0-9]{1})|0[0-9]{1})\\.([0-9]{2,3})['’]?)";
    //经度  ddd°mm’ss’’ 【度 . 分 . 秒 格式】
    private final String LONGITUDE_REGX_3 = "(([+-]?(((1[0-7][0-9])|180)|([1-9]{2}|10|20|30|40|50|60|70|80|90)|([0-9]{1}))°(([1-5]{1}[0-9]{1})|0[0-9]{1}))['’]+(([1-5]{1}[0-9]{1})|0[0-9]{1})(\\.[0-9]?)?[”\"]+)";

    //纬度  ddd.ddddd °【度 . 度 格式】的十进制小数部分（6位）
    private final String LATITUDE__REGX_1 = "([+-]?(([1-8]{1}[0-9]{1})|([0]?[0-9]{1})|([1-9]{1}0))\\.[0-9]{5,6})";
    //纬度  ddd°mm.mmm’ 【度 . 分 . 分 格式】的十进制小数部分（3位）
    private final String LATITUDE__REGX_2 = "([+-]?(([1-8]{1}[0-9]{1})|([0]?[0-9]{1})|([1-9]{1}0))°(([1-5]{1}[0-9]{1})|0[0-9]{1})\\.([0-9]{2,3})['’]?)";
    //纬度  ddd°mm.mmm’ 【度 . 分 . 分 格式】的十进制小数部分（3位）
    private final String LATITUDE__REGX_3 = "(([+-]?(([1-8]{1}[0-9]{1})|([0]?[0-9]{1})|([1-9]{1}0))°(([1-5]{1}[0-9]{1})|0[0-9]{1}))['’]+(([1-5]{1}[0-9]{1})|0[0-9]{1})(\\.[0-9]?)?[”\"]+)";
    public GpsRecognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "(".concat(LATITUDE__REGX_1).concat("\\s?,\\s?").concat(LONGITUDE_REGX_1).concat(")").concat("|")
        .concat("(").concat(LATITUDE__REGX_2).concat("\\s?,\\s?").concat(LONGITUDE_REGX_2).concat(")").concat("|")
        .concat("(").concat(LATITUDE__REGX_3).concat("\\s?,\\s?").concat(LONGITUDE_REGX_3).concat(")").concat("|")
        .concat(LONGITUDE_REGX_1).concat("°").concat("|").concat(LATITUDE__REGX_1).concat("°").concat("|")
        .concat(LONGITUDE_REGX_2).concat("|").concat(LONGITUDE_REGX_3).concat("|").concat(LATITUDE__REGX_2).concat("|").concat(LATITUDE__REGX_3);
    }


    public static void main(String[] args){
        GpsRecognizer gpsRecognizer = new GpsRecognizer("经纬度");
        String inputText = "2°00'00.0\" 123°01'59.9\"";
        System.out.println(gpsRecognizer.getRegex());
        System.out.println(gpsRecognizer.recognize(inputText));
    }

}
