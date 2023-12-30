package ma.yc.api.myrhapi.utils;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

public class FileUtils {


    public static String uploadFileToFileSystem(MultipartFile file) {
        String filepath = null;
        if (file != null) {
            String extension = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
            String uploadDir = "src/main/resources/static";
            String fileName =  UUID.randomUUID() + "." + extension;
            filepath =  fileName;
            Path path = Path.of(uploadDir);
            if(!Files.exists(path)){
                try {
                    Files.createDirectories(path);
                } catch (Exception e) {
                    throw new RuntimeException("could not create the directory where the uploaded files will be stored");
                }
            }
            Path filePath = path.resolve(fileName);
            try {
                Files.copy(file.getInputStream(), filePath);
            } catch (Exception e) {
                throw new RuntimeException("could not store the file. Error: " + e.getMessage());
            }

        }
        //: UPLOAD THE IMAGE TO THE SERVER THEN RETURN THE PATH FROM THE SERVER
        if (filepath == null) {
            throw new RuntimeException("image is required");
        }
        return filepath;
    }
}
