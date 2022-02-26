import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static boolean createDir(File file) {
        return file.mkdirs();
    }

    private static boolean createFile(File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    private static void logDirCreation(SimpleLogging log, File file) {
        log.write(createDir(file), "Создание директории " + file.getPath());
    }

    private static void logFileCreation(SimpleLogging log, File file) {
        log.write(createFile(file), "Создание файла " + file.getPath());
    }

    public static void main(String[] args) {
        final SimpleLogging log = SimpleLogging.start();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Укажите папку для установки программы: ");
            final File defaultPath = new File(scanner.nextLine());
            final boolean checkDefaultPath = (defaultPath.exists() || defaultPath.mkdirs()) && defaultPath.isDirectory();
            log.write(checkDefaultPath, "Инициализация установки " + defaultPath.getPath());

            if (checkDefaultPath) {
                File src = new File(defaultPath, "src");
                logDirCreation(log, src);
                File res = new File(defaultPath, "res");
                logDirCreation(log, res);
                File savegames = new File(defaultPath, "savegames");
                logDirCreation(log, savegames);
                File temp = new File(defaultPath, "temp");
                logDirCreation(log, temp);

                File main = new File(src, "main");
                logDirCreation(log, main);
                File test = new File(src, "test");
                logDirCreation(log, test);

                File drawables = new File(res, "drawables");
                logDirCreation(log, drawables);
                File vectors = new File(res, "vectors");
                logDirCreation(log, vectors);
                File icons = new File(res, "icons");
                logDirCreation(log, icons);

                File mainJava = new File(main, "Main.java");
                logFileCreation(log, mainJava);
                File utilsJava = new File(main, "Utils.java");
                logFileCreation(log, utilsJava);

                File tempTxt = new File(temp, "temp.txt");
                logFileCreation(log, tempTxt);

                log.save(tempTxt);
            }

            log.print();
        }
    }
}