package Entity;

public class Task {
    private String name;
    private double reward;
    private String state;
    private String description;
    private String destination;

    public Task(String name, double reward, String state, String description, String destination) {
        this.name = name;
        this.reward = reward;
        this.state = state;
        this.description = description;
        this.destination = destination;
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

    public void setState(String state){
        this.state = state;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String destination){
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCondition(String state){
        return switch (state) {
            case "ToBeConfirmed" -> "Submitted";
            case "ToBeTaken" -> "Pick it";
            case "Taken" -> "Action";
            default -> null;
        };
    }

    public String getCondition1(String state){
        return switch (state) {
            case "ToBeConfirmed" -> "Review";
            case "ToBeTaken" -> "Delete";
            case "Taken" -> "Pending";
            default -> null;
        };
    }


    public String getM(String state){
        return switch (state) {
            case "ToBeConfirmed" -> "Please wait patiently for parent's confirmation, you have submitted the task";
            case "ToBeTaken" -> "You have picked the task";
            case "Taken" -> "You have submitted the task";
            case "Confirmed" -> "Parents have confirmed your task, you have received reward $";
            default -> throw new IllegalStateException("Unexpected value: " + state);
        };
    }

    public String getCon1(String state){
        return switch (state) {
            case "ToBeConfirmed" -> "Submitted";
            case "ToBeTaken" -> "Do you want to pick this task?";
            case "Taken" -> "Do you want to submit or cancel this task? Yes for submit, No for cancel.";
            default -> null;
        };
    }

    public String getCon2(String state){
        return switch (state) {
            case "ToBeConfirmed" -> "Submitted";
            case "ToBeTaken" -> "You have taken this task!";
            case "Taken" -> "You have submitted this task!";
            default -> null;
        };
    }

    public String getText(String state){
        return switch (state) {
            case "ToBeConfirmed" -> "Please wait for parent's confirmation";
            case "ToBeTaken" -> "You can take this task";
            case "Taken" -> "You have taken this task.";
            default -> null;
        };
    }

    public Task taskOperation(Task task){
        switch (task.getState()) {
            case "ToBeConfirmed":{
                task.setState("Confirmed");
                break;
            }
            case "ToBeTaken":{
                task.setState("Taken");
                break;
            }
            case "Taken":{
                task.setState("ToBeConfirmed");
                break;
            }

        }
        return task;
    }

//    public void addSalary(Task task, String destination){
//        double current = task.getReward();
//
//    }



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