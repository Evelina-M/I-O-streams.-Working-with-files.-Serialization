package Conservation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(100, 5, 1, 2.5);
        GameProgress gameProgress2 = new GameProgress(80, 10, 3, 10.2);
        GameProgress gameProgress3 = new GameProgress(60, 15, 5, 25.7);

        saveGame("C:\\Users\\Эвелина\\IdeaProjects\\Input-OutputStreamsWorkingWithFilesSerialization_Installation\\src\\Installation\\Game\\savegames\\save1.dat", gameProgress1);
        saveGame("C:\\Users\\Эвелина\\IdeaProjects\\Input-OutputStreamsWorkingWithFilesSerialization_Installation\\src\\Installation\\Game\\savegames\\save2.dat", gameProgress2);
        saveGame("C:\\Users\\Эвелина\\IdeaProjects\\Input-OutputStreamsWorkingWithFilesSerialization_Installation\\src\\Installation\\Game\\savegames\\save3.dat", gameProgress3);

        List<String> pathToFile = new ArrayList<>();
        pathToFile.add("C:\\Users\\Эвелина\\IdeaProjects\\Input-OutputStreamsWorkingWithFilesSerialization_Installation\\src\\Installation\\Game\\savegames\\save1.dat");
        pathToFile.add("C:\\Users\\Эвелина\\IdeaProjects\\Input-OutputStreamsWorkingWithFilesSerialization_Installation\\src\\Installation\\Game\\savegames\\save2.dat");
        pathToFile.add("C:\\Users\\Эвелина\\IdeaProjects\\Input-OutputStreamsWorkingWithFilesSerialization_Installation\\src\\Installation\\Game\\savegames\\save3.dat");

        zipFiles("C:\\Users\\Эвелина\\IdeaProjects\\Input-OutputStreamsWorkingWithFilesSerialization_Installation\\src\\Installation\\Game\\savegames\\zip.zip", pathToFile);

        deleteFiles(pathToFile);
    }

    public static void saveGame(String pathToFile, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(pathToFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении игры: " + e.getMessage());
        }
    }

    public static void zipFiles(String pathToArchiveFile, List<String> pathToFile) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(pathToArchiveFile))) {
            for (String filePath : pathToFile) {
                try (FileInputStream fis = new FileInputStream(filePath)) {
                    ZipEntry zipEntry = new ZipEntry(new File(filePath).getName());
                    zos.putNextEntry(zipEntry);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) >= 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании архива: " + e.getMessage());
        }
    }

    public static void deleteFiles(List<String> filePaths) {
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
