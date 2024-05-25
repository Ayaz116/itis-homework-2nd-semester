import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        User Ayaz = new User("Ayaz", "Mustafin", new Address("Kremlevskaya", "Kazan", "Russia"),"ayaz2005");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        try {
            String json = objectMapper.writeValueAsString(Ayaz);
            System.out.println("Данные пользователя:");
            System.out.println(json);

            User decodedUser = objectMapper.readValue(json, User.class);
            System.out.println();
            System.out.println(decodedUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}