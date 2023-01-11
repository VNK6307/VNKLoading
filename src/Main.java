import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        // TODO ввести переменные для адресов
        String archivePath = "E:\\Java\\Netology\\Projects\\JavaCore\\1.3_Input-Output\\Games\\savegames\\zipSavings.zip";
        String folderPath = "E:\\Java\\Netology\\Projects\\JavaCore\\1.3_Input-Output\\Games\\savegames\\";

        openZip(archivePath, folderPath);
        String savingPath = "E:\\Java\\Netology\\Projects\\JavaCore\\1.3_Input-Output\\Games\\savegames\\unzipedPlayer1.dat";
        GameProgress unzippedPlayer1 = openPogress(savingPath);
        savingPath = "E:\\Java\\Netology\\Projects\\JavaCore\\1.3_Input-Output\\Games\\savegames\\unzipedPlayer2.dat";
        GameProgress unzippedPlayer2 = openPogress(savingPath);
        savingPath = "E:\\Java\\Netology\\Projects\\JavaCore\\1.3_Input-Output\\Games\\savegames\\unzipedPlayer3.dat";
        GameProgress unzippedPlayer3 = openPogress(savingPath);
        System.out.println("Player1 " + unzippedPlayer1);
        System.out.println("Player2 " + unzippedPlayer2);
        System.out.println("Player3 " + unzippedPlayer3);
    }// main

    public static void openZip(String filePath, String unzipPath) {
        try (ZipInputStream zin = new ZipInputStream(
                new FileInputStream(filePath))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(unzipPath + "unziped" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openPogress(String path) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}