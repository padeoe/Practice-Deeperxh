import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles {

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            List<String> paths = Arrays.asList(args);
            zipFiles(paths.subList(0, paths.size() - 1).stream().map(stringPath -> Paths.get(stringPath)).collect(Collectors.toList()),
                    Paths.get(paths.get(paths.size() - 1)));
        }
        else {
            System.out.println("请输入要压缩的文件路径和输出的压缩文件路径");
        }
    }

    /**
     * @param srcFilesPaths  输入文件的路径
     * @param outputFilePath 输出文件的路径
     * @throws IOException IO异常
     */
    public static void zipFiles(List<Path> srcFilesPaths, Path outputFilePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(outputFilePath.toAbsolutePath().toString());
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        srcFilesPaths.stream().map(path -> path.toFile()).forEach(fileToZip -> {
            try {
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        zipOut.close();
        fos.close();

    }
}
