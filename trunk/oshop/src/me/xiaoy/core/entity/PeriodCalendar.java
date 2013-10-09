package me.xiaoy.core.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import me.xiaoy.core.common.Period;

@Embeddable
public class PeriodCalendar implements java.io.Serializable {

	private static final long serialVersionUID = -5740015034143299101L;

	/**
	 * 开始时间
	 */
	@Column( name="PERIOD_START" )
	private Calendar start;
	
	/**
	 * 结束时间
	 */
	@Column( name="PERIOD_END" )
	private Calendar end;	
	
	public PeriodCalendar() {
		super();
	}

	public PeriodCalendar(Calendar start, Calendar end) {
		super();		
		if (end != null && start!= null && end.compareTo(start) < 0){
			throw new IllegalArgumentException("start:" + start + ", end:" 
					+ end + ", 结束时间应该大于起始时间！" );
		}
		this.start = start;
		this.end = end;
	}
	
	/**
	 * 当天 00:00:00 - 23:59:59
	 * @return
	 */
	public static Period today( ){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date start = cal.getTime();
		
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MILLISECOND, -1 );
		Date end = cal.getTime();
		Period period = new Period(start,end);
		
		return period;
	}


	/**
	 * 交集
	 */
	public PeriodCalendar intersection (final PeriodCalendar other){
		if( other == null )
			return null;
		
		PeriodCalendar result = null;
		if( overlap( other ) ){
			Calendar start_ = this.start.after( other.start )?this.start:other.start;
			Calendar end_ = this.end.after( other.end )?other.end:this.end;
			result = new PeriodCalendar( start_,end_ );
		}
				
		return result;
	}
	
	/**
	 * 并集
	 */
	public PeriodCalendar union (final PeriodCalendar other){
		if( other == null )
			return this;
				
		PeriodCalendar result = null;
		if( overlap( other ) ){
			Calendar start_ = this.start.before( other.start )?this.start:other.start;
			Calendar end_ = this.end.before( other.end )?other.end:this.end;
			result = new PeriodCalendar(start_, end_);
		}
		
		return result;
	}
	
	/**
	 * 是否包含
	 */
	public boolean isIncluded (final Calendar date){
		if( date == null )
			return false;
		
		if( start == null && end == null )
			return true;
		else if( start == null )
			return (end.after(date) || end.equals( date ) );
		else if( end == null )
			return (start.before(date) || start.equals(date));
		else
			return (start.before(date) || start.equals(date)) 
				&& (end.after(date) || end.equals( date ) );
	}
	
	/**
	 * 是否包含
	 */
	public boolean isIncluded ( final PeriodCalendar period ){
		return this.isIncluded( period.start ) && this.isIncluded( period.end );
	}
	
	/**
	 * 是否相交
	 */
	public boolean overlap( final PeriodCalendar period ){
		return isIncluded( period.start ) || period.isIncluded( this.start );
	}
	
	/**
	 * 起始时间差
	 */
	public long duration( ){
		return this.start.getTimeInMillis() - this.end.getTimeInMillis();
	}
	
	/**
	 * 是否过期
	 */
	public boolean isExpired( Calendar date ){
		return this.isIncluded(date);
	}
	
	public String toString( ){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "Period[" + df.format(start) + " to " + df.format(end) + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		PeriodCalendar other = (PeriodCalendar) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	public Calendar getStart() {
		return start;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public Calendar getEnd() {
		return end;
	}

	public void setEnd(Calendar end) {
		this.end = end;
	}

	

	
}
