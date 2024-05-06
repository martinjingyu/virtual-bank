package Entity;

public class Task {
    private String name;
    private double reward;
    private String state;
    private String description;

    public Task(String name, double reward, String state, String description) {
        this.name = name;
        this.reward = reward;
        this.state = state;
        this.description = description;
    }

    // Getter and setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter methods for reward
    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public String getState(){
        return state;
    }

    public void setState(){
        this.state = state;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(){
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", reward=" + reward +
                ", state=" + state +
                ", description=" + description +
                '}';
    }
}
