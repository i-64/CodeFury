package com.meetingRooms.entity;

public class AmenitiesEntity extends MeetingRoomEntity{
	private int ID;                      //to store amenities ID
	private String projector;            // to store checkbox values 
	private String wiFiConnection;
	private String conferenceCallFacility;
	private String whiteBoard;
	private String waterDispenser;
	private String TV;
	private String coffeeMachine;
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getProjector() {
		return projector;
	}
	public void setProjector(String projector) {
		this.projector = projector;
	}
	public String getWiFiConnection() {
		return wiFiConnection;
	}
	public void setWiFiConnection(String wiFiConnection) {
		this.wiFiConnection = wiFiConnection;
	}
	public String getConferenceCallFacility() {
		return conferenceCallFacility;
	}
	public void setConferenceCallFacility(String conferenceCallFacility) {
		this.conferenceCallFacility = conferenceCallFacility;
	}
	public String getWhiteBoard() {
		return whiteBoard;
	}
	public void setWhiteBoard(String whiteBoard) {
		this.whiteBoard = whiteBoard;
	}
	public String getWaterDispenser() {
		return waterDispenser;
	}
	public void setWaterDispenser(String waterDispenser) {
		this.waterDispenser = waterDispenser;
	}
	public String getTV() {
		return TV;
	}
	public void setTV(String tV) {
		TV = tV;
	}
	public String getCoffeeMachine() {
		return coffeeMachine;
	}
	public void setCoffeeMachine(String coffeeMachine) {
		this.coffeeMachine = coffeeMachine;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((TV == null) ? 0 : TV.hashCode());
		result = prime * result + ((coffeeMachine == null) ? 0 : coffeeMachine.hashCode());
		result = prime * result + ((conferenceCallFacility == null) ? 0 : conferenceCallFacility.hashCode());
		result = prime * result + ((projector == null) ? 0 : projector.hashCode());
		result = prime * result + ((waterDispenser == null) ? 0 : waterDispenser.hashCode());
		result = prime * result + ((whiteBoard == null) ? 0 : whiteBoard.hashCode());
		result = prime * result + ((wiFiConnection == null) ? 0 : wiFiConnection.hashCode());
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
		AmenitiesEntity other = (AmenitiesEntity) obj;
		if (ID != other.ID)
			return false;
		if (TV == null) {
			if (other.TV != null)
				return false;
		} else if (!TV.equals(other.TV))
			return false;
		if (coffeeMachine == null) {
			if (other.coffeeMachine != null)
				return false;
		} else if (!coffeeMachine.equals(other.coffeeMachine))
			return false;
		if (conferenceCallFacility == null) {
			if (other.conferenceCallFacility != null)
				return false;
		} else if (!conferenceCallFacility.equals(other.conferenceCallFacility))
			return false;
		if (projector == null) {
			if (other.projector != null)
				return false;
		} else if (!projector.equals(other.projector))
			return false;
		if (waterDispenser == null) {
			if (other.waterDispenser != null)
				return false;
		} else if (!waterDispenser.equals(other.waterDispenser))
			return false;
		if (whiteBoard == null) {
			if (other.whiteBoard != null)
				return false;
		} else if (!whiteBoard.equals(other.whiteBoard))
			return false;
		if (wiFiConnection == null) {
			if (other.wiFiConnection != null)
				return false;
		} else if (!wiFiConnection.equals(other.wiFiConnection))
			return false;
		return true;
	}
	
	

}
