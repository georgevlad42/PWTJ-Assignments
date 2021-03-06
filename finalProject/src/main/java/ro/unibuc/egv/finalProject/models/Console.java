package ro.unibuc.egv.finalProject.models;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "consoles")
public class Console{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long consoleID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "edition", nullable = false)
    private String edition;

    @Column(name = "spec_gpu", nullable = false)
    private String GPU;

    @Column(name = "spec_cpu", nullable = false)
    private String CPU;

    @Column(name = "spec_memory", nullable = false)
    private String memory;

    @Column(name = "spec_storage", nullable = false)
    private String storage;

    @Column(name = "spec_sound", nullable = false)
    private String sound;

    @Column(name = "spec_os", nullable = false)
    private String OS;

    @Column(name = "spec_media", nullable = false)
    private String media;

    @Column(name = "color", nullable = false)
    private String color;

    public Console(Product product, String edition, String GPU, String CPU, String memory, String storage, String sound, String OS, String media, String color) {
        this.product = product;
        this.edition = edition;
        this.GPU = GPU;
        this.CPU = CPU;
        this.memory = memory;
        this.storage = storage;
        this.sound = sound;
        this.OS = OS;
        this.media = media;
        this.color = color;
    }

    public Console(Long consoleID, Product product, String edition, String GPU, String CPU, String memory, String storage, String sound, String OS, String media, String color) {
        this.consoleID = consoleID;
        this.product = product;
        this.edition = edition;
        this.GPU = GPU;
        this.CPU = CPU;
        this.memory = memory;
        this.storage = storage;
        this.sound = sound;
        this.OS = OS;
        this.media = media;
        this.color = color;
    }

    public Console (){

    }

    public Long getConsoleID() {
        return consoleID;
    }

    public void setConsoleID(Long consoleID) {
        this.consoleID = consoleID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return Objects.equals(consoleID, console.consoleID) && Objects.equals(product, console.product) && Objects.equals(edition, console.edition) && Objects.equals(GPU, console.GPU) && Objects.equals(CPU, console.CPU) && Objects.equals(memory, console.memory) && Objects.equals(storage, console.storage) && Objects.equals(sound, console.sound) && Objects.equals(OS, console.OS) && Objects.equals(media, console.media) && Objects.equals(color, console.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consoleID, product, edition, GPU, CPU, memory, storage, sound, OS, media, color);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "consoleID = " + consoleID + ", " +
                "product = " + product + ", " +
                "edition = " + edition + ", " +
                "GPU = " + GPU + ", " +
                "CPU = " + CPU + ", " +
                "memory = " + memory + ", " +
                "storage = " + storage + ", " +
                "sound = " + sound + ", " +
                "OS = " + OS + ", " +
                "media = " + media + ", " +
                "color = " + color + ")";
    }
}
