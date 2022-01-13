package ro.unibuc.egv.finalProject.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import ro.unibuc.egv.finalProject.models.Product;
import ro.unibuc.egv.finalProject.models.User;

import java.beans.JavaBean;
import java.util.Properties;

@JavaBean
public class MailSenderPSM {

    private final JavaMailSenderImpl javaMailSender;
    private final SimpleMailMessage simpleMailMessage;

    public MailSenderPSM(User user, Product boughtProduct, String subject){

        //region Mail Sender settings
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("playstationmall4298@gmail.com");
        javaMailSender.setPassword("adminPSM4298");
        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        //endregion

        //region Mail Text settings
        String building = user.getAddress().getBuilding() != null ? user.getAddress().getBuilding() : "-";
        String entrance = user.getAddress().getEntrance() != null ? user.getAddress().getEntrance() : "-";
        String floor = user.getAddress().getFloor() != null ? (user.getAddress().getFloor()).toString() : "-";
        String apartment = user.getAddress().getApartment() != null ? (user.getAddress().getApartment()).toString() : "-";
        String interphone = user.getAddress().getInterphone() != null ? user.getAddress().getInterphone() : "-";
        String mailText = "Hello " + user.getUsername() + ",\n\n"
                + "The product \"" + boughtProduct.getName() + "\", which costs $" + boughtProduct.getPrice()
                + ", will be delivered to you soon.\n\nIf you purchased other products until now, the bought items will be sorted at " +
                "the end of the day and they'll be arranged to be sent together to you, where you'll pay the total for all the products you bought when they arrive.\n" +
                "(Please note that the transport fee is included in the product's price)\n\n" +
                "The product(s) will arrive to the following address:\n\n" +
                user.getFirstName() + " " + user.getLastName() + "\n" +
                user.getAddress().getCountry() + ", " + user.getAddress().getDistrict() + ", " + user.getAddress().getCity() + " " + user.getAddress().getPostalCode() + "\n" +
                "Street: " + user.getAddress().getStreet() + "\n" +
                "Number: " + user.getAddress().getNumber() + "\n" +
                "Building: " + building + "\n" +
                "Entrance: " + entrance + "\n" +
                "Floor: " + floor + "\n" +
                "Apartment: " + apartment + "\n" +
                "Interphone: " + interphone + "\n\n" +
                "Thank you for buying from us! Have a nice day! :)\n" +
                "- PlayStation Mall";
        //endregion

        //region Mail Message settings
        simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply@PSM.com");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(mailText);
        //endregion

    }

    public void sendMessage(){
        javaMailSender.send(simpleMailMessage);
    }

}
