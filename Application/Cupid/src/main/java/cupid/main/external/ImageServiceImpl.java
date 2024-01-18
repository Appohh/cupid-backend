package cupid.main.external;

import cupid.main.config.custom_exceptions.FileSaveException;
import cupid.main.domain.other.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Component
public class ImageServiceImpl implements ImageService {
    private final String IMAGE_DIRECTORY;
    public ImageServiceImpl(@Value("${images.users.profile}") String imageDirectory) {
        this.IMAGE_DIRECTORY = imageDirectory;
    }
    @Override
    public String SaveImage(String image) {
        try {
            // decode base64 encoded string to image bytes
            byte[] imageBytes = Base64.getDecoder().decode(image);

            // unique id for filename
            String fileName = UUID.randomUUID() + ".jpg";
            // path creation
            String filePath = IMAGE_DIRECTORY + fileName;

            // Create the directory if it doesn't exist
            File directory = new File(IMAGE_DIRECTORY);
            if (!directory.exists()) {
                if(!directory.mkdirs()) {
                    throw new RuntimeException("Directories could not be created");
                }
            }

            // write image
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(imageBytes);
            }

            return fileName;

        } catch (IOException e) {
            // when wrong
            throw new FileSaveException("Something went wrong while saving file");
        }
    }
}
