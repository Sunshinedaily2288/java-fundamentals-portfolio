package com.matharsa.enterprise.logistics.document_tracker;

public class DocumentStructure {
    String name;
    String extension;
    double size;

    public DocumentStructure(String name, String extension, double size) {
        this.name = name;
        this.extension = extension;
        this.size = size;
    }
}

