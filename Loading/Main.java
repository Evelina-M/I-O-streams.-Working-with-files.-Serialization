package Loading;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import Conservation.GameProgress;

public class Main {
    public static void main(String[] args) {
        // Распаковка архива
        openZip("C:\\Users\\Эвелина\\IdeaProjects\\Input-OutputStreamsWorkingWithFilesSerialization_Installation\\src\\Installation\\Game\\savegames\\zip.zip",

                "C:\\Users\\Эвелина\\IdeaProjects\\Input-OutputStreamsWorkingWithFilesSerialization_Installation\\src\\Installation\\Game\\savegames");

        // Десериализация файла сохранения и вывод в консоль
        GameProgress gameProgress = openProgress("C:\\Users\\Эвелина\\IdeaProjects\\Input-OutputStreamsWorkingWithFilesSerialization_Installation\\src\\Installation\\Game\\savegames\\save2.dat");
        System.out.println(gameProgress);
    }

    public static void openZip(String zipFilePath, String destDirectory) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(destDirectory + File.separator + fileName);
                new File(newFile.getParent()).mkdirs(); // Создание папок, если их нет
                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
                zipEntry = zis.getNextEntry();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при распаковке архива: " + e.getMessage());
        }
    }

    public static GameProgress openProgress(String filePath) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при чтении файла сохранения: " + e.getMessage());
        }
        return gameProgress;
    }
}
