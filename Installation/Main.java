package Installation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String gamesDirectoryPath = "D:\\DataBase education\\Курсы\\Нетология\\Потоки ввода-вывода. Работа с файлами. Сериализация\\Установка\\Games";
        StringBuilder logBuilder = new StringBuilder();

        File gamesDirectory = new File(gamesDirectoryPath);
        if (!gamesDirectory.exists()) {
            logBuilder.append("Папка Games не существует. Создание папки...\n");
            boolean created = gamesDirectory.mkdir();
            if (created) {
                logBuilder.append("Папка Games успешно создана.\n");
            } else {
                logBuilder.append("Не удалось создать папку Games.\n");
                return;
            }
        }

        File srcDirectory = new File(gamesDirectoryPath + "/src");
        createDirectory(srcDirectory, logBuilder);

        File mainDirectory = new File(srcDirectory.getPath() + "/main");
        createDirectory(mainDirectory, logBuilder);
        createFile(mainDirectory, "Main.java", logBuilder);
        createFile(mainDirectory, "Utils.java", logBuilder);

        File testDirectory = new File(srcDirectory.getPath() + "/test");
        createDirectory(testDirectory, logBuilder);

        File resDirectory = new File(gamesDirectoryPath + "/res");
        createDirectory(resDirectory, logBuilder);

        File drawablesDirectory = new File(resDirectory.getPath() + "/drawables");
        createDirectory(drawablesDirectory, logBuilder);

        File vectorsDirectory = new File(resDirectory.getPath() + "/vectors");
        createDirectory(vectorsDirectory, logBuilder);

        File iconsDirectory = new File(resDirectory.getPath() + "/icons");
        createDirectory(iconsDirectory, logBuilder);

        File savegamesDirectory = new File(gamesDirectoryPath + "/savegames");
        createDirectory(savegamesDirectory, logBuilder);

        File tempDirectory = new File(gamesDirectoryPath + "/temp");
        createDirectory(tempDirectory, logBuilder);
        createFile(tempDirectory, "temp.txt", logBuilder);

        writeLog(gamesDirectoryPath + "/temp/temp.txt", logBuilder.toString());

    }

    private static void createDirectory(File directory, StringBuilder logBuilder) {
        if (!directory.exists()) {
            logBuilder.append("Создание папки " + directory.getName() + "...\n");
            boolean created = directory.mkdir();
            if (created) {
                logBuilder.append("Папка " + directory.getName() + " успешно создана.\n");
            } else {
                logBuilder.append("Не удалось создать папку " + directory.getName() + ".\n");
            }
        }
    }

    private static void createFile(File directory, String fileName, StringBuilder logBuilder) {
        File file = new File(directory.getPath() + "/" + fileName);
        if (!file.exists()) {
            logBuilder.append("Создание файла " + fileName + "...\n");
            try {
                boolean created = file.createNewFile();
                if (created) {
                    logBuilder.append("Файл " + fileName + " успешно создан.\n");
                } else {
                    logBuilder.append("Не удалось создать файл " + fileName + ".\n");
                }
            } catch (IOException e) {
                logBuilder.append("Ошибка при создании файла " + fileName + ": " + e.getMessage() + "\n");
            }
        }
    }

    private static void writeLog(String logFilePath, String log) {
        try {
            FileWriter writer = new FileWriter(logFilePath);
            writer.write(log);
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл лога: " + e.getMessage());
        }
    }
}