package timelessodyssey.model.game.elements.player;

import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.particles.DeathParticle;
import timelessodyssey.model.game.elements.particles.Particle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeadState extends PlayerState {
    private long duration;
    private final List<Particle> deathParticles;

    public DeadState(Player player, long duration) {
        super(player);
        this.duration = duration;
        this.deathParticles = createParticles();
    }

    public long getDuration() {
        return duration;
    }

    public List<Particle> getDeathParticles() {
        return deathParticles;
    }

    private List<Particle> createParticles() {
        List<Particle> particles = new ArrayList<>();
        int numParticles = 20;
        Random random = new Random();
        for (int i = 0; i <= numParticles; i++) {
            double x = getPlayer().getPosition().x(), y = getPlayer().getPosition().y();
            double velocity = random.nextDouble() < 0.3 ? 2.0 : 1.2;
            double angle = 2 * Math.PI * i / numParticles;
            particles.add(new DeathParticle(x, y, velocity, angle));
        }
        return particles;
    }

    @Override
    public Vector jump() {
        return updateVelocity(getPlayer().getVelocity());
    }

    @Override
    public Vector dash() {
        return updateVelocity(getPlayer().getVelocity());
    }

    @Override
    public Vector updateVelocity(Vector velocity) {
        duration--;
        return new Vector(0, 0);
    }

    @Override
    public PlayerState getNextState() {
        return this;
    }
}
