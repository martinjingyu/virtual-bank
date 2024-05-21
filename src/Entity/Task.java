package Entity;

/**
 * The Task class represents a task with various properties such as name, reward, state, description, and destination.
 */
public class Task {
    private String name;
    private double reward;
    private String state;
    private String description;
    private String destination;

    /**
     * Constructs a new Task with the specified name, reward, state, description, and destination.
     *
     * @param name the name of the task
     * @param reward the reward for completing the task
     * @param state the current state of the task
     * @param description a brief description of the task
     * @param destination the destination associated with the task
     */
    public Task(String name, double reward, String state, String description, String destination) {
        this.name = name;
        this.reward = reward;
        this.state = state;
        this.description = description;
        this.destination = destination;
    }

    /**
     * Returns the name of the task.
     *
     * @return the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the task.
     *
     * @param name the new name of the task
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the reward for completing the task.
     *
     * @return the reward for completing the task
     */
    public double getReward() {
        return reward;
    }

    /**
     * Sets the reward for completing the task.
     *
     * @param reward the new reward for completing the task
     */
    public void setReward(double reward) {
        this.reward = reward;
    }

    /**
     * Returns the current state of the task.
     *
     * @return the current state of the task
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the current state of the task.
     *
     * @param state the new state of the task
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description the new description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the deposit destination associated with the task.
     *
     * @return the destination associated with the task
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the deposit destination associated with the task.
     *
     * @param destination the new destination associated with the task
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Returns a condition message based on the state of the task.
     *
     * @param state the state of the task
     * @return a condition message based on the state
     */
    public String getCondition(String state) {
        return switch (state) {
            case "ToBeConfirmed" -> "Submitted";
            case "ToBeTaken" -> "Pick it";
            case "Taken" -> "Action";
            default -> null;
        };
    }

    /**
     * Returns the condition based on the state, that should be seen by the parent.
     *
     * @param state the state of the task
     * @return an alternative condition message based on the state
     */
    public String getCondition1(String state) {
        return switch (state) {
            case "ToBeConfirmed" -> "Review";
            case "ToBeTaken" -> "Delete";
            case "Taken" -> "Pending";
            default -> null;
        };
    }

    /**
     * Returns a message based on the state of the task.
     *
     * @param state the state of the task
     * @return a message based on the state
     */
    public String getM(String state) {
        return switch (state) {
            case "ToBeConfirmed" -> "Please wait patiently for parent's confirmation, you have submitted the task";
            case "ToBeTaken" -> "You have picked the task";
            case "Taken" -> "You have submitted the task";
            case "Confirmed" -> "Parents have confirmed your task, you have received reward $";
            default -> throw new IllegalStateException("Unexpected value: " + state);
        };
    }

    /**
     * Returns a confirmation message based on the state of the task.
     *
     * @param state the state of the task
     * @return a confirmation message based on the state
     */
    public String getCon1(String state) {
        return switch (state) {
            case "ToBeConfirmed" -> "Submitted";
            case "ToBeTaken" -> "Do you want to pick this task?";
            case "Taken" -> "Do you want to submit or cancel this task? Yes for submit, No for cancel.";
            default -> null;
        };
    }

    /**
     * Returns an information message based on the state of the task.
     *
     * @param state the state of the task
     * @return an alternative confirmation message based on the state
     */
    public String getCon2(String state) {
        return switch (state) {
            case "ToBeConfirmed" -> "Submitted";
            case "ToBeTaken" -> "You have taken this task!";
            case "Taken" -> "You have submitted this task!";
            default -> null;
        };
    }

    /**
     * Returns a task condition description text message based on the state of the task, which should be seen by child.
     *
     * @param state the state of the task
     * @return a text message based on the state
     */
    public String getText(String state) {
        return switch (state) {
            case "ToBeConfirmed" -> "Please wait for parent's confirmation";
            case "ToBeTaken" -> "You can take this task";
            case "Taken" -> "You have taken this task.";
            default -> null;
        };
    }

    /**
     * Performs an operation on the task based on its current state.
     *
     * @param task the task to perform the operation on
     * @return the task after performing the operation
     */
    public Task taskOperation(Task task) {
        switch (task.getState()) {
            case "ToBeConfirmed" -> task.setState("Confirmed");
            case "ToBeTaken" -> task.setState("Taken");
            case "Taken" -> task.setState("ToBeConfirmed");
        }
        return task;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", reward=" + reward +
                ", state=" + state +
                ", description=" + description +
                ", destination=" + destination +
                '}';
    }
}
