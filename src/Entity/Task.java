package Entity;

public class Task {
    private String name;
    private double reward;
    private String state;

    public Task(String name, double reward, String state) {
        this.name = name;
        this.reward = reward;
        this.state = state;
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

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", reward=" + reward +
                ", state=" + state +
                '}';
    }
}
