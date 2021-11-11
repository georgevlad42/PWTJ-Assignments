package ro.unibuc.egv.assignment1.main.model;

import java.util.Objects;

public final class Game {

    private final Integer id;
    private final String name;
    private final int price;

    public Game(Integer id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Game(String name, int price) {
        this(null, name, price);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return price == game.price && Objects.equals(id, game.id) && Objects.equals(name, game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
