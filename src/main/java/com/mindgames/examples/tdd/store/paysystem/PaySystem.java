package com.mindgames.examples.tdd.store.paysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: andrebrov
 * Date: 12.02.13
 * Time: 20:32
 */
public class PaySystem {

    public void increaseProfit(int cash) {
        if (cash > 0) {
            long startTime = System.currentTimeMillis();
            double dollars = cash / 31;
            System.out.println("Summa v dollarah " + dbl2str(dollars));
            String sql = buildInsertString(dollars);
            Connection connection = null;
            try {
                connection = getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(getSecondsToTimeFormat(startTime, endTime));
        }
    }

    private Connection getConnection() throws Exception {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "");
        return connection;
    }

    protected String buildInsertString(double dollars) {
        String sql = (new StringBuilder("insert into ")).append("cash").append(" (").toString();
        sql += (new StringBuilder(String.valueOf(sql))).append("year,version_num,operatins,summ").toString();
        sql += (new StringBuilder(String.valueOf(sql))).append(") values ('").toString();
        sql += (new StringBuilder(String.valueOf(sql))).append("2012").append("',").toString();
        sql += (new StringBuilder(String.valueOf(sql))).append("325435").append("',").toString();
        sql += (new StringBuilder(String.valueOf(sql))).append("cash").append("',").toString();
        sql += (new StringBuilder(String.valueOf(sql))).append(dbl2str(dollars)).append("',").toString();
        sql += (new StringBuilder(String.valueOf(sql))).append("0,'Clarity','','')").toString();
        return sql;
    }

    private static String dbl2str(Object obj) {
        String value;
        String str = obj.toString();
        if (str.indexOf('E') != -1) {
            //System.out.print("1  ");
            String[] qwe = str.split("E");
            String newstr = "";
            int step = Integer.parseInt(qwe[1]);
            for (int ind = 0; ind < qwe[0].length(); ind++) {
                if (step > 0) {
                    if ((ind == (step + 2)) && (ind != qwe[0].length())) {
                        newstr = newstr + '.';
                    }

                    if (str.charAt(ind) != '.') {
                        newstr = newstr + str.charAt(ind);
                    }
                    if ((ind == qwe[0].length() - 1) && (ind <= step)) {
                        for (int ind2 = 0; ind2 < (step + 1 - ind); ind2++) {
                            newstr = newstr + "0";
                        }
                    }
                } else {
                    if (ind == 0) {
                        newstr = newstr + "0.";
                        for (int ind3 = 0; ind3 > step + 1; ind3--) {
                            newstr = newstr + "0";
                        }
                    }
                    if (str.charAt(ind) != '.') {
                        newstr = newstr + str.charAt(ind);
                    }
                }
            }
            value = newstr;
        } else {
            value = obj.toString();
        }
        return value;
    }


    private String getSecondsToTimeFormat(long startTime, long finishTime) {

        int secs = Math.round((finishTime - startTime) / 1000);

        int hours = secs / 3600,
                remainder = secs % 3600,
                minutes = remainder / 60,
                seconds = remainder % 60;

        StringBuilder result = new StringBuilder();

        if (hours > 0) {
            result.append((hours < 10 ? "0" : "") + hours).append(":");
        }

        if (minutes > 0 || hours > 0) {
            result.append((minutes < 10 ? "0" : "") + minutes).append(":");
        }

        if (seconds > 0 || hours > 0 || minutes > 0) {
            result.append((seconds < 10 ? "0" : "") + seconds);
        }

        if (hours == 0 && minutes == 0) {
            if (seconds == 1) {
                result.append(" second");
            } else {
                result.append(" seconds");
            }
        }

        return result.toString();
    }
}
