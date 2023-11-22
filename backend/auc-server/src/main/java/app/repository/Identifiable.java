package app.repository;

public interface Identifiable {
    //This interface defines a contract
    // for entities to be identifiable,
    // providing methods to get and set the ID.
    long getId();

    void setId(long id);
}