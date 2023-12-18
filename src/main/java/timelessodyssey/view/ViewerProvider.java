package timelessodyssey.view;

import timelessodyssey.view.elements.*;

import java.io.IOException;

public class ViewerProvider {
    private final ParticleViewer particleViewer;
    private final PlayerViewer playerViewer;
    private final SpikeViewer spikeViewer;
    private final StarViewer starViewer;
    private final TileViewer tileViewer;

    public ViewerProvider(SpriteLoader spriteLoader) throws IOException {
        this.particleViewer = new ParticleViewer();
        this.playerViewer = new PlayerViewer(spriteLoader);
        this.spikeViewer = new SpikeViewer(spriteLoader);
        this.starViewer = new StarViewer(spriteLoader);
        this.tileViewer = new TileViewer(spriteLoader);
    }

    public ParticleViewer getParticleViewer() {
        return particleViewer;
    }

    public PlayerViewer getPlayerViewer() {
        return playerViewer;
    }

    public SpikeViewer getSpikeViewer() {
        return spikeViewer;
    }

    public StarViewer getStarViewer() {
        return starViewer;
    }

    public TileViewer getTileViewer() {
        return tileViewer;
    }
}
