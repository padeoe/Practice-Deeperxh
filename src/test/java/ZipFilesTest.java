import org.junit.jupiter.api.Test;

import java.io.IOException;

class ZipFilesTest {
    @Test
    void zipFiles() {
        String[] stringPaths = {
                "src/test/resources/zip_input1.txt",
                "src/test/resources/zip_input2.txt",
                "src/test/resources/zip_output.zip"
        };
        try {
            ZipFiles.main(stringPaths);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}