package Audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class AudioPlayer {
    public static final int MENU = 0;
    public static final int GAME = 1;

    public static final int ATTACK = 0;
    public static final int JUMP = 1;
    public static final int DOUBLE_JUMP = 2;
    public static final int PICK_ITEM = 3;
    public static final int GAME_OVER = 4;

    private final Clip[] songs, effects;
    private int currentSongID;
    private float volume = 1.0f;
    private boolean songMuted = false;
    private boolean effectMuted = false;

    public AudioPlayer() {
        songs = new Clip[2];
        effects = new Clip[5];
        loadResources();
        playSong(MENU);
    }

    private void loadResources() {
        loadSong();
        loadEffect();
    }

    private void loadSong() {
        String[] names = {"menuBgm", "GameMusic"};
        for (int i = 0; i < names.length; i++) {
            songs[i] = getClip("audio/" + names[i] + ".wav");
        }
    }

    private void loadEffect() {
        String[] names = {"attack", "jump", "doublejump", "pickupitem", "gameover"};
        for (int i = 0; i < names.length; i++) {
            effects[i] = getClip("audio/" + names[i] + ".WAV");
        }
    }

    private Clip getClip(String path) {
        try {
            URL url = getClass().getClassLoader().getResource(path);
            if (url == null) {
                throw new RuntimeException("Audio file not found: " + path);
            }

            try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(url)) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                return clip;
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException("Failed to load audio: " + path, e);
        }
    }

    private void updateSongVolume() {
        if (songs[currentSongID] != null && songs[currentSongID].isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) songs[currentSongID].getControl(FloatControl.Type.MASTER_GAIN);
            setGain(gainControl, volume);
        }
    }

    private void updateEffectVolume() {
        for (Clip effect : effects) {
            if (effect != null && effect.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) effect.getControl(FloatControl.Type.MASTER_GAIN);
                setGain(gainControl, volume);
            }
        }
    }

    private void setGain(FloatControl control, float volume) {
        float range = control.getMaximum() - control.getMinimum();
        float gain = (range * volume) + control.getMinimum();
        control.setValue(gain);
    }

    public void toggleSongMute() {
        this.songMuted = !this.songMuted;
        for (Clip song : songs) {
            if (song != null && song.isControlSupported(BooleanControl.Type.MUTE)) {
                BooleanControl booleanControl = (BooleanControl) song.getControl(BooleanControl.Type.MUTE);
                booleanControl.setValue(songMuted);
            }
        }
    }

    public void toggleEffectMute() {
        this.effectMuted = !this.effectMuted;
        for (Clip effect : effects) {
            if (effect != null && effect.isControlSupported(BooleanControl.Type.MUTE)) {
                BooleanControl booleanControl = (BooleanControl) effect.getControl(BooleanControl.Type.MUTE);
                booleanControl.setValue(effectMuted);
            }
        }
    }

    public void setVolume(float volume) {
        this.volume = Math.max(0.0f, Math.min(1.0f, volume)); // Clamp value between 0 and 1
        updateSongVolume();
        updateEffectVolume();
    }

    public void stopSong() {
        if (songs[currentSongID] != null && songs[currentSongID].isActive()) {
            songs[currentSongID].stop();
        }
    }

    public void playEffect(int effect) {
        if (effect >= 0 && effect < effects.length && effects[effect] != null) {
            effects[effect].setFramePosition(0);
            effects[effect].start();
        }
    }

    public void playSong(int song) {
        if (song >= 0 && song < songs.length && songs[song] != null) {
            stopSong();
            currentSongID = song;
            updateSongVolume();
            songs[currentSongID].setFramePosition(0);
            songs[currentSongID].loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void close() {
        for (Clip song : songs) {
            if (song != null) {
                song.close();
            }
        }
        for (Clip effect : effects) {
            if (effect != null) {
                effect.close();
            }
        }
    }
}