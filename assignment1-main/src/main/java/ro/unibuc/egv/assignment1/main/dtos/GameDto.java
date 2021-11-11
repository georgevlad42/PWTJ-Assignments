package ro.unibuc.egv.assignment1.main.dtos;

import java.util.Objects;

public final class GameDto {

    private final String name;
    private final int price;

    public GameDto(String name, int price) {
        this.name = name;
        this.price = price;
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
        GameDto gameDto = (GameDto) o;
        return price == gameDto.price && Objects.equals(name, gameDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
