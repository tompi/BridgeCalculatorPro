package com.brisco.BridgeCalculatorPro.Duplicate;

import java.util.ArrayList;
import java.util.List;

import com.brisco.BridgeCalculatorPro.Competition;
import com.brisco.PBN.Game.Game;
import com.brisco.PBN.Game.Identification;
import com.brisco.PBN.Game.Supplemental;
import com.brisco.Score.Result;
import com.brisco.Tournament.BoardResult;
import com.brisco.Tournament.Round;
import com.brisco.Tournament.Session;
import com.brisco.Tournament.Tournament;

public class DuplicateEvent extends Competition {
	public DuplicateEvent() {
		_tournament = new Tournament();
	}

	private static final long serialVersionUID = -8073203483388831522L;

	private Tournament _tournament;

	@Override
	public void AppendHTML(StringBuilder html) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Game> GetPBNGames() {
		List<Game> ret = new ArrayList<Game>();
		for (Session s : _tournament.Sessions) {
			for (Round round : s.Rounds) {
				for (BoardResult b : round.BoardResults) {
					for (Result r : b.Results) {
						Game g = new Game();
						g.Identification = new Identification();
						g.Identification.Board = r.Board.Number;
						g.Identification.Contract = r.Contract;
						g.Identification.Dealer = r.Board.Dealer;
						g.Identification.Declarer = r.Contract.Player;
						g.Identification.Result = r.Contract.Tricks;
						g.Identification.Vulnerable = r.Board.Vulnerability;
						g.Identification.East = r.Table.EastWestPair.NorthEast.DisplayName;
						g.Identification.West = r.Table.EastWestPair.SouthWest.DisplayName;
						g.Identification.North = r.Table.NorthSouthPair.NorthEast.DisplayName;
						g.Identification.South = r.Table.NorthSouthPair.SouthWest.DisplayName;
						g.Supplemental = new Supplemental();
						g.Supplemental.Application = "BridgeCalculator Pro";
						g.Supplemental.ScoreMP = Double
								.toString(r.NorthSouthScore);
						ret.add(g);
					}
				}
			}
		}
		return ret;
	}
}
