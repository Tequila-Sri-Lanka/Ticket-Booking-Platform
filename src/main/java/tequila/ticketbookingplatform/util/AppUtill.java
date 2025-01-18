package tequila.ticketbookingplatform.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class AppUtill {
    public static File convertBase64ToImage(String base64String, String filePath) throws IOException {
        // Decode the Base64 string
        byte[] imageBytes = Base64.getDecoder().decode(base64String);

        // Create a file object
        File imageFile = new File(filePath);

        // Write the byte array to the file
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            fos.write(imageBytes);
        }

        return imageFile;
    }
}
