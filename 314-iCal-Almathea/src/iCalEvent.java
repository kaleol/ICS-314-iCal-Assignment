

public class iCalEvent {
	
	public String month;
	public String day;
	public String year;
	public String startTime;
	public String endTime;
	public String location;
	public String eventClass;
	public String name;
	public String totalDate;
	public String geo;
	public String comment;

	public iCalEvent() {
		super();
		this.month = null;
		this.day = null;
		this.year = null;
		this.startTime = null;
		this.endTime = null;
		this.location = null;
		this.eventClass = null;
		this.name = null;
		this.totalDate = null;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEventClass() {
		return eventClass;
	}
	public void setEventClass(String eventClass) {
		this.eventClass = eventClass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTotalDate() {
		return totalDate;
	}
	public void setTotalDate(String totalDate) {
		this.totalDate = totalDate;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

