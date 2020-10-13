package json.test.p03_json_to_object;

import java.util.Date;

public class Target {

	String name;
	
	Date dateStr;

	public Target() {
		super();
	}

	public Target(String name, Date dateStr) {
		super();
		this.name = name;
		this.dateStr = dateStr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateStr() {
		return dateStr;
	}

	public void setDateStr(Date dateStr) {
		this.dateStr = dateStr;
	}
	
}
