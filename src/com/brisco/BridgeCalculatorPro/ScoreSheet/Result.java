package com.brisco.BridgeCalculatorPro.ScoreSheet;

import java.io.Serializable;

import com.brisco.Game.Card;

public class Result implements Serializable, Comparable<Result> {
	private static final long serialVersionUID = -5710864919245446553L;
	public int BoardNumber;
	public int Table;
	public com.brisco.Game.Contract Contract;
	public com.brisco.Game.Auction Auction;
	public com.brisco.Game.Deal Deal;
	public Card Lead;
	public float Score;

	@Override
	public int compareTo(Result another) {
		if (another == null) {
			return -1;
		}
		Integer me = (Integer) BoardNumber;
		return me.compareTo((Integer) another.BoardNumber);
	}
}
