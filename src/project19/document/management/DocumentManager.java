package project19.document.management;

import java.util.ArrayList;
import java.util.List;

public class DocumentManager {
    // This is where you would link to your computer's folder
    private String sourcePath = "C:/Users/DD/Documents/ProjectFiles";
    private List<DocumentStructure> docs = new ArrayList<>();

    public void addDocument(String name, String ext, double size) {
        docs.add(new DocumentStructure(name, ext, size));
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public List<DocumentStructure> getAllDocs() {
        return docs;
    }
}


