package com.brisco.BridgeCalculatorPro.Contract;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.brisco.BridgeCalculatorPro.R;
import com.brisco.Game.Card;
import com.brisco.Game.Deal;
import com.brisco.Game.Denomination;
import com.brisco.Game.Direction;
import com.brisco.Game.Hand;
import com.brisco.Game.Suit;

public class CardViews extends ViewsHelper {
	public CardViews(Activity activity) {
		super(activity);

		// Suit
		for (int i = 0; i < 4; i++) {
			Suit[i] = (Button) Find(suitIds[i]);
		}

		// Denomination
		for (int i = 0; i < 13; i++) {
			Denomination[i] = (ToggleButton) Find(denominationIds[i]);
		}

		OKButton = (LinearLayout) Find(R.id.cardInputOKBtn);
	}

	public Button[] Suit = new Button[4];
	public ToggleButton[] Denomination = new ToggleButton[13];
	public LinearLayout OKButton;

	public Suit GetSuit() {
		for (int i = 0; i < Suit.length; i++) {
			if (!Suit[i].isEnabled())
				return suits[i];
		}
		return null;
	}

	public void ToggleToggleButtons(TextView[] buttons, int clickedButtonID) {
		for (TextView b : buttons) {
			if (b.getId() == clickedButtonID) {
				b.setEnabled(false);
			} else {
				b.setEnabled(true);
			}

		}
	}

	public void ClearControls() {
		for (Button b : Suit) {
			b.setEnabled(true);
		}
		for (ToggleButton b : Denomination) {
			b.setChecked(false);
		}
	}

	public List<Card> getCards() {
		Suit suit = GetSuit();
		LinkedList<Card> list = new LinkedList<Card>();
		for (ToggleButton b : Denomination) {
			if (b.isChecked()) {
				com.brisco.Game.Denomination d = GetDenominationFromToggleButton(b);
				list.add(0, new Card(suit, d));
			}
		}
		return list;
	}

	private com.brisco.Game.Denomination GetDenominationFromToggleButton(
			ToggleButton b) {
		int id = b.getId();
		for (int i = 0; i < denominationIds.length; i++) {
			if (denominationIds[i] == id)
				return denominations[i];
		}
		return null;
	}

	public void markSelectedCards(Suit suit, Deal deal, Direction direction) {
		Hand hand = deal.getHand(direction);
		for (ToggleButton b : Denomination) {
			boolean selected = false;
			boolean enabled = true;
			com.brisco.Game.Denomination denomination = GetDenominationFromToggleButton(b);
			Card card = new Card(suit, denomination);
			if (hand != null) {
				selected = hand.contains(card);
			}
			if (!selected && deal.contains(card)) {
				enabled = false;
			}
			if (selected != b.isChecked()) {
				b.setChecked(selected);
			}
			if (enabled != b.isEnabled()) {
				b.setEnabled(enabled);
			}
		}
	}

	public void markSelectedCard(Card card) {
		for (int i = 0; i < suits.length; i++) {
			Suit[i].setEnabled(card != null && suits[i] == card.Suit);
		}
		for (int i = 0; i < denominations.length; i++) {
			Denomination[i].setChecked(card != null
					&& denominations[i] == card.Denomination);
		}
	}

	private Suit[] suits = { com.brisco.Game.Suit.Clubs,
			com.brisco.Game.Suit.Diamonds, com.brisco.Game.Suit.Hearts,
			com.brisco.Game.Suit.Spades };

	private int[] suitIds = { R.id.dealSuitClubs, R.id.dealSuitDiamonds,
			R.id.dealSuitHearts, R.id.dealSuitSpades };

	private Denomination[] denominations = { com.brisco.Game.Denomination.Two,
			com.brisco.Game.Denomination.Three,
			com.brisco.Game.Denomination.Four,
			com.brisco.Game.Denomination.Five,
			com.brisco.Game.Denomination.Six,
			com.brisco.Game.Denomination.Seven,
			com.brisco.Game.Denomination.Eight,
			com.brisco.Game.Denomination.Nine,
			com.brisco.Game.Denomination.Ten,
			com.brisco.Game.Denomination.Jack,
			com.brisco.Game.Denomination.Queen,
			com.brisco.Game.Denomination.King, com.brisco.Game.Denomination.Ace };

	private int[] denominationIds = { R.id.dealTwo, R.id.dealThree,
			R.id.dealFour, R.id.dealFive, R.id.dealSix, R.id.dealSeven,
			R.id.dealEight, R.id.dealNine, R.id.dealTen, R.id.dealJack,
			R.id.dealQueen, R.id.dealKing, R.id.dealAce };

}
