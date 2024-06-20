import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        Task1.createDirsForGame();

        GameProgress gameProgress1 = new GameProgress(100, 2, 3, 100.0);
        GameProgress gameProgress2 = new GameProgress(65, 4, 5, 73.5);
        GameProgress gameProgress3 = new GameProgress(81, 7, 10, 1234.56);

        saveGame("D://Games/savegames/save1.dat", gameProgress1);
        saveGame("D://Games/savegames/save2.dat", gameProgress2);
        saveGame("D://Games/savegames/save3.dat", gameProgress3);

        zipFiles("D://Games/savegames/zip.zip",
                List.of("D://Games/savegames/save1.dat", "D://Games/savegames/save2.dat", "D://Games/savegames/save3.dat"));

        deleteSaves(new File("D://Games/savegames/"));
    }

    private static void saveGame(String path, GameProgress gameProgress) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void zipFiles(String zipPath, List<String> files) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (String file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    zos.putNextEntry(new ZipEntry(file.substring(file.lastIndexOf("/") + 1)));
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void deleteSaves(File savesDir) {
        for (File file : Objects.requireNonNull(savesDir.listFiles())) {
            if (!getFileExtension(file).equals("zip")) {
                file.delete();
            }
        }
    }

    private static String getFileExtension(File file) {
        return file.getName().substring(file.getName().lastIndexOf(".") + 1);
    }

}