package com.bom.service;

import com.bom.dto.DocumentRequest;
import com.bom.dto.DocumentResponse;
import com.bom.model.Document;
import com.bom.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public DocumentResponse createDocument(DocumentRequest request) {
        Document document = new Document();
        document.setTitle(request.getTitle());
        document.setContent(request.getContent());

        Document savedDocument = documentRepository.save(document);
        return new DocumentResponse(savedDocument.getId(), savedDocument.getTitle(), savedDocument.getContent());
    }

    public List<DocumentResponse> getAllDocuments() {
        return documentRepository.findAll()
                .stream()
                .map(doc -> new DocumentResponse(doc.getId(), doc.getTitle(), doc.getContent()))
                .collect(Collectors.toList());
    }

    public DocumentResponse getDocumentById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return new DocumentResponse(document.getId(), document.getTitle(), document.getContent());
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}
