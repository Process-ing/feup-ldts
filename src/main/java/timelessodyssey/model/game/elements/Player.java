package timelessodyssey.model.game.elements;

import timelessodyssey.model.Position;

import static java.lang.Math.*;

public class Player extends Element {

    private double vx = 0;
    private double vy = 0;

    private double Fx = 0;
    private double Fy = 0;
    private double mass = 1;
    private double gravity = 400;
    private double MAX_VELOCITY = 30;
    public Player(double x, double y) {
        super(x, y);
    }

    public Position move(double time, boolean isGrounded){
        double timeInSeconds = time / 1000.0;

        // Constant Forces
        if (!isGrounded) {
            Fy += gravity * mass;
        }

        // Update Velocity
        vx += (Fx / mass) * timeInSeconds;
        vy += (Fy / mass) * timeInSeconds;

        // Check velocity limit
        if (abs(vx) > MAX_VELOCITY){
            vx = vx > 0 ? MAX_VELOCITY : -MAX_VELOCITY;
        }
        if (isGrounded && Fy >= 0){
            vy = 0;
        }

        // Update Position
        double x = max(min(getPosition().x() + vx * timeInSeconds, 182),0);
        double y = max(min(getPosition().y() + vy * timeInSeconds, 80),0);

        // Reset Forces
        Fy = 0;
        Fx = 0;

        return new Position(x, y);
    }

    public double getVx() {
        return vx;
    }
    public void setvx(double vx) {
        this.vx = vx;
    }

    public void setvy(double vy) {
        this.vy = vy;
    }

    public void setFx(double Fx) {
        this.Fx = Fx;
    }

    public void setFy(double Fy) {
        this.Fy = Fy;
    }
}
