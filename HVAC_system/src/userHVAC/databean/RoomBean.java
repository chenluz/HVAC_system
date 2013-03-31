package userHVAC.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class RoomBean {
	private int     id = -1;
	private int     userId = -1;
	private String  roomNum = null;
	private double  temperature =  0.0;
	private boolean hvacStatus = false;
	private boolean lightStatus = false;
	private boolean occupancyStatus = false;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public boolean isHvacStatus() {
		return hvacStatus;
	}
	public void setHvacStatus(boolean hvacStatus) {
		this.hvacStatus = hvacStatus;
	}
	public boolean isLightStatus() {
		return lightStatus;
	}
	public void setLightStatus(boolean lightStatus) {
		this.lightStatus = lightStatus;
	}
	public boolean isOccupancyStatus() {
		return occupancyStatus;
	}
	public void setOccupancyStatus(boolean occupancyStatus) {
		this.occupancyStatus = occupancyStatus;
	}
}