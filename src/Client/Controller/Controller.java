package Client.Controller;


import Client.View.Graphic;
import ControlBox.ControlBox;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Controller {
    private static YaGson yaGson;
    private static String input;
    private static String region = "";
    private static String type;
    private static final Scanner scanner = new Scanner(System.in);
    private static Socket socket;
    private static Scanner serveerInput;
    private static Formatter serverOutput;

    private Controller(String region) {
        Controller.region = region;
    }

    public static void setRegion(String region) {
        Controller.region = region;
    }

    static {
        YaGsonBuilder yaGsonBuilder = new YaGsonBuilder();
        yaGson = yaGsonBuilder.create();
    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File musicPath = new File("src\\Client\\Heroic Demise (New).wav");
        AudioInputStream audioInput = null;
        audioInput = AudioSystem.getAudioInputStream(musicPath);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        //connect to server
        String IP ;
        IP = scanner.nextLine();
        socket = new Socket(IP, 8888);
        serveerInput = new Scanner(socket.getInputStream());
        serverOutput = new Formatter(socket.getOutputStream());
        Graphic.main(new String[2]);
        region = "Account";
    }
    public static void sendToServer(ControlBox controlBox) {
        YaGson yaGson = yaGson();
        String oblect = yaGson.toJson(controlBox);
        serverOutput.format(oblect + "\n");
        serverOutput.flush();
    }

    public static ControlBox giveFromGraphic(ControlBox controlBox) {
        if (controlBox == null) {
            controlBox = new ControlBox();
            controlBox.setRegion("xxx");
        }
        if (controlBox.getRegion().equals("chat")){
            int a=1;
        }
        sendToServer(controlBox);
        ControlBox answer = getFromServer();
        if (answer.getType() != null) {
            if (answer.getType().equals("matchMaking")) {
                if (answer.isSucces()) {
                    Graphic.setRegion("Battle");
                    return null;
                }
            }
        }
        return answer;
    }

    public static YaGson yaGson() {
        return yaGson;
    }

    public static ControlBox getFromServer() {
        String get = "";
        while (true) {
            if (serveerInput.hasNextLine()) {
                get = serveerInput.nextLine();
                break;
            }
        }
        return yaGson.fromJson(get, ControlBox.class);
    }

    public static ControlBox getInWait() {
        if (serveerInput.hasNextLine()) {
            String get = serveerInput.nextLine();
            return yaGson.fromJson(get, ControlBox.class);
        }
        return null;
    }

    public static void printInMenu() {
        System.out.println("1.Collection");
        System.out.println("2.Shop");
        System.out.println("3.Battle");
        System.out.println("4.Exit");
        System.out.println("5.Account");
        System.out.println("6.Help");
    }
}
