package ru.yanchenko.vlad.graphicsapps.generics;

import ru.yanchenko.vlad.graphicsapps.listeners.LabelMouseListener;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Влад on 07.07.2017.
 */
public class RenderButton {

    //** Strings that stand for a paths where an images of a render button reside
    private String strImgStartInitial = "pics/RenderButton/Start_Initial.png";
    private String strImgStartHovered = "pics/RenderButton/Start_Hovered.png";
    private String strImgStartPressed = "pics/RenderButton/Start_Pressed.png";
    private String strImgPauseInitial = "pics/RenderButton/Pause_Initial.png";
    private String strImgPauseHovered = "pics/RenderButton/Pause_Hovered.png";
    private String strImgPausePressed = "pics/RenderButton/Pause_Pressed.png";
    private String strImgContinueInitial = "pics/RenderButton/Continue_Initial.png";
    private String strImgContinueHovered = "pics/RenderButton/Continue_Hovered.png";
    private String strImgContinuePressed = "pics/RenderButton/Continue_Pressed.png";
    private String strImgInitInitial = "pics/RenderButton/Init_Initial.png";
    private String strImgInitHovered = "pics/RenderButton/Init_Hovered.png";
    private String strImgInitPressed = "pics/RenderButton/Init_Pressed.png";

    // Mapping images' names to their paths.
    private Map<String, String> mapStrImages = new HashMap();

    //** Image shown on a JLabel with a default text ("Start").
    private ImageIcon image = new ImageIcon(strImgStartInitial);
    //** In charge of running / stopping / continuing convergence.
    private JLabel view = new JLabel(image);

    public RenderButton() {
        view.setSize(image.getIconWidth(), image.getIconHeight());
        view.addMouseListener(new LabelMouseListener());
        populateImagesToStringsMap(mapStrImages);
        view.setIcon(image);
    }

    //** Populate images names
    private void populateImagesToStringsMap(Map map) {
        map.put("continueHovered", strImgContinueHovered);
        map.put("continueInitial", strImgContinueInitial);
        map.put("continuePressed", strImgContinuePressed);

        map.put("initHovered", strImgInitHovered);
        map.put("initInitial", strImgInitInitial);
        map.put("initPressed", strImgInitPressed);

        map.put("pauseHovered", strImgPauseHovered);
        map.put("pauseInitial", strImgPauseInitial);
        map.put("pausePressed", strImgPausePressed);

        map.put("startHovered", strImgStartHovered);
        map.put("startInitial", strImgStartInitial);
        map.put("startPressed", strImgStartPressed);
    }

    public ImageIcon changeLookRenderButtonEntered(ImageIcon image, JLabel label, Map map) {
        if (image.getDescription().equals(map.get("startInitial"))) {
            image = new ImageIcon((String) map.get("startHovered"));
            label.setIcon(image);
        }

        if (image.getDescription().equals(map.get("pauseInitial"))) {
            image = new ImageIcon((String) map.get("pauseHovered"));
            label.setIcon(image);
        }

        if (image.getDescription().equals(map.get("continueInitial"))) {
            image = new ImageIcon((String) map.get("continueHovered"));
            label.setIcon(image);
        }

        if (image.getDescription().equals(map.get("initInitial"))) {
            image = new ImageIcon((String) map.get("initHovered"));
            label.setIcon(image);
        }

        return image;
    }

    public ImageIcon changeLookRenderButtonExited(ImageIcon image, JLabel label, Map map) {
        //        System.out.println(image.getDescription());
        //        System.out.println(map.get("startHovered"));
        if (image.getDescription().equals(map.get("startHovered"))) {
            image = new ImageIcon((String) map.get("startInitial"));
            label.setIcon(image);
        }

        if (image.getDescription().equals(map.get("pauseHovered"))) {
            image = new ImageIcon((String) map.get("pauseInitial"));
            label.setIcon(image);
        }

        if (image.getDescription().equals(map.get("continueHovered"))) {
            image = new ImageIcon((String) map.get("continueInitial"));
            label.setIcon(image);
        }

        if (image.getDescription().equals(map.get("initHovered"))) {
            image = new ImageIcon((String) map.get("initInitial"));
            label.setIcon(image);
        }

        return image;
    }

    public ImageIcon changeLookRenderButtonPressed(ImageIcon image, JLabel label, Map map) {
        if (image.getDescription().equals(map.get("startHovered"))) {
            image = new ImageIcon((String) map.get("startPressed"));
            label.setIcon(image);
        }

        if (image.getDescription().equals(map.get("pauseHovered"))) {
            image = new ImageIcon((String) map.get("pausePressed"));
            label.setIcon(image);
        }

        if (image.getDescription().equals(map.get("continueHovered"))) {
            image = new ImageIcon((String) map.get("continuePressed"));
            label.setIcon(image);
        }

        if (image.getDescription().equals(map.get("initHovered"))) {
            image = new ImageIcon((String) map.get("initPressed"));
            label.setIcon(image);
        }

        return image;
    }

    public ImageIcon changeLookRenderButtonReleased(ImageIcon image, JLabel label, Map map) {
        if (image.getDescription().equals(map.get("startPressed"))) {
            image = new ImageIcon((String) map.get("pauseHovered"));
            label.setIcon(image);
        }

        if (image.getDescription().equals(map.get("pausePressed"))) {
            image = new ImageIcon((String) map.get("continueHovered"));
            label.setIcon(image);
        }

        if (image.getDescription().equals(map.get("continuePressed"))) {
            image = new ImageIcon((String) map.get("pauseHovered"));
            label.setIcon(image);
        }

        if (image.getDescription().equals(map.get("initPressed"))) {
            image = new ImageIcon((String) map.get("startInitial"));
            label.setIcon(image);
        }

        return image;
    }

    public JLabel getView() {
        return view;
    }

    public void setView(JLabel view) {
        this.view = view;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
}
