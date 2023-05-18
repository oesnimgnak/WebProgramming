package com.wplab.sessiontest;

import java.util.ArrayList;
import java.util.List;

public class ScoresDO {

	private List<Integer> scores = null;
	private double mean;
	private double sd;
	
	public int[] getScores() {		
		int[] scoreArray = null;
		
		if (scores.size() > 0) {
			scoreArray = new int[scores.size()];
			for (int i=0; i<scores.size(); i++) {
				scoreArray[i] = scores.get(i);
			}
		}
		
		return scoreArray;
	}
	
	public void setScores(int[] scores) {
		if (this.scores == null) {
			this.scores = new ArrayList<Integer>();
		}
		else {
			this.scores.clear();
		}
		
		for (int i=0; i<scores.length; i++) {
			this.scores.add(scores[i]);
		}
	}
	
	public void setScores(String[] scores) {
		if (this.scores == null) {
			this.scores = new ArrayList<Integer>();
		}
		else {
			this.scores.clear();
		}
		
		for (int i=0; i<scores.length; i++) {
//			if (scores[i] != "")
			if (scores[i].length() != 0)
				this.scores.add(Integer.parseInt(scores[i]));
		}
	}
	
	public double getMean() {
		return mean;
	}
	
	public void setMean(double mean) {
		this.mean = mean;
	}
	
	public double getSd() {
		return sd;
	}
	
	public void setSd(double sd) {
		this.sd = sd;
	}

	
}
