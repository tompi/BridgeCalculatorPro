package com.brisco.BridgeCalculatorPro.persistence;

import java.io.IOException;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.brisco.BridgeCalculatorPro.Rubber.Rubber;
import com.brisco.Game.Contract;
import com.brisco.Game.Direction;
import com.brisco.Game.Suit;

public class DBTest {
	private Rubber _rubber;
	private Rubber _deserializedRubber;

	@Before
	public void setUp() throws Exception {
		_rubber = new Rubber();
		_rubber.Description = "Hei på meg";
		_rubber.Started = new Date(2010, 12, 24, 12, 0);
		_rubber.AddContract(new Contract(3, Suit.Hearts, true, false,
				Direction.South, 10));
		_rubber.AddContract(new Contract(5, Suit.Notrump, false, false,
				Direction.East, 4));
		_rubber.AddContract(new Contract(2, Suit.Clubs, true, true,
				Direction.West, 8));
		byte[] byteArray = DB.GetByteArrayFromTournamen(_rubber);
		_deserializedRubber = (Rubber) DB
				.GetCompetitionFromByteArray(byteArray);
	}

	@Test
	public void CheckDates() {
		Assert.assertEquals(_rubber.Started, _deserializedRubber.Started);
	}

	@Test
	public void CheckTricksContract3() {
		Contract c = _deserializedRubber.GetContract(2);

		Assert.assertEquals(8, c.Tricks);
	}

	@Test
	public void CheckSuitContract2() {
		Contract c = _deserializedRubber.GetContract(1);

		boolean equals = c.Suit == Suit.Notrump;
		Assert.assertEquals(true, equals);
	}

	@Test
	public void CheckPlayerContract1() {
		Contract c = _deserializedRubber.GetContract(0);
		boolean equals = c.Player == Direction.South;
		Assert.assertEquals(true, equals);
	}

	@Test
	public void CheckRubberWithoutContracts() throws IOException,
			ClassNotFoundException {
		Rubber r1 = new Rubber();
		byte[] byteArray = DB.GetByteArrayFromTournamen(r1);
		Rubber r2 = (Rubber) DB.GetCompetitionFromByteArray(byteArray);
		Assert.assertEquals(r1.Description, r2.Description);
	}

}
