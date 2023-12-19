package timelessodyssey.view;

import timelessodyssey.view.elements.*;
import timelessodyssey.view.menu.EntryViewer;
import timelessodyssey.view.menu.LogoViewer;
import timelessodyssey.view.text.GameTextViewer;
import timelessodyssey.view.text.TextViewer;

import java.io.IOException;

public class ViewerProvider {
    private final ParticleViewer particleViewer;
    private final PlayerViewer playerViewer;
    private final SpikeViewer spikeViewer;
    private final StarViewer starViewer;
    private final TileViewer tileViewer;
    private final TextViewer textViewer;
    private final EntryViewer entryViewer;
    private final LogoViewer logoViewer;

    public ViewerProvider(SpriteLoader spriteLoader) throws IOException {
        this.particleViewer = new ParticleViewer();
        this.playerViewer = new PlayerViewer(spriteLoader);
        this.spikeViewer = new SpikeViewer(spriteLoader);
        this.starViewer = new StarViewer(spriteLoader);
        this.tileViewer = new TileViewer(spriteLoader);
        this.textViewer = new GameTextViewer();
        this.entryViewer = new EntryViewer(textViewer);
        this.logoViewer = new LogoViewer(spriteLoader);
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

    public TextViewer getTextViewer() {
        return textViewer;
    }

    public EntryViewer getEntryViewer() {
        return entryViewer;
    }

    public LogoViewer getLogoViewer() {
        return logoViewer;
    }
}
