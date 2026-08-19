abstract class MediaFile {
    private String fileName;

    MediaFile(String fileName) {
        this.fileName = fileName;
    }

    String getFileName() {
        return fileName;
    }

    abstract String describe();
}

interface Playable {
    void play();
}

interface Compressible {
    int compress();
}

class ImageFile extends MediaFile implements Compressible {
    private int sizeKb;

    ImageFile(String fileName, int sizeKb) {
        super(fileName);
        this.sizeKb = Math.max(0, sizeKb);
    }

    @Override
    String describe() {
        return "Image: " + getFileName() + " " + sizeKb + "KB";
    }

    @Override
    public int compress() {
        return sizeKb / 2;
    }
}

class AudioFile extends MediaFile implements Playable, Compressible {
    private int durationSec;
    private int sizeKb;

    AudioFile(String fileName, int durationSec, int sizeKb) {
        super(fileName);
        this.durationSec = Math.max(0, durationSec);
        this.sizeKb = Math.max(0, sizeKb);
    }

    @Override
    String describe() {
        return "Audio: " + getFileName() + " " + durationSec + "s";
    }

    @Override
    public void play() {
        System.out.println("Playing audio: " + getFileName());
    }

    @Override
    public int compress() {
        return sizeKb * 6 / 10;
    }
}

class VideoFile extends MediaFile implements Playable, Compressible {
    private int durationSec;
    private int sizeKb;

    VideoFile(String fileName, int durationSec, int sizeKb) {
        super(fileName);
        this.durationSec = Math.max(0, durationSec);
        this.sizeKb = Math.max(0, sizeKb);
    }

    @Override
    String describe() {
        return "Video: " + getFileName() + " " + durationSec + "s";
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + getFileName());
    }

    @Override
    public int compress() {
        return sizeKb * 4 / 10;
    }
}

public class MediaProcessingSystem {
    public static void main(String[] args) {
        MediaFile[] files = {
            new ImageFile("photo.png", 800),
            new AudioFile("song.mp3", 200, 4000),
            new VideoFile("clip.mp4", 90, 20000)
        };

        for (MediaFile file : files) {
            System.out.println(file.describe());
            if (file instanceof Playable playable) {
                playable.play();
            }
            if (file instanceof Compressible compressible) {
                System.out.println("compressed=" + compressible.compress() + "KB");
            }
        }
    }
}
