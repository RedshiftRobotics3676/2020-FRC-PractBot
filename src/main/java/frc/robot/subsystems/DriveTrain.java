/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  Spark FL, BL, FR, BR;
  public static Spark Fire;
  double Left, Right, PRight, PLeft, Max;

  public DriveTrain() {
    Max = 0.05;
    FL = new Spark(2);
    BL = new Spark(3);
    FR = new Spark(4);
    BR = new Spark(1);
    Fire = new Spark(5);
  }

  @Override
  public void periodic() {
    Right = RobotContainer.XboxController.getY(Hand.kRight);
    Left = RobotContainer.XboxController.getY(Hand.kLeft);
    
    if (Right-PRight > Max) {
      Right = PRight + Max;
    }
    else if (PRight-Right > Max) {
      Right = PRight - Max;
    }

    if (Left-PLeft > Max) {
      Left = PLeft + Max;
    }
    else if (PLeft-Left > Max) {
      Left = PLeft - Max;
    }

    FL.set(-Left*0.5);
    BL.set(-Left*0.5);
    FR.set(Right*0.5);
    BR.set(Right*0.5);

    PLeft = Left;
    PRight = Right;

    if (RobotContainer.XboxController.getYButton()) {
      Fire.set(1.0);
    }
    else {
      Fire.set(0.0);
    }
  }
}
