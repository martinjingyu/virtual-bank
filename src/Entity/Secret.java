package Entity;

/**
 * The Secret class represents a secret with an associated ID.
 */
public abstract class Secret {
    /** The ID associated with the secret. */
    protected String my_id;
    /** The secret information. */
    protected String my_secret;

    /**
     * Constructs a new Secret object with the specified ID and secret.
     *
     * @param my_id     the ID associated with the secret
     * @param my_secret the secret information
     */
    public Secret(String my_id, String my_secret) {
        this.my_id = my_id;
        this.my_secret = my_secret;
    }

    /**
     * Sets the secret information.
     *
     * @param my_secret the new secret information
     */
    public void setSecret(String my_secret) {
        this.my_secret = my_secret;
    }

    /**
     * Sets the ID associated with the secret.
     *
     * @param my_id the new ID to be set
     */
    public void setID(String my_id) {
        this.my_id = my_id;
    }

    /**
     * Retrieves the secret information.
     *
     * @return the secret information as a string
     */
    public String getSecret() {
        return my_secret;
    }

    /**
     * Retrieves the ID associated with the secret.
     *
     * @return the ID associated with the secret
     */
    public String getID() {
        return my_id;
    }

}
