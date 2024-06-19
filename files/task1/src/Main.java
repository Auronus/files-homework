import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        File gamesDir = new File("D://Games");
        createDir(gamesDir, sb);

        File srcDir = new File(gamesDir.getPath() + "/src");
        File resDir = new File(gamesDir.getPath() + "/res");
        File savegamesDir = new File(gamesDir.getPath() + "/savegames");
        File tempDir = new File(gamesDir.getPath() + "/temp");

        createDir(srcDir, sb);
        createDir(resDir, sb);
        createDir(savegamesDir, sb);
        createDir(tempDir, sb);

        createDir(new File(srcDir.getPath() + "/main"), sb);
        createDir(new File(srcDir.getPath() + "/test"), sb);

        createFile(new File(srcDir.getPath() + "/main/Main.java"), sb);
        createFile(new File(srcDir.getPath() + "/main/Utils.java"), sb);

        createDir(new File(resDir.getPath() + "/drawables"), sb);
        createDir(new File(resDir.getPath() + "/vectors"), sb);
        createDir(new File(resDir.getPath() + "/icons"), sb);

        createFile(new File(tempDir.getPath() + "/temp.txt"), sb);

        try (FileWriter writer = new FileWriter(tempDir.getPath() + "/temp.txt", true)) {
            writer.write(sb.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static void createDir(File dir, StringBuilder sb) {
        if (dir.mkdir()) {
            sb.append("Директория " + dir.getName() + " создана\n");
        } else {
            sb.append("Не удалось создать директорию " + dir.getName() + "\n");
        }
    }

    private static void createFile(File file, StringBuilder sb) {
        try {
            if (file.createNewFile()) {
                sb.append("Файл " + file.getName() + " создан\n");
            } else {
                sb.append("Не удалось создать файл " + file.getName() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}