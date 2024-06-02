package pl.com.itsystems.taskmanager;

public enum Status {
    TO_DO("do zrobienia"), IN_PROGRESS("w toku"), DONE("zrobione");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
