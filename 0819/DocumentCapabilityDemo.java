interface DocumentExportable {
    String exportContent();
}

interface DocumentCompressible {
    int compress();
}

class BackupDocument implements DocumentExportable, DocumentCompressible {
    private String title;
    private int sizeKb;

    BackupDocument(String title, int sizeKb) {
        this.title = title;
        this.sizeKb = Math.max(0, sizeKb);
    }

    @Override
    public String exportContent() {
        return title + ".bak (" + sizeKb + "KB)";
    }

    @Override
    public int compress() {
        return sizeKb / 2;
    }
}

public class DocumentCapabilityDemo {
    public static void main(String[] args) {
        BackupDocument document = new BackupDocument("Report", 400);

        DocumentExportable exportable = document;
        DocumentCompressible compressible = document;

        System.out.println(exportable.exportContent());
        System.out.println("compressed=" + compressible.compress() + "KB");
        System.out.println("same object: "
                + (exportable == compressible));
    }
}
