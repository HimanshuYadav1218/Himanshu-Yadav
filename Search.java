package Himanshu;
import java.io.*;
import java.util.*;

public class Search {

    // Map to store the word counts in each document
    private Map<String, Map<String, Integer>> documentWords = new HashMap<>();
    private List<String> documents = new ArrayList<>();
    private Map<String, Integer> documentFrequency = new HashMap<>();

    // Method to load and parse documents from a directory
    public void loadDocuments(String directoryPath) throws IOException {
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                String documentName = file.getName();
                documents.add(documentName);
                Map<String, Integer> wordCounts = new HashMap<>();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\W+");
                    for (String word : words) {
                        word = word.toLowerCase();
                        if (!word.isEmpty()) {
                            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                        }
                    }
                }
                documentWords.put(documentName, wordCounts);
                reader.close();
                
                // Update document frequency for IDF
                Set<String> uniqueWordsInDoc = new HashSet<>(wordCounts.keySet());
                for (String word : uniqueWordsInDoc) {
                    documentFrequency.put(word, documentFrequency.getOrDefault(word, 0) + 1);
                }
            }
        }
    }

    // Method to compute Term Frequency (TF)
    private double calculateTF(String term, Map<String, Integer> wordCounts) {
        int termCount = wordCounts.getOrDefault(term, 0);
        int totalTerms = wordCounts.values().stream().mapToInt(Integer::intValue).sum();
        return (double) termCount / totalTerms;
    }

    // Method to compute Inverse Document Frequency (IDF)
    private double calculateIDF(String term) {
        int documentCount = documents.size();
        int docContainingTerm = documentFrequency.getOrDefault(term, 0);
        if (docContainingTerm == 0) {
            return 0;
        }
        return Math.log((double) documentCount / docContainingTerm);
    }

    // Method to calculate TF-IDF score for a term in a document
    private double calculateTFIDF(String term, Map<String, Integer> wordCounts) {
        double tf = calculateTF(term, wordCounts);
        double idf = calculateIDF(term);
        return tf * idf;
    }

    // Method to perform the search based on a query
    public void search(String query) {
        String[] queryWords = query.toLowerCase().split("\\W+");
        Map<String, Double> documentScores = new HashMap<>();

        for (String doc : documents) {
            double score = 0.0;
            Map<String, Integer> wordCounts = documentWords.get(doc);

            // For each word in the query, calculate the TF-IDF score for the document
            for (String word : queryWords) {
                if (!word.isEmpty()) {
                    score += calculateTFIDF(word, wordCounts);
                }
            }
            documentScores.put(doc, score);
        }

        // Sort documents based on scores
        documentScores.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .forEach(entry -> System.out.println("Document: " + entry.getKey() + " | Score: " + entry.getValue()));
    }

    // Main method to test the search engine
    public static void main(String[] args) throws IOException {
        Search engine = new Search();

        // Load documents from the "docs" directory (you can modify this path to match your setup)
        engine.loadDocuments("docs");

        // Search for a query
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your search query:");
        String query = scanner.nextLine();
        engine.search(query);
    }
}
