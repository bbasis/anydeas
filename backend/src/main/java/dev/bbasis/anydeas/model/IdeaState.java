package dev.bbasis.anydeas.model;

public enum IdeaState {
    NEW("STATE_NEW"), ANALYZING("STATE_ANALYZING"), WAITING("STATE_ANALYZING"),
    IMPLEMENTING("STATE_IMPLEMENTING"), REJECTED("STATE_REJECTED"),
    CANCELED("STATE_CANCELED"), IN_PRODUCTION ("STATE_IN_PRODUCTION");

    private final String name;

    IdeaState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "IdeaState{" +
                "name='" + name + '\'' +
                '}';
    }
}
