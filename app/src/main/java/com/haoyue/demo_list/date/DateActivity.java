package com.haoyue.demo_list.date;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.haoyue.demo_list.R;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateActivity extends AppCompatActivity {

    @BindView(R.id.tvMaxDay)
    TextView mTvMaxDay;
    @BindView(R.id.tvOutputDate)
    TextView mTvOutputDate;
    @BindView(R.id.tvWhichWeek)
    TextView mTvWhichWeek;
    @BindView(R.id.tvWhichDay)
    TextView mTvWhichDay;
    @BindView(R.id.tvAddMethod)
    TextView mTvAddMethod;
    @BindView(R.id.tvRollMethod)
    TextView mTvRollMethod;
    @BindView(R.id.tvTimeStamp)
    TextView mTvTimeStamp;
    @BindView(R.id.tvTimestampToDate)
    TextView mTvTimestampToDate;
    @BindView(R.id.tvTwoDay)
    TextView mTvTwoDay;
    @BindView(R.id.tvAmericanTime)
    TextView mTvAmericanTime;
    @BindView(R.id.tvEndDateOfMonth)
    TextView mTvEndDateOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        ButterKnife.bind(this);
        getMaxDayInMonrh(2016, 2);
//        initOutputDate();
        getWhichWeek(2015, 6, 21);
        getWhichDay(2017, 30, 3);
        addMethod(2017, 8, 1);
        rollMethod(2017, 8, 1);
        getTimeStamp();
        timestampToDate();
        getTwoDay("1987-4-6", "2017-8-4");
        getEDate("2017-8-4");
        getEndDateOfMonth("2016-02-04");
    }

    /**
     * 获取一个月的最后一天
     * @param dat
     * @return
     */
    public void getEndDateOfMonth(String dat) {// yyyy-MM-dd
        String str = dat.substring(0, 8);
        String year = dat.substring(0, 4);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            str += "31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            str += "30";
        } else {
            if ((Integer.parseInt(year) % 4) == 0 && (Integer.parseInt(year) % 100) == 0) {
                str += "28";
            } else if((Integer.parseInt(year) % 400) == 0|| (Integer.parseInt(year) % 4) == 0){
                str += "29";
            }else {
                str += "28";
            }
        }
        mTvEndDateOfMonth.setText("这个月的最后一天是：" + str);
    }

    /**
     * 返回美国时间格式 26 Apr 2006
     *
     * @param str
     * @return
     */
    public void getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        mTvAmericanTime.setText("得到的美国时间是：" + k[2] + k[1].toUpperCase() + k[5]);
    }


    /**
     * 得到二个日期间的间隔天数
     */
    public void getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
            mTvTwoDay.setText("二个日期间的间隔天数: " + day);
        } catch (Exception e) {
            mTvTwoDay.setText("二个日期间的间隔天数异常 ");
        }
    }


    /**
     * 将时间戳转化为标准时间
     * 输出：Tue Oct 07 12:04:36 CST 2014
     */
    public void timestampToDate() {
        long times = 2412654676572L;
        Date date = new Date(times);
        mTvTimestampToDate.setText("timestampToDate:  " + date);
    }

    /**
     * 获取时间戳
     * 输出结果:1438692801766
     */
    public void getTimeStamp() {
        Date date = new Date();
        long times = date.getTime();
        mTvTimeStamp.setText("times ===" + times);
    }


    private void rollMethod(int year, int month, int day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.roll(Calendar.DATE, -4);
        date = cal.getTime();
        String sdf = df.format(date);
        cal.roll(Calendar.DATE, 4);
        date = cal.getTime();
        mTvRollMethod.setText("roll()方法:" + sdf + "***" + df.format(date));
    }

    private void addMethod(int year, int month, int day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.add(Calendar.DATE, -5);
        Date date = cal.getTime();
        String datef = df.format(date);
        cal.add(Calendar.DATE, 6);
        date = cal.getTime();
        mTvAddMethod.setText("add()方法:" + datef + "***" + df.format(date));
    }

    /**
     * 计算一年中的第几星期是几号
     *
     * @param year
     * @param week
     * @param dayWeek
     */
    private void getWhichDay(int year, int week, int dayWeek) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, dayWeek);//dayWeek取值：0-6，对应星期天-星期六
//        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println(df.format(cal.getTime()));
        mTvWhichDay.setText("输入的星期是一年中的 " + df.format(cal.getTime()));
    }

    /**
     * 计算某一天是一年中的第几星期
     *
     * @param year
     * @param month
     * @param day
     */
    private void getWhichWeek(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        int weekno = cal.get(Calendar.WEEK_OF_YEAR);
        mTvWhichWeek.setText("输入的日期是一年中的第 " + weekno + "星期");
    }

    /**
     * 格式化输出日期时间:"yyyy-MM-dd hh:mm:ss","yyyyMMdd hh:mm:ss","yyyy/MM/dd hh:mm:ss",
     * "yyyy/MM/dd","yyyy-MM-dd","y年M月d日 h时m分s秒"
     * @param timeStyle
     */
    private void initOutputDate(String timeStyle) {

        Date date = new Date();
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf5 = new SimpleDateFormat("y年M月d日 h时m分s秒");
        String newStyle = "获取到的日期时间：" + sdf0.format(date) + "\n ***" + sdf1.format(date) + "***" +
                sdf2.format(date) + "***" + sdf3.format(date) + "\n ***" + sdf4.format(date) + "***" + sdf5.format(date);

        mTvOutputDate.setText("获取到的日期时间：" + sdf0.format(date) + "\n ***" + sdf1.format(date) + "***" +
                sdf2.format(date) + "***" + sdf3.format(date) + "\n ***" + sdf4.format(date) + "***" + sdf5.format(date));
    }

    private void calendarToDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
    }

    private void dateToCalendar() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
    }

    /**
     * 计算某一月份的最大天数
     *
     * @param year
     * @param month
     */
    private void getMaxDayInMonrh(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month - 1);//注意,Calendar对象默认一月为0
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        mTvMaxDay.setText("某一月份的最大天数是：" + day);
    }
}
