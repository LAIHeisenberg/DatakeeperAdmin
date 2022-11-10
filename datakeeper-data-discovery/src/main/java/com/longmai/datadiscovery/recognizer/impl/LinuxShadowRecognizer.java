package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class LinuxShadowRecognizer extends AbstractRecognizer {


    public LinuxShadowRecognizer(String name) {
        super(name);
    }

    /**
     * 同 /etc/passwd 文件一样，文件中每行代表一个用户，同样使用 ":" 作为分隔符，
     * 不同之处在于，每行用户信息被划分为 9 个字段。每个字段的含义如下：用户名：加密密码：最后一次修改时间
     * ：最小修改时间间隔：密码有效期：密码需要变更前的警告天数：密码过期后的宽限时间：账号失效时间：保留字段
     * @return
     */
    @Override
    protected String getRegex() {
        return "([a-zA-Z0-9])+:[^:]*:\\d+:\\d+:\\d+:\\d*:\\d*:\\d*:";
    }

    public static void main(String[] args){
        String text =
                "man:*:17647:0:99999:7:::\n" +
                "lp:*:17647:0:99999:7:::\n" +
                "mail:*:17647:0:99999:7:::\n" +
                "news:*:17647:0:99999:7:::\n" +
                "uucp:*:17647:0:99999:7:::\n" +
                "proxy:*:17647:0:99999:7:::\n" +
                "www-data:*:17647:0:99999:7:::\n" +
                "backup:*:17647:0:99999:7:::\n" +
                "list:*:17647:0:99999:7:::\n" +
                "irc:*:17647:0:99999:7:::\n" +
                "gnats:*:17647:0:99999:7:::\n" +
                "nobody:*:17647:0:99999:7:::\n" +
                "systemd-network:*:17647:0:99999:7:::\n" +
                "systemd-resolve:*:17647:0:99999:7:::\n" +
                "syslog:*:17647:0:99999:7:::\n" +
                "messagebus:*:17647:0:99999:7:::\n" +
                "_apt:*:17647:0:99999:7:::\n" +
                "sshd:*:17651:0:99999:7:::\n" +
                "dnsmasq:*:17651:0:99999:7:::\n" +
                "postfix:*:19306:0:99999:7:::\n" +
                "ftp:*:19331:0:99999:7:::";
        LinuxShadowRecognizer linuxShadowRecognizer = new LinuxShadowRecognizer(null);
        System.out.println(linuxShadowRecognizer.recognize(text));

    }
}
