package com.luxoft.model;

import java.util.Objects;

public class Effort {

    private String team;
    private double totalEffort;
    private double remainingEffort;

    public Effort() {
    }

    public Effort(String team) {
        this.team = team;
    }

    public Effort(String team, double totalEffort, double remainingEffort) {
        this.team = team;
        this.totalEffort = totalEffort;
        this.remainingEffort = remainingEffort;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public double getTotalEffort() {
        return totalEffort;
    }

    public void setTotalEffort(double totalEffort) {
        this.totalEffort = totalEffort;
    }

    public double getRemainingEffort() {
        return remainingEffort;
    }

    public void setRemainingEffort(double remainingEffort) {
        this.remainingEffort = remainingEffort;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", team, totalEffort, remainingEffort);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Effort effort = (Effort) o;
        return Double.compare(effort.totalEffort, totalEffort) == 0 && Double.compare(effort.remainingEffort, remainingEffort) == 0 && Objects.equals(team, effort.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, totalEffort, remainingEffort);
    }
}
