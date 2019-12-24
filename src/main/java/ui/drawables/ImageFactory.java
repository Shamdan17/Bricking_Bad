package ui.drawables;

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
  private static Image ball;
  private static Image chemicalBall;
  private static Image fireBall;
  private static Image confusedAlien;
  private static BufferedImage bufferedImage;

  private static Image chemicalButton;
  private static Image tallerButton;
  private static Image laserButton;
  private static Image magneticButton;

  private static String chemicalBallPowerupPath = "Chemical_ball_Powerup.png";
  private static String cooperativeAlienPath = "CooperativeAlien.png";
  private static String laserBeamPowerupPath = "Laser_Beam_Powerup.png";
  private static String magneticPowerupPath = "Magnetic_Powerup.png";
  private static String protectingAlienPath = "ProtectingAlien.png";
  private static String reparingAlienPath = "RepairingAlien.png";
  private static String tallerPaddlePowerupPath = "Taller_Paddle_Powerup.png";
  private static String ballPath = "NormalBall.png";
  private static String chemicalBallPath = "ChemicalBall.png";
  private static String fireBallPath = "FireBall.png";
  private static String confusedAlienPath = "Confused1.png";


  private static String path = System.getProperty("user.dir") + "/assets/";

  public static Image get(SpecificType type, int width, int length) {
    try {
      switch (type) {
        case ChemicalBallPowerup:
          if (chemicalBallPowerup != null) return chemicalBallPowerup;
          chemicalBallPowerup = getImage(chemicalBallPowerupPath, width, length);
          return chemicalBallPowerup;
        case CooperativeAlien:
          if (cooperativeAlien != null) return cooperativeAlien;
          cooperativeAlien = getImage(cooperativeAlienPath, width, length);
          return cooperativeAlien;
        case DestructiveLaserGun:
          if (laserBeamPowerup != null) return laserBeamPowerup;
          laserBeamPowerup = getImage(laserBeamPowerupPath, width, length);
          return laserBeamPowerup;
        case MagnetPowerup:
          if (magneticPowerup != null) return magneticPowerup;
          magneticPowerup = getImage(magneticPowerupPath, width, length);
          return magneticPowerup;
        case ProtectingAlien:
          if (protectingAlien != null) return protectingAlien;
          protectingAlien = getImage(protectingAlienPath, width, length);
          return protectingAlien;
        case RepairingAlien:
          if (reparingAlien != null) return reparingAlien;
          reparingAlien = getImage(reparingAlienPath, width, length);
          return reparingAlien;
        case TallerPaddlePowerup:
          if (tallerPaddlePowerup != null) return tallerPaddlePowerup;
          tallerPaddlePowerup = getImage(tallerPaddlePowerupPath, width, length);
          return tallerPaddlePowerup;
        case FireBall:
          if(fireBall != null) return fireBall;
          fireBall = getImage(fireBallPath,width,length);
          return fireBall;
        case ChemicalBall:
          if(chemicalBall != null) return chemicalBall;
          chemicalBall = getImage(chemicalBallPath,width,length);
          return chemicalBall;
        case Ball:
          if(ball != null) return ball;
          ball = getImage(ballPath,width,length);
          return ball;
        case ConfusedDrunkAlien:
          if(confusedAlien != null) return confusedAlien;
          confusedAlien = getImage(confusedAlienPath,width,length);
          return confusedAlien;
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
          tallerButton = getImage(tallerPaddlePowerupPath, width, length);
          return tallerButton;
        case MagnetPowerup:
          if (magneticButton != null) return magneticButton;
          magneticButton = getImage(magneticPowerupPath, width, length);
          return magneticButton;
        case ChemicalBallPowerup:
          if (chemicalButton != null) return chemicalButton;
          chemicalButton = getImage(chemicalBallPowerupPath, width, length);
          return chemicalButton;
        case DestructiveLaserGun:
          if (laserButton != null) return laserButton;
          laserButton = getImage(laserBeamPowerupPath, width, length);
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
