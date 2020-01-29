import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for(int i=1; i<=10;i++){
            String encodedString = encoder.encode("sml12345");
            System.out.println(encodedString);
        }

    }

}
