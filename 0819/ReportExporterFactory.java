interface ReportExporter {
    String export(String title, int[] values);
}

class CsvExporter implements ReportExporter {
    @Override
    public String export(String title, int[] values) {
        StringBuilder sb = new StringBuilder(title);
        if (values != null) {
            for (int value : values) {
                sb.append(",").append(value);
            }
        }
        return sb.toString();
    }
}

class JsonExporter implements ReportExporter {
    @Override
    public String export(String title, int[] values) {
        StringBuilder sb = new StringBuilder("{\"title\":\"" + title + "\",\"values\":[");
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(values[i]);
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}

class TextExporter implements ReportExporter {
    @Override
    public String export(String title, int[] values) {
        StringBuilder sb = new StringBuilder(title + ": ");
        if (values == null || values.length == 0) {
            sb.append("no data");
        } else {
            for (int value : values) {
                sb.append(value).append(" ");
            }
        }
        return sb.toString().trim();
    }
}

public class ReportExporterFactory {
    static ReportExporter createExporter(String format) {
        if ("csv".equalsIgnoreCase(format)) {
            return new CsvExporter();
        }
        if ("json".equalsIgnoreCase(format)) {
            return new JsonExporter();
        }
        return new TextExporter();
    }

    static void exportReport(ReportExporter exporter, String title, int[] values) {
        System.out.println(exporter.export(title, values));
    }

    public static void main(String[] args) {
        int[] values = {80, 90, 70};

        exportReport(createExporter("csv"), "Scores", values);
        exportReport(createExporter("json"), "Scores", values);
        exportReport(createExporter("xml"), "Scores", values);
        exportReport(createExporter("text"), "Empty", null);
    }
}
