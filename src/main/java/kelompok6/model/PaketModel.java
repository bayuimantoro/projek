package kelompok6.model;

public class PaketModel {
    private String id;
    private String paket;
    private String usernameUtama;

    // Constructor
    public PaketModel(String id, String paket, String usernameUtama) {
        this.id = id;
        this.paket = paket;
        this.usernameUtama = usernameUtama;
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

    public String getUsernameUtama() {
        return usernameUtama;
    }

    public void setUsernameUtama(String usernameUtama) {
        this.usernameUtama = usernameUtama;
    }
}
