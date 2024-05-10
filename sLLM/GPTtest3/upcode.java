import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

public class GPTFineTuningTest {

    public static void main(String[] args) {
        String url1 = "https://datasets-server.huggingface.co/splits?dataset=Anthropic%2Fhh-rlhf";
        String url2 = "https://datasets-server.huggingface.co/splits?dataset=stanfordnlp%2FSHP";
        downloadAndUnzip(url1, "rlhf1.zip");
        downloadAndUnzip(url2, "rlhf2.zip");
    }

    private static void downloadAndUnzip(String url, String outputFileName) {
        // Download the file
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(URI.create(url));
            client.execute(request, response -> {
                Files.copy(response.getEntity().getContent(), Paths.get(outputFileName));
                return null;
            });

            // Unzip the file
            unzip(outputFileName, "unzipped/");
            Files.deleteIfExists(Paths.get(outputFileName)); // Clean up the zip file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void unzip(String zipFileName, String outputDir) throws IOException {
        try (ZipFile zipFile = new ZipFile(zipFileName)) {
            zipFile.getEntries().asIterator().forEachRemaining(entry -> {
                if (!entry.isDirectory()) {
                    File outputFile = new File(outputDir, entry.getName());
                    outputFile.getParentFile().mkdirs();
                    try (InputStream zipStream = zipFile.getInputStream(entry);
                         OutputStream fileStream = new FileOutputStream(outputFile)) {
                        zipStream.transferTo(fileStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
