package ui;

import domain.model.SpecificType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFactory {

  private static Image chemicalBallPowerup;
  private static Image cooperativeAlien;
  private static Image laserBeamPowerup;
  private static Image magneticPowerup;
  private static Image protectingAlien;
  private static Image reparingAlien;
  private static Image tallerPaddlePowerup;
  private static BufferedImage bufferedImage;

  private static Image chemicalButton;
  private static Image tallerButton;
  private static Image laserButton;
  private static Image magneticButton;

  private static String path = System.getProperty("user.dir") + "/assets/";

  public static Image get(SpecificType type, int width, int length) {
    try {
      switch (type) {
        case ChemicalBallPowerup:
          if (chemicalBallPowerup != null) return chemicalBallPowerup;
          chemicalBallPowerup = getImage("Chemical_ball_Powerup.png", width, length);
          return chemicalBallPowerup;
        case CooperativeAlien:
          if (cooperativeAlien != null) return cooperativeAlien;
          cooperativeAlien = getImage("CooperativeAlien.png", width, length);
          return cooperativeAlien;
        case DestructiveLaserGun:
          if (laserBeamPowerup != null) return laserBeamPowerup;
          laserBeamPowerup = getImage("Laser_Beam_Powerup.png", width, length);
          return laserBeamPowerup;
        case MagnetPowerup:
          if (magneticPowerup != null) return magneticPowerup;
          magneticPowerup = getImage("Magnetic_Powerup.png", width, length);
          return magneticPowerup;
        case ProtectingAlien:
          if (protectingAlien != null) return protectingAlien;
          protectingAlien = getImage("ProtectingAlien.png", width, length);
          return protectingAlien;
        case RepairingAlien:
          if (reparingAlien != null) return reparingAlien;
          reparingAlien = getImage("RepairingAlien.png", width, length);
          return reparingAlien;
        case TallerPaddlePowerup:
          if (tallerPaddlePowerup != null) return tallerPaddlePowerup;
          tallerPaddlePowerup = getImage("Taller_Paddle_Powerup.png", width, length);
          return tallerPaddlePowerup;
        default:
          throw new IllegalArgumentException("Unsupported type provided: " + type);
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Image getImageButton(SpecificType type, int width, int length) {
    try {
      switch (type) {
        case TallerPaddlePowerup:
          if (tallerButton != null) return tallerButton;
          tallerButton = getImage("Taller_Paddle_Powerup.png", width, length);
          return tallerButton;
        case MagnetPowerup:
          if (magneticButton != null) return magneticButton;
          magneticButton = getImage("Magnetic_Powerup.png", width, length);
          return magneticButton;
        case ChemicalBallPowerup:
          if (chemicalButton != null) return chemicalButton;
          chemicalButton = getImage("Chemical_ball_Powerup.png", width, length);
          return chemicalButton;
        case DestructiveLaserGun:
          if (laserButton != null) return laserButton;
          laserButton = getImage("Laser_Beam_Powerup.png", width, length);
          return laserButton;
        default:
          throw new IllegalArgumentException("Unsupported type provided " + type);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private static Image getImage(String type, int width, int length) throws IOException {
    bufferedImage = ImageIO.read(new File(path + type));
    return bufferedImage.getScaledInstance(width, length, Image.SCALE_DEFAULT);
  }
}
