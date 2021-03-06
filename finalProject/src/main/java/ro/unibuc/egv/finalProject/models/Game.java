package ro.unibuc.egv.finalProject.models;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "games")
public class Game{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long gameID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "platform", nullable = false)
    private String platform;

    @Column(name = "edition", nullable = false)
    private String edition;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "game_mode", nullable = false)
    private String gameMode;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "developer", nullable = false)
    private String developer;

    public Game(Product product, String platform, String edition, String genre, String gameMode, String publisher, String developer) {
        this.product = product;
        this.platform = platform;
        this.edition = edition;
        this.genre = genre;
        this.gameMode = gameMode;
        this.publisher = publisher;
        this.developer = developer;
    }

    public Game(Long gameID, Product product, String platform, String edition, String genre, String gameMode, String publisher, String developer) {
        this.gameID = gameID;
        this.product = product;
        this.platform = platform;
        this.edition = edition;
        this.genre = genre;
        this.gameMode = gameMode;
        this.publisher = publisher;
        this.developer = developer;
    }

    public Game() {

    }

    public Long getGameID() {
        return gameID;
    }

    public void setGameID(Long gameID) {
        this.gameID = gameID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameID, game.gameID) && Objects.equals(product, game.product) && Objects.equals(platform, game.platform) && Objects.equals(edition, game.edition) && Objects.equals(genre, game.genre) && Objects.equals(gameMode, game.gameMode) && Objects.equals(publisher, game.publisher) && Objects.equals(developer, game.developer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID, product, platform, edition, genre, gameMode, publisher, developer);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "gameID = " + gameID + ", " +
                "product = " + product + ", " +
                "platform = " + platform + ", " +
                "edition = " + edition + ", " +
                "genre = " + genre + ", " +
                "gameMode = " + gameMode + ", " +
                "publisher = " + publisher + ", " +
                "developer = " + developer + ")";
    }
}
