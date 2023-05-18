package com.wplab.sessiontest;

public class ScoreProcessingService {

	public void processScores(ScoresDO scoresDo) {
		
		int[] scores = scoresDo.getScores();
		
		if (scores != null) {
			double sum = 0;
			double ssum = 0;
			for (int i=0; i<scores.length; i++) {
				sum += scores[i];
				ssum += scores[i] * scores[i];
			}
			
			double mean = sum / scores.length;
			double sd = Math.sqrt((ssum/scores.length) - mean*mean);
			
			scoresDo.setMean(mean);
			scoresDo.setSd(sd);
		}
	}
	
}
