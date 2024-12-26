package kelompok6.model;

public class PaketModel {
    private String id;
    private String paket;
    private String username;

    // Constructor
    public PaketModel(String id, String paket, String username) {
        this.id = id;
        this.paket = paket;
        this.username = username;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
