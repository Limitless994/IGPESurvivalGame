package dev.igpe.theamazingame.scenario;

public class ScenarioManager {
	
	private Boolean isLoaded = Boolean.FALSE;
	private Scenario scenario;
	
	public ScenarioManager() {}
	
	public Boolean getIsLoaded() {
		return isLoaded;
	}
	
	public Scenario getScenario() {
		return scenario;
	}
	
	public void setIsLoaded(Boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
	
	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

}
