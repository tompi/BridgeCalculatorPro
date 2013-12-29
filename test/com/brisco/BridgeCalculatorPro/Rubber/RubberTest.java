package com.brisco.BridgeCalculatorPro.Rubber;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.brisco.Game.Contract;
import com.brisco.Game.Direction;
import com.brisco.Game.Suit;

public class RubberTest {
	RubberResult rubberResultACBL;
	RubberResult rubberResultWikipedia;

	@Before
	public void setUp() throws Exception {

		// Test case from http://www.acbl.org/learn/scoreRubber.html
		Rubber rubber = new Rubber();
		rubber.AddContract(new Contract(2, Suit.Hearts, false, false,
				Direction.North, 10));
		rubber.AddContract(new Contract(4, Suit.Spades, true, false,
				Direction.East, 8));
		rubber.AddContract(new Contract(3, Suit.Notrump, false, false,
				Direction.East, 10));
		rubber.AddContract(new Contract(3, Suit.Spades, false, false,
				Direction.East, 9));
		rubber.AddContract(new Contract(2, Suit.Diamonds, false, false,
				Direction.West, 6));
		rubber.AddContract(new Contract(6, Suit.Hearts, true, false,
				Direction.North, 13));
		rubber.AddContract(new Contract(1, Suit.Notrump, false, false,
				Direction.East, 8));
		rubber.AddContract(new Contract(3, Suit.Clubs, false, false,
				Direction.North, 9));
		rubber.AddContract(new Contract(3, Suit.Hearts, false, false,
				Direction.West, 9));
		rubberResultACBL = rubber.GetRubberResult();

		// Test case from http://en.wikipedia.org/wiki/Rubber_bridge
		rubber = new Rubber();
		rubber.AddContract(new Contract(1, Suit.Notrump, false, false,
				Direction.North, 8));
		rubber.AddContract(new Contract(3, Suit.Hearts, false, false,
				Direction.East, 9));
		rubber.AddContract(new Contract(3, Suit.Diamonds, true, false,
				Direction.East, 7));
		rubber.AddContract(new Contract(2, Suit.Notrump, false, false,
				Direction.North, 8));
		// NB: No honors, so no extra 100 for ew...
		rubber.AddContract(new Contract(6, Suit.Spades, false, false,
				Direction.East, 12));
		rubber.AddContract(new Contract(4, Suit.Spades, false, false,
				Direction.South, 10));
		rubberResultWikipedia = rubber.GetRubberResult();

	}

	@Test
	public void ACBL_H210N_scores_60_above_and_60_below() {
		int below = rubberResultACBL.NorthSouth.BelowLine1.get(0).Score;
		int above = rubberResultACBL.NorthSouth.AboveLine.get(0).Score;
		Assert.assertEquals(60, below);
		Assert.assertEquals(60, above);
	}

	@Test
	public void ACBL_S4X8E_scores_300_above() {
		int above = rubberResultACBL.NorthSouth.AboveLine.get(1).Score;
		Assert.assertEquals(300, above);
	}

	@Test
	public void ACBL_EW_Won_Rubber() {
		Assert.assertEquals(true, rubberResultACBL.HasRubberEnded());
		Assert.assertEquals(false, rubberResultACBL.NorthWon());
	}

	@Test
	public void ACBL_EW_Totals_880() {
		Assert.assertEquals(880, rubberResultACBL.EastWest.Score);
	}

	@Test
	public void ACBL_NS_Totals_1690() {
		Assert.assertEquals(1690, rubberResultACBL.NorthSouth.Score);
	}

	// Wikipedia example
	// //total sums are 1060 for N-S and 870 for E-W.
	@Test
	public void Wikipedia_EW_Totals_770() {
		Assert.assertEquals(770, rubberResultWikipedia.EastWest.Score);
	}

	@Test
	public void Wikipedia_NS_Totals_1060() {
		Assert.assertEquals(1060, rubberResultWikipedia.NorthSouth.Score);
	}

	@Test
	public void Wikipedia_NS_Won_Rubber() {
		Assert.assertEquals(true, rubberResultWikipedia.HasRubberEnded());
		Assert.assertEquals(true, rubberResultWikipedia.NorthWon());
	}

}
