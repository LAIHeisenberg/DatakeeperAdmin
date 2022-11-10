package com.longmai.datadiscovery.recognizer.impl;

import com.longmai.datadiscovery.recognizer.AbstractRecognizer;

public class LinuxPasswdRecognizer extends AbstractRecognizer {

    public LinuxPasswdRecognizer(String name) {
        super(name);
    }

    @Override
    protected String getRegex() {
        return "([a-zA-Z0-9])+:[^:]*:\\d+:\\d+:[^:]*:[a-zA-Z/0-9_-]+:[a-zA-Z/0-9_-]+";
    }

    public static void main(String[] args){
        String text = "daemon:x:1:1:daemon:/usr/sbin:/usr/sbin/nologin\n" +
                "bin:x:2:2:bin:/bin:/usr/sbin/nologin\n" +
                "sys:x:3:3:sys:/dev:/usr/sbin/nologin\n" +
                "sync:x:4:65534:sync:/bin:/bin/sync\n" +
                "games:x:5:60:games:/usr/games:/usr/sbin/nologin\n" +
                "man:x:6:12:man:/var/cache/man:/usr/sbin/nologin\n" +
                "lp:x:7:7:lp:/var/spool/lpd:/usr/sbin/nologin\n" +
                "mail:x:8:8:mail:/var/mail:/usr/sbin/nologin\n" +
                "news:x:9:9:news:/var/spool/news:/usr/sbin/nologin\n" +
                "uucp:x:10:10:uucp:/var/spool/uucp:/usr/sbin/nologin\n" +
                "proxy:x:13:13:proxy:/bin:/usr/sbin/nologin\n" +
                "www-data:x:33:33:www-data:/var/www:/usr/sbin/nologin\n" +
                "backup:x:34:34:backup:/var/backups:/usr/sbin/nologin\n" +
                "list:x:38:38:Mailing List Manager:/var/list:/usr/sbin/nologin\n" +
                "irc:x:39:39:ircd:/var/run/ircd:/usr/sbin/nologin\n" +
                "gnats:x:41:41:Gnats Bug-Reporting System (admin):/var/lib/gnats:/usr/sbin/nologin\n" +
                "nobody:x:65534:65534:nobody:/nonexistent:/usr/sbin/nologin\n" +
                "systemd-network:x:100:102:systemd Network Management,,,:/run/systemd/netif:/usr/sbin/nologin\n" +
                "systemd-resolve:x:101:103:systemd Resolver,,,:/run/systemd/resolve:/usr/sbin/nologin\n" +
                "syslog:x:102:106::/home/syslog:/usr/sbin/nologin\n" +
                "messagebus:x:103:107::/nonexistent:/usr/sbin/nologin\n" +
                "_apt:x:104:65534::/nonexistent:/usr/sbin/nologin\n" +
                "sshd:x:105:65534::/run/sshd:/usr/sbin/nologin\n" +
                "dnsmasq:x:106:65534:dnsmasq,,,:/var/lib/misc:/usr/sbin/nologin\n" +
                "postfix:x:107:113::/var/spool/postfix:/usr/sbin/nologin\n" +
                "ftp:x:108:115:ftp daemon,,,:/srv/ftp:/usr/sbin/nologin\n";
        LinuxPasswdRecognizer linuxPasswdRecognizer = new LinuxPasswdRecognizer("");
        System.out.println(linuxPasswdRecognizer.recognize(text));
    }
}
