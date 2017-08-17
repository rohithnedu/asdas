
public class DataHolder {
	
	
	String startTime;
	int Id_Event;
	String Date_Event;
	boolean isNotified  = false;

	public boolean isNotified() {
		return isNotified;
	}



	public void setNotified(boolean isNotified) {
		this.isNotified = isNotified;
	}



	public DataHolder(String startTime, String Date_Event, int Id_Event) {

		this.startTime = startTime;
		this.Date_Event = Date_Event;
		this.Id_Event = Id_Event;
        this.isNotified= false;
	}

	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getId_Event() {
		return Id_Event;
	}

	public void setId_Event(int id_Event) {
		Id_Event = id_Event;
	}

	public String getDate_Event() {
		return Date_Event;
	}

	public void setDate_Event(String date_Event) {
		Date_Event = date_Event;
	}



}
