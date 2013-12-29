package com.brisco.BridgeCalculatorPro.Teams;

import java.io.Serializable;

public class TeamsResult implements Serializable, Comparable<TeamsResult> {
	private static final long serialVersionUID = -1720287731799019650L;
	public int BoardNumber;
	public com.brisco.Game.Contract OpenContract;
	public com.brisco.Game.Contract ClosedContract;
	public com.brisco.Game.Auction OpenAuction;
	public com.brisco.Game.Auction ClosedAuction;
	public com.brisco.Game.Deal Deal;

	@Override
	public int compareTo(TeamsResult another) {
		if (another == null) {
			return -1;
		}
		Integer me = (Integer) BoardNumber;
		return me.compareTo((Integer) another.BoardNumber);
	}
}
