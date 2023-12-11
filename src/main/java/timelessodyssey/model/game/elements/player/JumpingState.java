package timelessodyssey.model.game.elements.player;

import timelessodyssey.model.Vector;

public class JumpingState extends PlayerState {
    public JumpingState(Player player) {
        super(player);
    }

    @Override
    public Vector jump() {
        return updateVelocity(getPlayer().getVelocity());
    }

    @Override
    public Vector updateVelocity(Vector velocity) {
        Vector newVelocity = new Vector(
                velocity.x() * getPlayer().getScene().getFriction(),
                velocity.y() + getPlayer().getScene().getGravity()
        );
        return limitVelocity(applyCollisions(newVelocity));
    }

    @Override
    public PlayerState getNextState() {
        if (getPlayer().getVelocity().y() > 0)
            return new FallingState(getPlayer());
        return this;
    }
}
