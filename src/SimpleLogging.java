import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class SimpleLogging {
    private final StringBuilder log;

    private SimpleLogging() {
        log = new StringBuilder();
    }

    public void write(boolean result, String task) {
        log.append(LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        log.append(" - ");
        log.append(result ? "Успех" : "Неудача");
        log.append(" - ");
        log.append(task);
        log.append(System.lineSeparator());
    }

    public void print() {
        System.out.println(log);
    }

    public void save(File path) {
        if (path.getParentFile().exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
                writer.write(log.toString());
                return;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Ошибка сохранения лог-файла.");
    }

    public static SimpleLogging start() {
        return new SimpleLogging();
    }
}