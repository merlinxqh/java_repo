package com.imooc.base.utils;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.util.Assert;

import com.imooc.base.common.StringUtils;

public class DateUtil {
	
	public final static String GENERAL_FORMATTER="yyyy-MM-dd";
	public final static String GENERAL_FORMHMS="yyyy-MM-dd HH:mm:ss";
	public final static String FORMAT_YYYY_MM = "yyyy-MM";
	public final static String FORMAT_HH_MM_SS = "HH:mm:ss";
	public final static String FORMAT_OLD = "yyyy/MM/dd";
	public final static String FORMAT_YYYY_NIAN_MM_YUE_MM_RI = "yyyy年MM月dd日";
	public final static String FORMAT_YYYY_NIAN_M_YUE_M_RI = "yyyy年M月d日";
	
	public final static String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm" ;


	/**
	 * 将时间字符串变成date
	 * @param dateStr
	 * @return
	 */
	public static Date convertStrToDate(String dateStr, String formatter){
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("请确认时间的格式为"+formatter);
		}
		
		return date;
	}
	
	/**
	 * 将字符串 变成时间
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String convertDateToStr(Date date, String formatter){
		if(null == date){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		String  dateStr = sdf.format(date);
		return dateStr;
	}
	
	/**
	 * 将字符串 变成时间,格式为yyyy-MM-dd
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String convertDateToStr(Date date){
		if(date==null) return null;
		return convertDateToStr(date,GENERAL_FORMATTER);
	}
	
	/**
	 * 将字符串 变成时间,格式为yyyy-MM-dd
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static Date convertStrToDate(String dateStr){
		if(StringUtils.isEmpty(dateStr)) return null;
		return convertStrToDate(dateStr,GENERAL_FORMATTER);
	}
	
	/**
	 * 将字符串 变成时间,格式为yyyy-MM-dd hh:mm:ss
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static Date convertStrToDateHms(String dateStr){
		if(StringUtils.isEmpty(dateStr)) return null;
		return convertStrToDate(dateStr,GENERAL_FORMHMS);
	}
	
	/**
	 * 判断日期是否在strartTime日期和endTime日期之间  大于startTime,小于endTime
	 * 将时间变为字符串,格式为yyyy-MM-dd hh:mm:ss
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String convertDateToStrHms(Date date){
		if(StringUtils.isNotNull(date)){
			return convertDateToStr(date,GENERAL_FORMHMS);
		}
		return null;
	}
	
	/**
	 * 判断日期是否在strartTime日期和endTime日期之间
	 * @param startTime
	 * @param endTime
	 * @param needJudgeDate
	 * @return
	 */
	public static boolean judgeDateBetweenStartTimeAndEndTime(Date startTime, Date endTime, Date needJudgeDate){
		Date newStartTime = DateUtil.convertStrToDate(DateUtil.convertDateToStr(startTime, "yyyy-MM-dd"), "yyyy-MM-dd");
		Date newEndTime = DateUtil.convertStrToDate(DateUtil.convertDateToStr(endTime, "yyyy-MM-dd"), "yyyy-MM-dd");
		Date newNeedJudgeDate = DateUtil.convertStrToDate(DateUtil.convertDateToStr(needJudgeDate, "yyyy-MM-dd"), "yyyy-MM-dd");
		return ((newNeedJudgeDate.after(newStartTime)) && (newNeedJudgeDate.before(newEndTime)));
	}
	
	/**
	 * 判断日期是否在strartTime日期和endTime日期之间  大于等于startTime,小于等于endTime
	 * @param startTime
	 * @param endTime
	 * @param needJudgeDate
	 * @return
	 */
	public static boolean judgeDateBetweenStartTimeAndEndTimeAndEquals(Date startTime, Date endTime, Date needJudgeDate){
		Date newStartTime = DateUtil.convertStrToDate(DateUtil.convertDateToStr(startTime, "yyyy-MM-dd"), "yyyy-MM-dd");
		Date newEndTime = DateUtil.convertStrToDate(DateUtil.convertDateToStr(endTime, "yyyy-MM-dd"), "yyyy-MM-dd");
		Date newNeedJudgeDate = DateUtil.convertStrToDate(DateUtil.convertDateToStr(needJudgeDate, "yyyy-MM-dd"), "yyyy-MM-dd");
		return ((!newNeedJudgeDate.before(newStartTime)) && (!newNeedJudgeDate.after(newEndTime)));
	}
	
	/**
	 * 得到只有年和月的Date
	 * @param date
	 * @return
	 */
	public static Date getDateYyyyMmDd(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 *得到连个时间相差的秒钟时间
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDifferSecound(Date date1, Date date2){
		return (date1.getTime() - date2.getTime())/1000;
	}
	
	/**
	 * 得到两个时间相差的分钟时间
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDifferMinute(Date date1, Date date2){
		long time = date1.getTime() - date2.getTime();
		long minute = time/(60*1000);
		return minute;
	}
	
	/**
	 * 得到两个时间相差的小时
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDifferHour(Date date1, Date date2){
		long time = date1.getTime() - date2.getTime();
		long hour = time/(60*60*1000);
		return hour;
	}
	
	/**
	 * 得到两个时间相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long getDifferDay(Date date1, Date date2){
		if(date1==null || date2==null){
			return 0L;
		}
		long time = date1.getTime() - date2.getTime();
		long day = time/(60*60*1000*24);
		return day;
	}
	
	/**
	 * 得到两个时间相差的小时数
	 * @param time1 格式为：HH:mm
	 * @param time2 格式为: HH:mm
	 * @return
	 */
	public static long getDifferHour(String time1, String time2){
		Integer hour1 = Integer.parseInt(time1.split(":")[0]);
		Integer minute1 = Integer.parseInt(time1.split(":")[1]);
		Integer hour2 = Integer.parseInt(time2.split(":")[0]);
		Integer minute2 = Integer.parseInt(time2.split(":")[1]);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.HOUR_OF_DAY, hour1);
		calendar1.set(Calendar.MINUTE, minute1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.HOUR_OF_DAY, hour2);
		calendar2.set(Calendar.MINUTE, minute2);
		return getDifferHour(calendar1.getTime(), calendar2.getTime());
	}
	
	/**
	 * 得到两个时间相差的分钟数
	 * @param time1 格式为：HH:mm
	 * @param time2 格式为: HH:mm
	 * @return
	 */
	public static long getDifferMinute(String time1, String time2){
		Integer hour1 = Integer.parseInt(time1.split(":")[0]);
		Integer minute1 = Integer.parseInt(time1.split(":")[1]);
		Integer hour2 = Integer.parseInt(time2.split(":")[0]);
		Integer minute2 = Integer.parseInt(time2.split(":")[1]);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.HOUR_OF_DAY, hour1);
		calendar1.set(Calendar.MINUTE, minute1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.HOUR_OF_DAY, hour2);
		calendar2.set(Calendar.MINUTE, minute2);
		return getDifferMinute(calendar1.getTime(), calendar2.getTime());
	}
	
	/**
	 * 将分钟转换成小时
	 * @param minute
	 * @return
	 */
	public static BigDecimal getHour(long minute){
		return new BigDecimal(minute).divide(new BigDecimal(60),2,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 比较两个日期 
	 * 结果如果大于0则date1大于date2 小于0则是date1小于date2, 0则是相等
	 * @param date1
	 * @param date2
	 * @return 
	 */
	public static int compareDate(Date date1, Date date2){
		return date1.compareTo(date2);
	}
	
	public static boolean isCommonOneDay(Date date1, Date date2){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		
		return ((calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))
				&& (calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))
				&& (calendar1.get(Calendar.DATE) == calendar2.get(Calendar.DATE)));
	}
	
	/**
	 * 获取2个日期之间的天数(flag=1时，间隔的天数； flag=1时，去掉周日的间隔天数)
	 * @param begin
	 * @param end
	 * @param flag
	 * @return
	 * @author linyongan
	 * 2012-11-22
	 * @return int
	 */
	public static int countDays(String begin,String end,int flag){  
		int days = 1;    
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar c_b = Calendar.getInstance();  
		Calendar c_e = Calendar.getInstance();    
		try{   
			c_b.setTime(df.parse(begin));   
			c_e.setTime(df.parse(end));      
			while(c_b.before(c_e)){   
				days++;  
				if(flag==1 && c_b.get(Calendar.DAY_OF_WEEK)==1){
					days--;
				}
				c_b.add(Calendar.DAY_OF_YEAR, 1);   
			}     
		}catch(ParseException pe){   
//			System.out.println("日期格式必须为：yyyy-MM-dd；如：2012-1-1.");
			pe.printStackTrace();
		}   
		return days; 
	}

	/**
	 * 将日期转换成字符串
	 * @param date
	 * @param format
	 * @return 
	 * @return
	 */
	public static final  <T extends Date>String dateConvertStr(T date,String format){
		Assert.notNull(date, "日期为空！");
		SimpleDateFormat sd=new SimpleDateFormat(format);
		String str=sd.format(date);
		return str;
	}
	
	public static final <T extends Date>int getYear(T date){
		return getDateItems(date, Calendar.YEAR);
	}
	
	/**
	 * 功能说明：返回日期中的任何元素
	 * @param field：Calendar类中的常数，如YEAR/MONTH/DAY_OF_MONTH...
	 * <br><b><big>注意返回的month一月份是从0开始的！</big></b>
	 */
	public static final <T extends Date>int getDateItems(Date dDate, int field) {
		GregorianCalendar cl = new GregorianCalendar();
		cl.setTime(dDate);
		return cl.get(field);
	}

	/**
	 * 功能说明：得到日期
	 * @param dDate 传日的日期对象
	 * @return int日
	 */
	public static final <T extends Date>int getDay(Date dDate) {
		return getDateItems(dDate, Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 功能说明：得到月份
	 * @param dDate 传日的日期对象
	 * @return int得到月份
	 */
	public static int getMonth(Date dDate) {
		return getDateItems(dDate, Calendar.MONTH) + 1;
	}
	
	/**
	 * 获取当前服务器日期
	 * @return
	 */
	public static java.util.Date getCurDate(){
		return new java.util.Date();
	}
	
	
	
	/**
	 * 获得时间差[分,秒,毫秒]
	 * @param begTime
	 * @param endTime
	 * @return
	 */
	public static String getDiffTime(Date begTime,Date endTime){
		 SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String begDate= dfs.format(begTime);
		String endDate= dfs.format(endTime);
	     long between = 0;
	     try {
	         java.util.Date begin = dfs.parse(begDate);
	         java.util.Date end = dfs.parse(endDate);
	         between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
	     } catch (Exception ex) {
	         ex.printStackTrace();
	     }
	     long day = between / (24 * 60 * 60 * 1000);
	     long hour = (between / (60 * 60 * 1000) - day * 24);
	     long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
	     long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
	     long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
	             - min * 60 * 1000 - s * 1000);
	    return  min + "分" + s + "秒" + ms + "毫秒";
	}
	
	/**
	 * 得到向前计算相隔多少天的日期
	 * @param d
	 * @param diff
	 * @return
	 */
	public static Date getDateByDays(Date d,int diff){		
		if(null==d) d = new Date();
		long t = (d.getTime()/1000-diff*24 * 60 * 60) * 1000;
		Date nd = new Date(t);
		return nd;
	}

	
	/**
	 * 日期月 的第一天
	 * @param d
	 * @return
	 */
	public static Date getFirstDay(Date d){		
		if(d==null){
			d = new Date();//参数日期为空 默认当前日期
		 }
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(d);
		 cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	/**
	 * 日期月 的最后一天
	 * @param d
	 * @return
	 */
	public static Date getLastDay(Date d){
		if(d==null){
			d = new Date();//参数日期为空 默认当前日期
		 }
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(d);
		 cal.add(Calendar.MONTH, 1);//加一个月  得到下一下月
		 cal.set(Calendar.DAY_OF_MONTH, 1);//下一个月的第一天
		 cal.add(Calendar.DAY_OF_MONTH, -1);//减一天 给出日期的最后一天
		return cal.getTime();
	}
	
	/**
	 * 取日期的下一天
	 * @param d
	 * @return
	 */
	public static Date getNextDay(Date d){
		if(d==null){
			d = new Date();//参数日期为空 默认当前日期
		 }
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(d);
		 cal.add(Calendar.DAY_OF_MONTH, 1);//下一天
		return cal.getTime();
	}
	
	/**
	 * 取日期的上一天
	 * @param d
	 * @return
	 */
	public static Date getPrevDay(Date d){
		if(d==null){
			d = new Date();//参数日期为空 默认当前日期
		 }
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(d);
		 cal.add(Calendar.DAY_OF_MONTH, -1);//上一天
		return cal.getTime();
	}
}
